package com.clouddo.hydrology.service.impl;


import com.clouddo.commons.common.util.CollectionUtil;
import com.clouddo.commons.common.util.UUIDGenerator;
import com.clouddo.hydrology.mapper.StAddvcdDMapper;
import com.clouddo.hydrology.model.StAddvcdDDO;
import com.clouddo.hydrology.service.StAddvcdDService;
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
public class StAddvcdDServiceImpl implements StAddvcdDService {
	
	@Resource
	private StAddvcdDMapper stAddvcdDMapper;

    /**
     * 查询操作
     * @param parameterSet 参数
     * @return 查询结果
     */
    @Override
    public List<StAddvcdDDO> findAll(Map<String, Object> parameterSet) {
        return this.stAddvcdDMapper.findAll(parameterSet);
    }

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    @Override
    public int delete(List<StAddvcdDDO> deleteObjects) {
        int deleteNum = this.stAddvcdDMapper.delete(deleteObjects);
        return deleteNum;
    }

    /**
    * 保存修改操作
    * @param object 要保存修改的实体
    * @return 结果
    */
    @Override
    public Map<String, Object> saveOrUpdate(StAddvcdDDO object) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        int num = 0;
        if(object.getAddvcd() == null || "".equals(object.getAddvcd())) {
            object.setAddvcd(UUIDGenerator.getUUID());
            num = this.insert(object);
            returnData.put("message", "insert");
            returnData.put("number", num);
        } else {
            StAddvcdDDO object1 = this.stAddvcdDMapper.get(object);
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
    public int batchInsert(List<StAddvcdDDO> insertObjects) {
        return this.stAddvcdDMapper.batchInsert(insertObjects);
    }

    /**
    * 插入
    * @param object 要插入你的实体
    * @return 插入的记录数
    */
    @Override
    public int insert(StAddvcdDDO object) {
    List<StAddvcdDDO> objects = new ArrayList<StAddvcdDDO>();
        objects.add(object);
        return this.batchInsert(objects);
    }

    /**
    * 更新操作
    * @param object 要更新的实体
    * @return 更新记录数
    */
    @Override
    public int update(StAddvcdDDO object) {
    List<StAddvcdDDO> objects = new ArrayList<StAddvcdDDO>();
        objects.add(object);
        return this.batchUpdate(objects);
    }

    /**
    * 批量更新
    * @param objects 要更新的实体集合
    * @return 更新的记录数
    */
    @Override
    public int batchUpdate(List<StAddvcdDDO> objects) {
        return this.stAddvcdDMapper.batchUpdate(objects);
    }

    /**
    * 查询单个结果
    * @param object 内含要获取实体的ID信息
    * @return 实体信息
    */
    @Override
    public StAddvcdDDO get(StAddvcdDDO object) {
        return this.stAddvcdDMapper.get(object);
    }


    /**
     * 统计各个行政区站点数
     * @param parameters 参数：addvcds
     * @return 行政区的站点数
     */
    @Override
    public Map<String, Object> countStationNum(Map<String, Object> parameters) {
        //获取数据
        return CollectionUtil.listToMap(this.stAddvcdDMapper.countStationNum(parameters), "ADDVCD");
    }


    /**
     * 统计各个行政区降雨站点信息
     * 1、降雨站点数
     * 2、平均降雨
     * @param parameters 参数：addvcds
     * @return 行政区的站点数
     */
    @Override
    public Map<String, Object> countStationRainNum(Map<String, Object> parameters) {
        return CollectionUtil.listToMap(this.stAddvcdDMapper.countStationRainNum(parameters), "ADDVCD");
    }

    /**
     * 查询最大降雨量站点信息
     * @param parameters 参数
     * @return
     */
    @Override
    public Map<String, Object> countMaxRainStation(Map<String, Object> parameters) {
        List<Map<String, Object>> data = this.stAddvcdDMapper.countMaxRainStation(parameters);
        return CollectionUtil.listToMap(data, "ADDVCD");
    }

    /**
     * 查询行政区雨量信息
     * 1、行政区站点数
     * 2、降雨站数
     * 3、平均降雨量
     * 4、最大降雨站点及降雨量
     * @param parameters 参数
     * @return 行政区雨量信息
     */
    @Override
    public List<Map<String, Object>> countAddvcdRain(Map<String, Object> parameters) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        //1、统计各个行政区站点数
        Map<String, Object> stationNumData = this.countStationNum(parameters);
        //2、统计各个行政区降雨站点信息
        Map<String, Object> stationRainNumData = this.countStationRainNum(parameters);
        //3、查询行政区雨量信息
        Map<String, Object> maxRainStationData = this.countMaxRainStation(parameters);
        //4、整合数据
        for(Map.Entry<String, Object> entry : stationNumData.entrySet()) {
            Map<String, Object> record = (Map<String, Object>) entry.getValue();
            record.putAll((Map<? extends String, ?>) stationRainNumData.get(entry.getKey()));
            record.putAll((Map<? extends String, ?>) maxRainStationData.get(entry.getKey()));
            result.add(record);
        }
        return result;
    }

}