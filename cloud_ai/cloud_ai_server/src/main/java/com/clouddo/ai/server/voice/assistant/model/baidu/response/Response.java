package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 应答体
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午5:04
 */
public class Response implements Serializable {

    private static final long serialVersionUID = 2049706985123579588L;

    /**
     * 动作列表
     */
    @SerializedName("action_list")
    private List<Action> actionList;

    /**
     * 解析的schema，解析意图、词槽结果都从这里面获取
     */
    @SerializedName("schema")
    private Schema schema;

    /**
     * BOT解析的结果
     */
    @SerializedName("qu_res")
    private Qures qures;


    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public Qures getQures() {
        return qures;
    }

    public void setQures(Qures qures) {
        this.qures = qures;
    }
}
