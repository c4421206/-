package com.clouddo.ai.server.voice.assistant.model.baidu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度语音交互技术请求实体
 * @author zhongming
 * @since 3.0
 * 2018/6/12下午3:58
 */
public class RequestModel implements Serializable {

    private static final long serialVersionUID = -4935087038429161876L;


    public RequestModel(String message, String botId) {
        this(message, "temp", botId);
    }

    public RequestModel(String message, String userId, String botId) {
        this.logId = System.currentTimeMillis() + "";
        this.request.setQuery(message);
        this.request.setUserId(userId);
        this.botId = botId;
    }

    public void setQueryInfo(String key, Object value) {
        this.request.queryInfo.put(key, value);
    }

    /**
     * 本轮请求query（用户说的话）
     * @param message 用户说的话
     */
    public void setQueryMessage(String message) {
        this.request.setQuery(message);
    }

    public void setBotSession(String botSession) {
        this.botSession = botSession;
    }

    public String getBotSession() {
        return botSession;
    }



    //--------------------------
    /**
     * 本轮请求体
     */
    @JsonProperty("request")
    private Request request = new Request();

    /**
     * 百度unit版本
     */
    @JsonProperty("version")
    private String version = "2.0";

    /**
     * BOT唯一标识，在『我的BOT』的BOT列表中第一列数字即为bot_id
     */
    @JsonProperty("bot_id")
    private String botId;

    /**
     * 开发者需要在客户端生成的唯一id，用来定位请求，响应中会返回该字段。对话中每轮请求都需要一个log_id
     */
    @JsonProperty("log_id")
    private String logId;

    @JsonProperty("bot_session")
    private String botSession = "";


    /**
     * 本轮请求体
     */
    class Request implements Serializable {

        private static final long serialVersionUID = 5930665836133600539L;

        public Request() {
            this.queryInfo.put("type", "TEXT");
            this.queryInfo.put("source", "ASR");
        }

        @JsonProperty("query_info")
        private Map<String, Object> queryInfo = new HashMap<String, Object>();
        /**
         * 与BOT对话的用户id
         *（如果BOT客户端是用户未登录状态情况下对话的，也需要尽量通过其他标识（比如设备id）来唯一区分用户），
         * 方便今后在平台的日志分析模块定位分析问题、从用户维度统计分析相关对话情况
         */
        @JsonProperty("user_id")
        private String userId;

        /**
         * 本轮请求query（用户说的话），详情见【参数详细说明】
         */
        private String query;

        /**
         * client希望传给BOT的本地信息，以一组K-V形式保存，如不需要此字段，
         * 需填充一个默认值：{"client_results":"", "candidate_options":[]}。
         如果需要此字段，其定义详见【参数详细说明】
         */
        @JsonProperty("client_session")
        private String clientSession = "{\"client_results\":\"\", \"candidate_options\":[]}";

        /**
         * 系统自动发现不置信意图/词槽，并据此主动发起澄清确认的敏感程度。
         * 取值范围：0(关闭)、1(中敏感度)、2(高敏感度)。
         * 取值越高BOT主动发起澄清的频率就越高，建议值为1。
         */
        @JsonProperty("bernard_level")
        private Integer bernardLevel = 1;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
