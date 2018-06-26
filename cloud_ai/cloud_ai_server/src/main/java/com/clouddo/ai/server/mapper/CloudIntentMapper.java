package com.clouddo.ai.server.mapper;


import com.clouddo.ai.server.model.CloudIntentDO;

import java.util.List;
import java.util.Map;

/**
 * Dao层 接口类，用于持久化数据处理
 * @author charsmingCodeGenerator
 */
public interface CloudIntentMapper {

	/**
     * 查询所有
     * @param parameterSet 参数
     * @return 结果
     */
    List<CloudIntentDO> list(Map<String, Object> parameterSet);

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    int delete(List<CloudIntentDO> deleteObjects);


	/**
	* 获取一个对象
	* @param object 实体内含实体主键
	* @return 实体对象
	*/
	CloudIntentDO get(CloudIntentDO object);

	/**
	* 批量插入操作
	* @param insertObjects 要插入的实体集合
	* @return 插入的记录数
	*/
	int batchInsert(List<CloudIntentDO> insertObjects);

	/**
	* 更新操作
	* @param objects 要更新的实体
	* @return
	*/
	int batchUpdate(List<CloudIntentDO> objects);

	/**
	 * 查询意图信息和意图对应的词条信息
	 * @param cloudIntent 意图信息，内含意图ID
	 * @return 意图信息
	 */
	CloudIntentDO getWithSolts(CloudIntentDO cloudIntent);
}