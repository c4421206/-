package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 百度UNIT
 * 应答回复实体
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午4:59
 */
public class ResponseModel implements Serializable {

    private static final long serialVersionUID = 2785708548677411673L;

    /**
     * 获取第一个动作的类型
     * @return
     */
    public String getFristActionType() {
        return this.getResponse().getActionList().get(0).getType();
    }

    /**
     * 获取第一个动作的应答话术
     * @return
     */
    public String getFirstActionSay() {
        return this.getResponse().getActionList().get(0).getSay();
    }


    /**
     * 版本信息
     */
    @SerializedName("version")
    private String version;

    /**
     * 	BOT唯一ID
     */
    @SerializedName("bot_id")
    private String botId;

    /**
     * 日志唯一ID（用户与BOT的一问一答为一次interaction，其中用户每说一次对应有一个log_id）
     */
    @SerializedName("log_id")
    private String logId;

    /**
     * session信息
     */
    @SerializedName("bot_session")
    private String botSession;

    /**
     * 为本轮请求+应答之组合，生成的id
     */
    @SerializedName("interaction_id")
    private String interactionId;

    /**
     * 本轮应答体
     */
    @SerializedName("response")
    private Response response;


    public String getVersion() {

        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getBotSession() {
        return botSession;
    }

    public void setBotSession(String botSession) {
        this.botSession = botSession;
    }

    public String getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}





