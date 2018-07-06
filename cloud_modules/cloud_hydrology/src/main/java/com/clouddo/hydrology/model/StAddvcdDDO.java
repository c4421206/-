package com.clouddo.hydrology.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>实体类</p>
 * <p>Table: st_addvcd_d - 行政区划信息</p>
 * @author zhongming
 * @since 1.0
 * 2018-07-04 03:59:55
 */
@Table(name="st_addvcd_d")
public class StAddvcdDDO implements Serializable  {

    /** ADDVCD - 行政区编码 */
    @Column(name = "ADDVCD")
    private String addvcd;

    /** ADDVNM - 行政区名称 */
    @Column(name = "ADDVNM")
    private String addvnm;

    /** COMMENTS -  */
    @Column(name = "COMMENTS")
    private String comments;

    /** MODITIME -  */
    @Column(name = "MODITIME")
    private Date moditime;

    /** SEQ - 序号 */
    @Column(name = "SEQ")
    private Integer seq;

    /** YZID -  */
    @Column(name = "YZID")
    private String yzid;

    /** ISYZ -  */
    @Column(name = "ISYZ")
    private String isyz;

    /** MARK - 备注 */
    @Column(name = "MARK")
    private String mark;


    public String getAddvcd(){
        return this.addvcd;
    }
    public void setAddvcd(String addvcd){
        this.addvcd = addvcd;
    }

    public String getAddvnm(){
        return this.addvnm;
    }
    public void setAddvnm(String addvnm){
        this.addvnm = addvnm;
    }

    public String getComments(){
        return this.comments;
    }
    public void setComments(String comments){
        this.comments = comments;
    }

    public Date getModitime(){
        return this.moditime;
    }
    public void setModitime(Date moditime){
        this.moditime = moditime;
    }

    public Integer getSeq(){
        return this.seq;
    }
    public void setSeq(Integer seq){
        this.seq = seq;
    }

    public String getYzid(){
        return this.yzid;
    }
    public void setYzid(String yzid){
        this.yzid = yzid;
    }

    public String getIsyz(){
        return this.isyz;
    }
    public void setIsyz(String isyz){
        this.isyz = isyz;
    }

    public String getMark(){
        return this.mark;
    }
    public void setMark(String mark){
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "StAddvcdDDO{" +
                "addvcd='" + addvcd + '\'' +
                ", addvnm='" + addvnm + '\'' +
                ", comments='" + comments + '\'' +
                ", moditime=" + moditime +
                ", seq=" + seq +
                ", yzid='" + yzid + '\'' +
                ", isyz='" + isyz + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}