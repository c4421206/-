package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 百度UNIT
 * query的词法分析结果
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午4:42
 */
public class LexicalAnalysis implements Serializable {

    private static final long serialVersionUID = -5525150002900335858L;

    /**
     * 词汇(含命名实体)))
     */
    @SerializedName("term")
    private String term;

    /**
     * 重要性权重
     */
    @SerializedName("weight")
    private Double weight;

    /**
     * 词性或专名类别
     */
    @SerializedName("type")
    private String type;

    /**
     * 构成词汇的基本词
     */
    @SerializedName("basic_word")
    private List<String> basicWord;



}
