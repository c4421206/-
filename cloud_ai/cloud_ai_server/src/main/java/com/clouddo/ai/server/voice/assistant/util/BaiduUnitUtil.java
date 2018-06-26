package com.clouddo.ai.server.voice.assistant.util;

import com.clouddo.ai.server.config.BaiduVoiceConfig;
import com.clouddo.ai.server.constatns.ConversationStatus;
import com.clouddo.ai.server.model.CloudIntentDO;
import com.clouddo.ai.server.model.CloudSlotDO;
import com.clouddo.ai.server.pojo.dto.SlotDto;
import com.clouddo.ai.server.service.CloudIntentService;
import com.clouddo.ai.server.service.CloudSlotService;
import com.clouddo.ai.server.voice.assistant.model.baidu.Conversation;
import com.clouddo.ai.server.voice.assistant.model.baidu.RequestModel;
import com.clouddo.ai.server.voice.assistant.model.baidu.response.ResponseModel;
import com.clouddo.ai.server.voice.assistant.model.baidu.response.Schema;
import com.clouddo.ai.server.voice.assistant.model.baidu.response.Slot;
import com.clouddo.commons.common.service.CacheService;
import com.clouddo.commons.common.util.GsonUtil;
import com.clouddo.commons.common.util.JSONUtils;
import com.clouddo.commons.common.util.http.RestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度自然语音交互系统工具类
 * @author zhongming
 * @since 3.0
 * 2018/6/19下午1:54
 */
@Component
public class BaiduUnitUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduUnitUtil.class);

    /**
     * 会话保存时间2小时
     */
    private static final Integer CONVERSATION_TIMEOUT = 2 * 60 * 60;

    /**
     * 缓存服务层
     */
    @Autowired
    private CacheService cacheService;

    @Autowired
    private BaiduVoiceConfig baiduVoiceConfig;

    /**
     * 意图服务层
     */
    @Autowired
    private CloudIntentService cloudIntentService;

    /**
     * 词槽服务层
     */
    @Autowired
    private CloudSlotService cloudSlotService;


    /**
     * 语音对话
     * @param conversationId 对话的ID，第一次对话无需传ID
     * @param message 用户说的话
     * @param bootId bootId，第一次对话需要传入，对话ID不为null，则无需传入
     * @return
     * @throws JsonProcessingException
     */
    public Conversation conversation(String conversationId, String message, String bootId) throws JsonProcessingException {
        //生成会话信息
        Conversation conversation = null;
        if(!StringUtils.isEmpty(conversationId)) {
            conversation = (Conversation) this.cacheService.get(conversationId);
            bootId = conversation.getBootId();
        }
        if(conversation == null) {
            conversation = new Conversation(bootId);
        }
        //创建本次请求的请求体
        RequestModel requestModel = new RequestModel(message, bootId);
        //设置上次会话的session
        if(conversation.getNewestResponse() != null) {
            requestModel.setBotSession(conversation.getNewestResponse().getBotSession());
        }

        //存储本次请求信息
        conversation.addRequest(requestModel);

        //执行发送请求
        ResponseModel responseModel = this.executeRequest(requestModel);

        //进行结果的解析
        if(responseModel != null) {
            conversation.addResponse(responseModel);
            if(ConversationStatus.satisfy.name().equals(responseModel.getFristActionType())) {
                //如果回复信息动作类型为satisfy，则进行最终的解析
                LOGGER.debug("语音解析完毕，开始解析去数据库请求数据解析");
                //设置最终动作话语
                conversation.setMessage(responseModel.getFirstActionSay());
                //开始解析
                conversation = this.intentAnalysis(conversation);
            }
            if(ConversationStatus.clarify.name().equals(responseModel.getFristActionType())) {
                //如果是clarify，则进行初步解析
                conversation.setStatus(ConversationStatus.clarify.name());
                conversation = this.conversationAnalysis(conversation);
            }
        } else {
            conversation.setStatus(ConversationStatus.error.name());
        }


        //将对话信息保存到缓存
        this.cacheService.put(conversation.getId(), conversation, CONVERSATION_TIMEOUT);
        return conversation;
    }

    /**
     * 执行发送请求信息，并返回回复实体
     * @param requestModel 请求信息
     * @return 回复实体
     * @throws JsonProcessingException
     */
    private ResponseModel executeRequest(RequestModel requestModel) throws JsonProcessingException {
        //设置请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        //获取token
        String token = BaiduAccessTokenUtil.getToken();
        //发送请求
        ResponseEntity responseEntity = RestUtil.restPost(baiduVoiceConfig.getServiceURL() + "?access_token=" + token, headers, JSONUtils.beanToJson(requestModel));
        //解析请求
        if(responseEntity.getStatusCodeValue() == org.apache.http.HttpStatus.SC_OK) {
            Map<String, Object> data = (Map<String, Object>) responseEntity.getBody();
            //获取结果吗
            Integer errorCode = (Integer) data.get("error_code");
            if(errorCode == 0) {
                //请求成功
                return GsonUtil.jsonToBean(GsonUtil.toJson(data.get("result")), ResponseModel.class);
            } else {
                LOGGER.warn("百度UNIT响应失败，错误码：{}，错误信息：{}", errorCode, data.get("error_msg"));
                return null;
            }
        }
        LOGGER.warn("百度UNIT请求失败,错误信息：{}", responseEntity);
        return null;

    }

    /**
     * 进行对话解析
     * @param conversation
     * @return
     */
    private Conversation conversationAnalysis(Conversation conversation) {
        conversation.setMessage(conversation.getNewestResponse().getFirstActionSay());
        return conversation;
    }

    /**
     * 解析意图
     * @param conversation 最终的对话信息
     */
    private Conversation intentAnalysis(Conversation conversation) {

        List<String> warnMessages = new ArrayList<String>();

        Schema schema = conversation.getNewestResponse().getResponse().getSchema();
        //获取意图名称
        String intentName = schema.getIntent();
        //从数据库查询意图信息和意图对应的词条信息
        CloudIntentDO cloudIntent = new CloudIntentDO();
        cloudIntent.setIntentName(intentName);
        cloudIntent = this.cloudIntentService.getWithSolts(cloudIntent);


        //格式化后的词槽信息
        List<SlotDto> soltDtoList = new ArrayList<SlotDto>();

        //解析词槽信息
        List<Slot> slotList = schema.getSlots();
        for(Slot slot : slotList) {
            //从意图信息中获取对应的词槽信息
            CloudSlotDO cloudSlot = cloudIntent.getCloudSlotMap().get(slot.getName());
            if(cloudSlot == null) {
                warnMessages.add("数据库中为找对词槽的对应信息，词槽名【"+ slot.getName() +"】，词槽值【"+ slot.getNormalizedWord() +"】");
                LOGGER.warn("数据库中为找对词槽的对应信息，词槽名：{}，词槽值：{}", slot.getName(), slot.getNormalizedWord());
            }
            Map<String, Object> data = this.cloudSlotService.createSoltDto(slot, cloudSlot);
            SlotDto slotDto = (SlotDto) data.get("data");
            soltDtoList.add(slotDto);
            if(!StringUtils.isEmpty(data.get("message"))) {
                warnMessages.add(data.get("message").toString());
            }
        }
        //如果存在警告信息，则设置状态为警告并设置警告信息
        if(warnMessages.size() > 0) {
            conversation.setStatus(ConversationStatus.warning.name());
            conversation.setWarnMessages(warnMessages);
        }

        conversation.setCloudIntentDO(cloudIntent);
        conversation.setSoltDtoList(soltDtoList);
        return conversation;
    }



}














