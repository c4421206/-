package com.clouddo.ai.server.voice.assistant.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 语义识别解析后的结果
 * @author zhongming
 * @since 3.0
 * 2018/6/4下午4:54
 */
public class ResolveResult implements Serializable {

    private static final long serialVersionUID = -7683719035823981663L;

    //服务ID
    private String ServiceId;

    //操作ID
    private String operationId;

    //识别前的语音/字符串
    private String text;

    //操作参数
    Map<String, Object> parameters = new HashMap<String, Object>(0);


    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String serviceId) {
        ServiceId = serviceId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
