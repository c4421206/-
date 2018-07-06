package com.clouddo.commons.meteo.config.model;

/**
 * 等值面配置实体类
 * @author zhongming
 * @since 1.0
 * 2018/7/2上午9:05
 */
public class IsosurfaceConfig {

    /**
     * x轴格点数
     */
    private Integer xNum;

    /**
     * Y轴格点数
     */
    private Integer yNum;

    /**
     * 半径
     */
    private Integer radius;

    /**
     * 最小点数
     */
    private Integer minPointNum;

    /**
     * 边界shape文件路径
     */
    private String borderShapePath;


    public IsosurfaceConfig(Integer xNum, Integer yNum, Integer radius, Integer minPointNum) {
        this.xNum = xNum;
        this.yNum = yNum;
        this.radius = radius;
        this.minPointNum = minPointNum;
    }

    public Integer getxNum() {
        return xNum;
    }

    public void setxNum(Integer xNum) {
        this.xNum = xNum;
    }

    public Integer getyNum() {
        return yNum;
    }

    public void setyNum(Integer yNum) {
        this.yNum = yNum;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getMinPointNum() {
        return minPointNum;
    }

    public void setMinPointNum(Integer minPointNum) {
        this.minPointNum = minPointNum;
    }

    public String getBorderShapePath() {
        return borderShapePath;
    }

    public void setBorderShapePath(String borderShapePath) {
        this.borderShapePath = borderShapePath;
    }
}
