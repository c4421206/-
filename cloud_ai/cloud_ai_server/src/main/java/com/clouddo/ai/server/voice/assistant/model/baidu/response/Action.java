package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 百度UNIT
 * 动作实体信息
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午2:43
 */
public class Action implements Serializable {

    private static final long serialVersionUID = 8628452376989262187L;

    /**
     * 动作置信度
     */
    @SerializedName("confidence")
    private Double confidence;

    /**
     * 动作ID
     */
    @SerializedName("action_id")
    private String actionId;

    /**
     * 应答话术
     */
    @SerializedName("say")
    private String say;

    /**
     * 用户自定义应答，如果action_type为event，对应事件定义在此处
     */
    @SerializedName("custom_reply")
    private String customReply;

    /**
     * 类型，具体有以下几种:clarify/satisfy/guide/faqguide/understood(理解达成)/failure(理解失败)/chat(聊天话术)/event(触发事件，在"对话意图--场景bot回应--答复"选择了"执行函数"将返回event类型)
     */
    @SerializedName("type")
    private String type;

    /**
     * 『泛澄清』时，即clarify/guide/faqguide时有效
     */
    @SerializedName("refine_detail")
    private RefineDetail refineDetail;


    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }

    public String getCustomReply() {
        return customReply;
    }

    public void setCustomReply(String customReply) {
        this.customReply = customReply;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RefineDetail getRefineDetail() {
        return refineDetail;
    }

    public void setRefineDetail(RefineDetail refineDetail) {
        this.refineDetail = refineDetail;
    }
}












