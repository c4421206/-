package com.clouddo.fileserver.model;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>实体类</p>
 * <p>Table: cloud_file - 文件信息</p>
 * @author zhongming
 * @since 2018-06-22 03:07:43
 */
public class CloudFile implements Serializable  {

    private static final long serialVersionUID = -2364096417691740355L;
    /** ID - 文件主键 */
    private String id;

    /** FILENAME - 文件名 */
    private String filename;

    /** REAL_NAME - 真实文件名 */
    private String realName;

    /** HASH - 文件hash */
    private String hash;

    /** CREATE_TIME - 创建时间 */
    private Date createTime;

    /** UPDATE_TIME - 更新时间 */
    private Date updateTime;

    /** TYPE - 类型 */
    private String type;

    /** PATH - 文件路径 */
    private String path;

    /** DESCRIPTION - 描述 */
    private String description;


    public String getId(){
        return this.id; 
    }
    public void setId(String id){
        this.id = id; 
    }

    public String getFilename(){
        return this.filename;
    }
    public void setFilename(String filename){
        this.filename = filename;
    }

    public String getRealName(){
        return this.realName;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }

    public String getHash(){
        return this.hash;
    }
    public void setHash(String hash){
        this.hash = hash;
    }

    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}