package com.clouddo.hydrology.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>实体类</p>
 * <p>Table: ST_STBPRP_B - 测站基本信息表</p>
 * @author zhongming`
 * @since 2018-07-05 04:17:18
 */
@Table(name="ST_STBPRP_B")
public class StStbprpBDO implements Serializable  {

    private static final long serialVersionUID = 8821160786186367940L;
    /** STCD - 测站编码 */
	@Column(name = "STCD")
    private String stcd;

    /** STTP - 站类 */
	@Column(name = "STTP")
    private String sttp;

    /** ISYZ -  */
	@Column(name = "ISYZ")
    private String isyz;

    /** STNM - 测站名称 */
    @Column(name = "STNM")
    private String stnm;

    /** RVNM -  */
    @Column(name = "RVNM")
    private String rvnm;

    /** HNNM -  */
    @Column(name = "HNNM")
    private String hnnm;

    /** BSNM -  */
    @Column(name = "BSNM")
    private String bsnm;

    /** LGTD -  */
    @Column(name = "LGTD")
    private BigDecimal lgtd;

    /** LTTD -  */
    @Column(name = "LTTD")
    private BigDecimal lttd;

    /** STLC -  */
    @Column(name = "STLC")
    private String stlc;

    /** ADDVCD -  */
    @Column(name = "ADDVCD")
    private String addvcd;

    /** DTMNM -  */
    @Column(name = "DTMNM")
    private String dtmnm;

    /** DTMEL -  */
    @Column(name = "DTMEL")
    private BigDecimal dtmel;

    /** DTPR -  */
    @Column(name = "DTPR")
    private BigDecimal dtpr;

    /** FRGRD -  */
    @Column(name = "FRGRD")
    private String frgrd;

    /** ESSTYM -  */
    @Column(name = "ESSTYM")
    private String esstym;

    /** BGFRYM -  */
    @Column(name = "BGFRYM")
    private String bgfrym;

    /** ATCUNIT -  */
    @Column(name = "ATCUNIT")
    private String atcunit;

    /** ADMAUTH -  */
    @Column(name = "ADMAUTH")
    private String admauth;

    /** LOCALITY -  */
    @Column(name = "LOCALITY")
    private String locality;

    /** STBK -  */
    @Column(name = "STBK")
    private String stbk;

    /** STAZT -  */
    @Column(name = "STAZT")
    private Double stazt;

    /** DSTRVM -  */
    @Column(name = "DSTRVM")
    private Double dstrvm;

    /** DRNA -  */
    @Column(name = "DRNA")
    private Double drna;

    /** PHCD -  */
    @Column(name = "PHCD")
    private String phcd;

    /** USFL -  */
    @Column(name = "USFL")
    private String usfl;

    /** COMMENTS -  */
    @Column(name = "COMMENTS")
    private String comments;

    /** MODITIME -  */
    @Column(name = "MODITIME")
    private Date moditime;

    /** EXNAME -  */
    @Column(name = "EXNAME")
    private String exname;

    /** STATUS -  */
    @Column(name = "STATUS")
    private String status;

    /** GISFL -  */
    @Column(name = "GISFL")
    private String gisfl;

    /** CONSTCD -  */
    @Column(name = "CONSTCD")
    private String constcd;

    /** WATAREA -  */
    @Column(name = "WATAREA")
    private String watarea;

    /** STBEL -  */
    @Column(name = "STBEL")
    private String stbel;

    /** REPFL -  */
    @Column(name = "REPFL")
    private String repfl;

    /** SEQ -  */
    @Column(name = "SEQ")
    private Integer seq;

    /** PHOFL -  */
    @Column(name = "PHOFL")
    private String phofl;

    /** STCD5 -  */
    @Column(name = "STCD5")
    private String stcd5;

    /** ZXFL -  */
    @Column(name = "ZXFL")
    private String zxfl;

    /** YZID -  */
    @Column(name = "YZID")
    private String yzid;

    /** ISCS -  */
    @Column(name = "ISCS")
    private Boolean iscs;


    public String getStcd(){
        return this.stcd; 
    }
    public void setStcd(String stcd){
        this.stcd = stcd; 
    }

    public String getSttp(){
        return this.sttp; 
    }
    public void setSttp(String sttp){
        this.sttp = sttp; 
    }

    public String getIsyz(){
        return this.isyz; 
    }
    public void setIsyz(String isyz){
        this.isyz = isyz; 
    }

    public String getStnm(){
        return this.stnm;
    }
    public void setStnm(String stnm){
        this.stnm = stnm;
    }

    public String getRvnm(){
        return this.rvnm;
    }
    public void setRvnm(String rvnm){
        this.rvnm = rvnm;
    }

    public String getHnnm(){
        return this.hnnm;
    }
    public void setHnnm(String hnnm){
        this.hnnm = hnnm;
    }

    public String getBsnm(){
        return this.bsnm;
    }
    public void setBsnm(String bsnm){
        this.bsnm = bsnm;
    }

    public BigDecimal getLgtd(){
        return this.lgtd;
    }
    public void setLgtd(BigDecimal lgtd){
        this.lgtd = lgtd;
    }

    public BigDecimal getLttd(){
        return this.lttd;
    }
    public void setLttd(BigDecimal lttd){
        this.lttd = lttd;
    }

    public String getStlc(){
        return this.stlc;
    }
    public void setStlc(String stlc){
        this.stlc = stlc;
    }

    public String getAddvcd(){
        return this.addvcd;
    }
    public void setAddvcd(String addvcd){
        this.addvcd = addvcd;
    }

    public String getDtmnm(){
        return this.dtmnm;
    }
    public void setDtmnm(String dtmnm){
        this.dtmnm = dtmnm;
    }

    public BigDecimal getDtmel(){
        return this.dtmel;
    }
    public void setDtmel(BigDecimal dtmel){
        this.dtmel = dtmel;
    }

    public BigDecimal getDtpr(){
        return this.dtpr;
    }
    public void setDtpr(BigDecimal dtpr){
        this.dtpr = dtpr;
    }

    public String getFrgrd(){
        return this.frgrd;
    }
    public void setFrgrd(String frgrd){
        this.frgrd = frgrd;
    }

    public String getEsstym(){
        return this.esstym;
    }
    public void setEsstym(String esstym){
        this.esstym = esstym;
    }

    public String getBgfrym(){
        return this.bgfrym;
    }
    public void setBgfrym(String bgfrym){
        this.bgfrym = bgfrym;
    }

    public String getAtcunit(){
        return this.atcunit;
    }
    public void setAtcunit(String atcunit){
        this.atcunit = atcunit;
    }

    public String getAdmauth(){
        return this.admauth;
    }
    public void setAdmauth(String admauth){
        this.admauth = admauth;
    }

    public String getLocality(){
        return this.locality;
    }
    public void setLocality(String locality){
        this.locality = locality;
    }

    public String getStbk(){
        return this.stbk;
    }
    public void setStbk(String stbk){
        this.stbk = stbk;
    }

    public Double getStazt(){
        return this.stazt;
    }
    public void setStazt(Double stazt){
        this.stazt = stazt;
    }

    public Double getDstrvm(){
        return this.dstrvm;
    }
    public void setDstrvm(Double dstrvm){
        this.dstrvm = dstrvm;
    }

    public Double getDrna(){
        return this.drna;
    }
    public void setDrna(Double drna){
        this.drna = drna;
    }

    public String getPhcd(){
        return this.phcd;
    }
    public void setPhcd(String phcd){
        this.phcd = phcd;
    }

    public String getUsfl(){
        return this.usfl;
    }
    public void setUsfl(String usfl){
        this.usfl = usfl;
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

    public String getExname(){
        return this.exname;
    }
    public void setExname(String exname){
        this.exname = exname;
    }

    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public String getGisfl(){
        return this.gisfl;
    }
    public void setGisfl(String gisfl){
        this.gisfl = gisfl;
    }

    public String getConstcd(){
        return this.constcd;
    }
    public void setConstcd(String constcd){
        this.constcd = constcd;
    }

    public String getWatarea(){
        return this.watarea;
    }
    public void setWatarea(String watarea){
        this.watarea = watarea;
    }

    public String getStbel(){
        return this.stbel;
    }
    public void setStbel(String stbel){
        this.stbel = stbel;
    }

    public String getRepfl(){
        return this.repfl;
    }
    public void setRepfl(String repfl){
        this.repfl = repfl;
    }

    public Integer getSeq(){
        return this.seq;
    }
    public void setSeq(Integer seq){
        this.seq = seq;
    }

    public String getPhofl(){
        return this.phofl;
    }
    public void setPhofl(String phofl){
        this.phofl = phofl;
    }

    public String getStcd5(){
        return this.stcd5;
    }
    public void setStcd5(String stcd5){
        this.stcd5 = stcd5;
    }

    public String getZxfl(){
        return this.zxfl;
    }
    public void setZxfl(String zxfl){
        this.zxfl = zxfl;
    }

    public String getYzid(){
        return this.yzid;
    }
    public void setYzid(String yzid){
        this.yzid = yzid;
    }

    public Boolean getIscs(){
        return this.iscs;
    }
    public void setIscs(Boolean iscs){
        this.iscs = iscs;
    }


    @Override
    public String toString() {
        return "StStbprpBDO{" +
                "stcd='" + stcd + '\'' +
                ", sttp='" + sttp + '\'' +
                ", isyz='" + isyz + '\'' +
                ", stnm='" + stnm + '\'' +
                ", rvnm='" + rvnm + '\'' +
                ", hnnm='" + hnnm + '\'' +
                ", bsnm='" + bsnm + '\'' +
                ", lgtd=" + lgtd +
                ", lttd=" + lttd +
                ", stlc='" + stlc + '\'' +
                ", addvcd='" + addvcd + '\'' +
                ", dtmnm='" + dtmnm + '\'' +
                ", dtmel=" + dtmel +
                ", dtpr=" + dtpr +
                ", frgrd='" + frgrd + '\'' +
                ", esstym='" + esstym + '\'' +
                ", bgfrym='" + bgfrym + '\'' +
                ", atcunit='" + atcunit + '\'' +
                ", admauth='" + admauth + '\'' +
                ", locality='" + locality + '\'' +
                ", stbk='" + stbk + '\'' +
                ", stazt=" + stazt +
                ", dstrvm=" + dstrvm +
                ", drna=" + drna +
                ", phcd='" + phcd + '\'' +
                ", usfl='" + usfl + '\'' +
                ", comments='" + comments + '\'' +
                ", moditime=" + moditime +
                ", exname='" + exname + '\'' +
                ", status='" + status + '\'' +
                ", gisfl='" + gisfl + '\'' +
                ", constcd='" + constcd + '\'' +
                ", watarea='" + watarea + '\'' +
                ", stbel='" + stbel + '\'' +
                ", repfl='" + repfl + '\'' +
                ", seq=" + seq +
                ", phofl='" + phofl + '\'' +
                ", stcd5='" + stcd5 + '\'' +
                ", zxfl='" + zxfl + '\'' +
                ", yzid='" + yzid + '\'' +
                ", iscs=" + iscs +
                '}';
    }
}