package com.clouddo.commons.meteo.config.model;

import java.awt.*;
import java.io.Serializable;

/**
 * 等值线面分隔点实体类
 * @author zhongming
 * @since 3.0
 * 2018/7/2下午4:18
 */
public class LegendBreak implements Serializable {

    private static final long serialVersionUID = 7310996498017929633L;
    private Double startValue;

    private Double endValue;

    private Color color;


    public LegendBreak(Double startValue, Double endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    public LegendBreak(Double startValue, Double endValue, Color color) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.color = color;
    }

    public Double getStartValue() {
        return startValue;
    }

    public void setStartValue(Double startValue) {
        this.startValue = startValue;
    }

    public Double getEndValue() {
        return endValue;
    }

    public void setEndValue(Double endValue) {
        this.endValue = endValue;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
