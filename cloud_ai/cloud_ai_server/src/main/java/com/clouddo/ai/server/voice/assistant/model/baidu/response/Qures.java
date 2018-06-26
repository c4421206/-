package com.clouddo.ai.server.voice.assistant.model.baidu.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 百度UNIT
 * BOT解析的结果
 * @author zhongming
 * @since 3.0
 * 2018/6/15下午4:16
 */
public class Qures implements Serializable {

    private static final long serialVersionUID = -4803959759355137155L;

    /**
     * query结果时间戳
     */
    @SerializedName("timestamp")
    private Integer timestamp;

    /**
     * 	query结果状态
     */
    @SerializedName("status")
    private Integer status;

    /**
     * 原始query
     */
    @SerializedName("raw_query")
    private String rawQuery;

    /**
     * 意图候选项
     */
    @SerializedName("candidates")
    private List<Candidate> candidates;

    /**
     * 最终qu结果，内部格式同result.response.qu_res.candidates[]
     */
    @SerializedName("qu_res_chosen")
    private String quResChosen;

    /**
     * query的词法分析结果
     */
    @SerializedName("lexical_analysis")
    private List<LexicalAnalysis> lexicalAnalysis;

    /**
     * query的情感分析结果
     */
    @SerializedName("sentiment_analysis")
    private Map<String, Object> sentimentAnalysis;


    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRawQuery() {
        return rawQuery;
    }

    public void setRawQuery(String rawQuery) {
        this.rawQuery = rawQuery;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public String getQuResChosen() {
        return quResChosen;
    }

    public void setQuResChosen(String quResChosen) {
        this.quResChosen = quResChosen;
    }

    public List<LexicalAnalysis> getLexicalAnalysis() {
        return lexicalAnalysis;
    }

    public void setLexicalAnalysis(List<LexicalAnalysis> lexicalAnalysis) {
        this.lexicalAnalysis = lexicalAnalysis;
    }

    public Map<String, Object> getSentimentAnalysis() {
        return sentimentAnalysis;
    }

    public void setSentimentAnalysis(Map<String, Object> sentimentAnalysis) {
        this.sentimentAnalysis = sentimentAnalysis;
    }
}







