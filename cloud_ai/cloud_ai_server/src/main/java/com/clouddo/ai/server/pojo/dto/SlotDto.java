package com.clouddo.ai.server.pojo.dto;

import java.io.Serializable;

/**
 * 词槽数据传输对象
 * @author zhongming
 * @since 3.0
 * 2018/6/21下午1:53
 */
public class SlotDto implements Serializable {

    private static final long serialVersionUID = -2757722503839834397L;

    /** SLOT_NAME - 词槽名称 */
    private String slotName;

    /** FORMAT_NAME - 格式化名称 */
    private String formatName;

    /**
     * 词槽值
     */
    private String originalWord;

    /**
     * 	归一化词槽值
     */
    private String normalizedWord;

    /**
     * 格式化词槽值
     */
    private Object formatValue;

    public SlotDto(String slotName, String formatName, String originalWord, String normalizedWord, Object formatValue) {
        this.slotName = slotName;
        this.formatName = formatName;
        this.originalWord = originalWord;
        this.normalizedWord = normalizedWord;
        this.formatValue = formatValue;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
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

    public Object getFormatValue() {
        return formatValue;
    }

    public void setFormatValue(Object formatValue) {
        this.formatValue = formatValue;
    }
}
