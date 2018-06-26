package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 百度UNIT
 * 意图候选项
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午4:31
 */
public class Candidate implements Serializable {

    private static final long serialVersionUID = -7137456052502803538L;

    /**
     * 候选项意图名称
     */
    @SerializedName("intent")
    private String intent;

    /**
     * 候选项意图置信度
     */
    @SerializedName("intent_confidence")
    private Double intentConfidence;

    /**
     * 候选项domain分类置信度
     */
    @SerializedName("domain_confidence")
    private Double domainConfidence;

    /**
     * 意图是否需要澄清
     */
    @SerializedName("intent_need_clarify")
    private Boolean intentNeedClarify;

    /**
     * 候选项词槽列表
     */
    @SerializedName("slots")
    private List<Slot> slots;

    /**
     * 来自哪个qu策略（smart-qu对应对话模板，ml-qu对应对话样本学习
     */
    @SerializedName("from_who")
    private String fromWho;

    /**
     * query匹配信息
     */
    @SerializedName("match_info")
    private String matchInfo;

    //TODO extra_info信息为映射，类型为kvdict


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

    public Boolean getIntentNeedClarify() {
        return intentNeedClarify;
    }

    public void setIntentNeedClarify(Boolean intentNeedClarify) {
        this.intentNeedClarify = intentNeedClarify;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public String getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(String matchInfo) {
        this.matchInfo = matchInfo;
    }
}












