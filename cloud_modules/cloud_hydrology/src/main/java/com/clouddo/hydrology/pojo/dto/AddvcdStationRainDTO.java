package com.clouddo.hydrology.pojo.dto;

import java.io.Serializable;

/**
 * 行政区站点降雨量统计
 * @author zhongming
 * @since 1.0
 * 2018/7/5上午10:22
 */
public class AddvcdStationRainDTO implements Serializable {

    private static final long serialVersionUID = 1739604932506497819L;

    /**
     * 行政区编码
     */
    private String addvcd;

    /**
     * 下雨站点数
     */
    private Integer rainStationNum;

    /**
     * 总站点数
     */
    private Integer stationNum;

    /**
     * 降雨量
     */
    private Double drp;

    /**
     * 最大降雨站点降雨量
     */
    private Double maxDrp;

    /**
     * 最大降雨量站点编码
     */
    private String maxStcd;

    /**
     * 最大降雨量站点名称
     */
    private String maxStnm;


    public String getAddvcd() {
        return addvcd;
    }

    public void setAddvcd(String addvcd) {
        this.addvcd = addvcd;
    }

    public Integer getRainStationNum() {
        return rainStationNum;
    }

    public void setRainStationNum(Integer rainStationNum) {
        this.rainStationNum = rainStationNum;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    public Double getDrp() {
        return drp;
    }

    public void setDrp(Double drp) {
        this.drp = drp;
    }

    public Double getMaxDrp() {
        return maxDrp;
    }

    public void setMaxDrp(Double maxDrp) {
        this.maxDrp = maxDrp;
    }

    public String getMaxStcd() {
        return maxStcd;
    }

    public void setMaxStcd(String maxStcd) {
        this.maxStcd = maxStcd;
    }

    public String getMaxStnm() {
        return maxStnm;
    }

    public void setMaxStnm(String maxStnm) {
        this.maxStnm = maxStnm;
    }

    @Override
    public String toString() {
        return "AddvcdStationRainDTO{" +
                "addvcd='" + addvcd + '\'' +
                ", rainStationNum=" + rainStationNum +
                ", stationNum=" + stationNum +
                ", drp=" + drp +
                ", maxDrp=" + maxDrp +
                ", maxStcd='" + maxStcd + '\'' +
                ", maxStnm='" + maxStnm + '\'' +
                '}';
    }
}
