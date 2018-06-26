package com.clouddo.ai.server.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>实体类</p>
 * <p>Table: cloud_intent - 意图实体类</p>
 *
 * @author zhongming
 * @since 2018-06-21 10:14:04
 */
public class CloudIntentDO implements Serializable  {

    private static final long serialVersionUID = -9125423801705287265L;

    /**
     * 意图包含的词槽信息
     */
    private List<CloudSlotDO> cloudSlotList;

    /**
     * 词槽名称与词槽对应关系
     */
    private Map<String, CloudSlotDO> cloudSlotMap = new HashMap<String, CloudSlotDO>();

    /** INTENT_ID - 意图主键 */
    private String intentId;

    /** INTENT_NAME - 意图名称 */
    private String intentName;

    /** TYPE - 意图类型 */
    private String type;

    /** ALIAS - 意图别名，使用逗号分开 */
    private String alias;

    /** DESCRIPTION - 意图描述 */
    private String description;

    /** OPERATION_TYPE - 操作类型（URL跳转，查询） */
    private String operationType;

    /** OPERATION - 操作 */
    private String operation;

    /** BOT_ID - 意图所属bot的主键 */
    private String botId;


    public String getIntentId(){
        return this.intentId; 
    }
    public void setIntentId(String intentId){
        this.intentId = intentId; 
    }

    public String getIntentName(){
        return this.intentName;
    }
    public void setIntentName(String intentName){
        this.intentName = intentName;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getAlias(){
        return this.alias;
    }
    public void setAlias(String alias){
        this.alias = alias;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getOperationType(){
        return this.operationType;
    }
    public void setOperationType(String operationType){
        this.operationType = operationType;
    }

    public String getOperation(){
        return this.operation;
    }
    public void setOperation(String operation){
        this.operation = operation;
    }

    public String getBotId(){
        return this.botId;
    }
    public void setBotId(String botId){
        this.botId = botId;
    }

    public List<CloudSlotDO> getCloudSlotList() {
        return cloudSlotList;
    }

    public void setCloudSlotList(List<CloudSlotDO> cloudSlotList) {
        this.cloudSlotList = cloudSlotList;
    }

    public Map<String, CloudSlotDO> getCloudSlotMap() {
        return cloudSlotMap;
    }

    public void setCloudSlotMap(Map<String, CloudSlotDO> cloudSlotMap) {
        this.cloudSlotMap = cloudSlotMap;
    }

    @Override
    public String toString() {
        return "CloudIntentDO{" +
                "intentId='" + intentId + '\'' +
                ", intentName='" + intentName + '\'' +
                ", type='" + type + '\'' +
                ", alias='" + alias + '\'' +
                ", description='" + description + '\'' +
                ", operationType='" + operationType + '\'' +
                ", operation='" + operation + '\'' +
                ", botId='" + botId + '\'' +
                '}';
    }
}