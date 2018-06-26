package com.clouddo.ai.server.model;


import java.io.Serializable;


/**
 * <p>实体类</p>
 * <p>Table: cloud_slot - 词槽实体类</p>
 * @author zhongming
 *
 * @since 2018-06-21 10:07:33
 * 初始化
 */
public class CloudSlotDO implements Serializable  {

    private static final long serialVersionUID = 2776790648837625555L;

    /**
     * 格式化后的值
     */
    private Object formatValue;


    /** SLOT_ID - 词槽主键 */
    private String slotId;

    /** SLOT_NAME - 词槽名称 */
    private String slotName;

    /** FORMAT_NAME - 格式化名称 */
    private String formatName;

    /** ALIAS - 词槽别名，使用逗号分开 */
    private String alias;

    /** DESCRIPTION - 词槽描述 */
    private String description;

    /** FORMAT_VALUE_CLASS - 格式化值对应的class的限定名 */
    private String formatValueClass;

    /** FORMAT_VALUE_METHOD - 格式化值对应的方法名 */
    private String formatValueMethod;


    public String getSlotId(){
        return this.slotId; 
    }
    public void setSlotId(String slotId){
        this.slotId = slotId; 
    }

    public String getSlotName(){
        return this.slotName;
    }
    public void setSlotName(String slotName){
        this.slotName = slotName;
    }

    public String getFormatName(){
        return this.formatName;
    }
    public void setFormatName(String formatName){
        this.formatName = formatName;
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

    public String getFormatValueClass(){
        return this.formatValueClass;
    }
    public void setFormatValueClass(String formatValueClass){
        this.formatValueClass = formatValueClass;
    }

    public String getFormatValueMethod(){
        return this.formatValueMethod;
    }
    public void setFormatValueMethod(String formatValueMethod){
        this.formatValueMethod = formatValueMethod;
    }

    public Object getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(Object formatValue) {
        this.formatValue = formatValue;
    }

    @Override
    public String toString() {
        return "CloudSlotDO{" +
                "formatValue=" + formatValue +
                ", slotId='" + slotId + '\'' +
                ", slotName='" + slotName + '\'' +
                ", formatName='" + formatName + '\'' +
                ", alias='" + alias + '\'' +
                ", description='" + description + '\'' +
                ", formatValueClass='" + formatValueClass + '\'' +
                ", formatValueMethod='" + formatValueMethod + '\'' +
                '}';
    }
}