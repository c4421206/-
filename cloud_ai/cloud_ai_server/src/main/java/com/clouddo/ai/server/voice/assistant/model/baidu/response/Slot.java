package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 百度UNIT
 * 词槽实体类
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午3:29
 */
public class Slot implements Serializable {

    private static final long serialVersionUID = 2592573308923587097L;

    /**
     * 词槽置信度
     */
    @SerializedName("confidence")
    private Double confidence;

    /**
     * 词槽起始
     */
    @SerializedName("begin")
    private Integer begin;

    /**
     * 	词槽长度
     */
    @SerializedName("length")
    private Integer length;

    /**
     * 词槽值
     */
    @SerializedName("original_word")
    private String originalWord;

    /**
     * 	归一化词槽值
     */
    @SerializedName("normalized_word")
    private String normalizedWord;

    /**
     * 词槽值细化类型
     */
    @SerializedName("word_type")
    private String wordType;

    /**
     * 词槽名称
     */
    @SerializedName("name")
    private String name;

    /**
     * 词槽是在第几轮对话中引入的
     */
    @SerializedName("session_offset")
    private Integer sessionOffset;

    /**
     * 引入的方式
     */
    @SerializedName("merge_method")
    private String mergeMethod;


    //TODO sub_slots还未解析


    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public void setOriginalWord(String originalWord) {
        this.originalWord = originalWord;
    }

    public String getNormalizedWord() {
        return normalizedWord;
    }

    public void setNormalizedWord(String normalizedWord) {
        this.normalizedWord = normalizedWord;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSessionOffset() {
        return sessionOffset;
    }

    public void setSessionOffset(Integer sessionOffset) {
        this.sessionOffset = sessionOffset;
    }

    public String getMergeMethod() {
        return mergeMethod;
    }

    public void setMergeMethod(String mergeMethod) {
        this.mergeMethod = mergeMethod;
    }

}










