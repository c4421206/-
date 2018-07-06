package com.clouddo.hydrology.service;


import com.clouddo.hydrology.model.StAddvcdDDO;

import java.util.List;
import java.util.Map;

/**
 * Service层 接口类，用于业务逻辑处理，事务控制等 
 * @author charsmingCodeGenerator
 * @since 1.0
 */
public interface StAddvcdDService {

    /**
     * 查询所有
     * @param parameterSet 参数
     * @return 结果
     */
    List<StAddvcdDDO> findAll(Map<String, Object> parameterSet);

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    int delete(List<StAddvcdDDO> deleteObjects);

    /**
    * 保存修改操作
    * @param object 要保存修改的实体
    * @return 结果
    */
    Map<String,Object> saveOrUpdate(StAddvcdDDO object);

    /**
    * 插入操作
    * @param insertObjects 要插入的实体集合
    * @return 插入的记录数
    */
    int batchInsert(List<StAddvcdDDO> insertObjects);
    int insert(StAddvcdDDO object);

    /**
    * 更新操作
    * @param object 要更新的实体
    * @return 更新记录数
    */
    int update(StAddvcdDDO object);

    /**
    * 批量更新
    * @param objects 要更新的实体集合
    * @return 更新的记录数
    */
    int batchUpdate(List<StAddvcdDDO> objects);

    /**
    * 查询单个结果
    * @param object 内含要获取实体的ID信息
    * @return 实体信息
    */
    StAddvcdDDO get(StAddvcdDDO object);

    /**
     * 统计各个行政区站点数
     * @param parameters 参数：addvcds
     * @return 行政区的站点数
     */
    Map<String, Object> countStationNum(Map<String, Object> parameters);

    /**
     * 统计各个行政区降雨站点信息
     * 1、降雨站点数
     * 2、平均降雨
     * @param parameters 参数：addvcds
     * @return 行政区降雨站点信息
     */
    Map<String, Object> countStationRainNum(Map<String, Object> parameters);

    /**
     * 查询最大降雨量站点信息
     * @param parameters 参数
     * @return
     */
    Map<String, Object> countMaxRainStation(Map<String, Object> parameters);

    /**
     * 查询行政区雨量信息
     * 1、行政区站点数
     * 2、降雨站数
     * 3、平均降雨量
     * 4、最大降雨站点及降雨量
     * @param parameters 参数
     * @return 行政区雨量信息
     */
    List<Map<String, Object>> countAddvcdRain(Map<String, Object> parameters);

}