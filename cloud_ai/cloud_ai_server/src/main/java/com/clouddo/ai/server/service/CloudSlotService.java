package com.clouddo.ai.server.service;


import com.clouddo.ai.server.model.CloudSlotDO;
import com.clouddo.ai.server.voice.assistant.model.baidu.response.Slot;

import java.util.List;
import java.util.Map;

/**
 * Service层 接口类，用于业务逻辑处理，事务控制等 
 * @author charsmingCodeGenerator 
 */
public interface CloudSlotService {

    /**
     * 查询所有
     * @param parameterSet 参数
     * @return 结果
     */
    List<CloudSlotDO> list(Map<String, Object> parameterSet);

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    int delete(List<CloudSlotDO> deleteObjects);

    /**
    * 保存修改操作
    * @param object 要保存修改的实体
    * @return 结果
    */
    Map<String,Object> saveOrUpdate(CloudSlotDO object);

    /**
    * 插入操作
    * @param insertObjects 要插入的实体集合
    * @return 插入的记录数
    */
    int batchInsert(List<CloudSlotDO> insertObjects);
    int insert(CloudSlotDO object);

    /**
    * 更新操作
    * @param object 要更新的实体
    * @return 更新记录数
    */
    int update(CloudSlotDO object);

    /**
    * 批量更新
    * @param objects 要更新的实体集合
    * @return 更新的记录数
    */
    int batchUpdate(List<CloudSlotDO> objects);

    /**
    * 查询单个结果
    * @param object 内含要获取实体的ID信息
    * @return 实体信息
    */
    CloudSlotDO get(CloudSlotDO object);

    /**
     * 使用从百度UNIT获取词槽信息和从数据库获取的词槽信息创建词槽数据传输对象
     * @param solt 从百度UNIT获取的词槽信息
     * @param cloudSlotDO 从数据库获取的词槽信息
     * @return 词槽数据传输对象
     */
    Map<String, Object> createSoltDto(Slot solt, CloudSlotDO cloudSlotDO);
	
}