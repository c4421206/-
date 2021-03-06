package com.clouddo.hydrology.service;


import com.clouddo.hydrology.model.StStbprpBDO;
import com.clouddo.hydrology.pojo.dto.StStbprpBDTO;

import java.util.List;
import java.util.Map;

/**
 * Service层 接口类，用于业务逻辑处理，事务控制等 
 * @author charsmingCodeGenerator 
 */
public interface StStbprpBService {

    /**
     * 查询所有
     * @param parameterSet 参数
     * @return 结果
     */
    List<StStbprpBDO> findAll(Map<String, Object> parameterSet);

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    int delete(List<StStbprpBDO> deleteObjects);

    /**
    * 保存修改操作
    * @param object 要保存修改的实体
    * @return 结果
    */
    Map<String,Object> saveOrUpdate(StStbprpBDO object);

    /**
    * 插入操作
    * @param insertObjects 要插入的实体集合
    * @return 插入的记录数
    */
    int batchInsert(List<StStbprpBDO> insertObjects);
    int insert(StStbprpBDO object);

    /**
    * 更新操作
    * @param object 要更新的实体
    * @return 更新记录数
    */
    int update(StStbprpBDO object);

    /**
    * 批量更新
    * @param objects 要更新的实体集合
    * @return 更新的记录数
    */
    int batchUpdate(List<StStbprpBDO> objects);

    /**
    * 查询单个结果
    * @param object 内含要获取实体的ID信息
    * @return 实体信息
    */
    StStbprpBDO get(StStbprpBDO object);

    /**
     * 统计站点降雨信息
     * @param parameterSet 参数
     * @return 站点降雨信息
     */
    List<StStbprpBDTO> countStationRain(Map<String, Object> parameterSet);
	
}