package com.clouddo.fileserver.service.impl;


import com.clouddo.commons.common.util.UUIDGenerator;
import com.clouddo.fileserver.mapper.CloudFileMapper;
import com.clouddo.fileserver.model.CloudFile;
import com.clouddo.fileserver.service.CloudFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service层 实现类，用于业务逻辑处理，事务控制等 
 * @author charsmingCodeGenerator 
 */
@Service
@Transactional
public class CloudFileServiceImpl implements CloudFileService {
	
	@Resource
	private CloudFileMapper cloudFileMapper;

    /**
     * 查询操作
     * @param parameterSet 参数
     * @return 查询结果
     */
    @Override
    public List<CloudFile> list(Map<String, Object> parameterSet) {
        return this.cloudFileMapper.list(parameterSet);
    }

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    @Override
    public int delete(List<CloudFile> deleteObjects) {
        int deleteNum = this.cloudFileMapper.delete(deleteObjects);
        return deleteNum;
    }

    /**
    * 保存修改操作
    * @param object 要保存修改的实体
    * @return 结果
    */
    @Override
    public Map<String, Object> saveOrUpdate(CloudFile object) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        int num = 0;
        if(object.getId() == null || "".equals(object.getId())) {
            object.setId(UUIDGenerator.getUUID());
            num = this.insert(object);
            returnData.put("message", "insert");
            returnData.put("number", num);
        } else {
            CloudFile object1 = this.cloudFileMapper.get(object);
            if(object1 == null) {
                num = this.insert(object);
                returnData.put("message", "insert");
                returnData.put("number", num);
            } else {
                num = this.update(object);
                returnData.put("message", "update");
                returnData.put("number", num);
            }
        }
        return returnData;
    }

    /**
    * 批量插入
    * @param insertObjects 要插入的实体集合
    * @return 插入的记录数
    */
    @Override
    public int batchInsert(List<CloudFile> insertObjects) {
        return this.cloudFileMapper.batchInsert(insertObjects);
    }

    /**
    * 插入
    * @param object 要插入你的实体
    * @return 插入的记录数
    */
    @Override
    public int insert(CloudFile object) {
    List<CloudFile> objects = new ArrayList<CloudFile>();
        objects.add(object);
        return this.batchInsert(objects);
    }

    /**
    * 更新操作
    * @param object 要更新的实体
    * @return 更新记录数
    */
    @Override
    public int update(CloudFile object) {
    List<CloudFile> objects = new ArrayList<CloudFile>();
        objects.add(object);
        return this.batchUpdate(objects);
    }

    /**
    * 批量更新
    * @param objects 要更新的实体集合
    * @return 更新的记录数
    */
    @Override
    public int batchUpdate(List<CloudFile> objects) {
        return this.cloudFileMapper.batchUpdate(objects);
    }

    /**
    * 查询单个结果
    * @param object 内含要获取实体的ID信息
    * @return 实体信息
    */
    @Override
    public CloudFile get(CloudFile object) {
        return this.cloudFileMapper.get(object);
    }

    /**
     * 查询单个结果
     * @param id 文件的ID
     * @return 实体信息
     */
    @Override
    public CloudFile get(String id) {
        CloudFile cloudFile = new CloudFile();
        cloudFile.setId(id);
        return this.get(cloudFile);
    }
}