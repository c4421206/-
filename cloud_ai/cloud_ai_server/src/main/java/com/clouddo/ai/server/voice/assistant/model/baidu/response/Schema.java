package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 百度UNIT
 * 解析的schema，解析意图、词槽结果都从这里面获取
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午3:26
 */
public class Schema implements Serializable {

    private static final long serialVersionUID = 1487008611657561097L;

    /**
     * 意图信息
     */
    @SerializedName("intent")
    private String intent;

    /**
     * 词置信度
     */
    @SerializedName("intent_confidence")
    private Double intentConfidence;

    /**
     * domain分类置信度
     */
    @SerializedName("domain_confidence")
    private Double domainConfidence;

    /**
     * 词槽列表
     */
    @SerializedName("slots")
    private List<Slot> slots;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public Double getIntentConfidence() {
        return intentConfidence;
    }

    public void setIntentConfidence(Double intentConfidence) {
        this.intentConfidence = intentConfidence;
    }

    public Double getDomainConfidence() {
        return domainConfidence;
    }

    public void setDomainConfidence(Double domainConfidence) {
        this.domainConfidence = domainConfidence;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}














