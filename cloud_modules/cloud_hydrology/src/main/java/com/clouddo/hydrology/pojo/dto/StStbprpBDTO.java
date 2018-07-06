package com.clouddo.hydrology.pojo.dto;

import com.clouddo.hydrology.model.StStbprpBDO;

/**
 * 站点雨量信息
 * @author zhongming
 * @since 1.0
 * 2018/7/5下午4:45
 */
public class StStbprpBDTO extends StStbprpBDO {


    private static final long serialVersionUID = 2025154549510556200L;

    private Double drp;

    public Double getDrp() {
        return drp;
    }

    public void setDrp(Double drp) {
        this.drp = drp;
    }
}
