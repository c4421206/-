package com.clouddo.hydrology.mapper;


import com.clouddo.hydrology.model.StAddvcdDDO;

import java.util.List;
import java.util.Map;

/**
 * Dao层 接口类，用于持久化数据处理
 * @author zhongming
 * @since 1.0
 * 2018-07-04 03:59:55
 */
public interface StAddvcdDMapper {

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
	* 获取一个对象
	* @param object 实体内含实体主键
	* @return 实体对象
	*/
	StAddvcdDDO get(StAddvcdDDO object);

	/**
	* 批量插入操作
	* @param insertObjects 要插入的实体集合
	* @return 插入的记录数
	*/
	int batchInsert(List<StAddvcdDDO> insertObjects);

	/**
	* 更新操作
	* @param objects 要更新的尸体
	* @return
	*/
	int batchUpdate(List<StAddvcdDDO> objects);

	/**
	 * 统计各个行政区站点数
	 * @param parameters 参数：addvcds
	 * @return 行政区的站点数
	 */
	List<Map<String, Object>> countStationNum(Map<String, Object> parameters);

	/**
	 * 统计各个行政区降雨站点信息
	 * 1、降雨站点数
	 * 2、平均降雨
	 * @param parameters 参数：addvcds
	 * @return 行政区的站点数
	 */
	List<Map<String, Object>> countStationRainNum(Map<String, Object> parameters);

	/**
	 * 查询最大降雨量站点信息
	 * @param parameters 参数
	 * @return
	 */
	List<Map<String, Object>> countMaxRainStation(Map<String, Object> parameters);
}