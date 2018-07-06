package com.clouddo.hydrology.service.impl;


import com.clouddo.commons.common.util.UUIDGenerator;
import com.clouddo.hydrology.mapper.StStbprpBMapper;
import com.clouddo.hydrology.model.StStbprpBDO;
import com.clouddo.hydrology.pojo.dto.StStbprpBDTO;
import com.clouddo.hydrology.service.StStbprpBService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.charsming.test.service.stStbprpB.StStbprpBService;

/**
 * Service层 实现类，用于业务逻辑处理，事务控制等 
 * @author charsmingCodeGenerator 
 */
@Service
@Transactional
public class StStbprpBServiceImpl implements StStbprpBService {
	
	@Resource
	private StStbprpBMapper stStbprpBMapper;

    /**
     * 查询操作
     * @param parameterSet 参数
     * @return 查询结果
     */
    @Override
    public List<StStbprpBDO> findAll(Map<String, Object> parameterSet) {
        return this.stStbprpBMapper.findAll(parameterSet);
    }

    /**
    * 删除操作
    * @param deleteObjects 要删除的实体对象集合，内含对象主键信息
    */
    @Override
    public int delete(List<StStbprpBDO> deleteObjects) {
        int deleteNum = this.stStbprpBMapper.delete(deleteObjects);
        return deleteNum;
    }

    /**
    * 保存修改操作
    * @param object 要保存修改的实体
    * @return 结果
    */
    @Override
    public Map<String, Object> saveOrUpdate(StStbprpBDO object) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        int num = 0;
        if(object.getStcd() == null || "".equals(object.getStcd())) {
            object.setStcd(UUIDGenerator.getUUID());
            num = this.insert(object);
            returnData.put("message", "insert");
            returnData.put("number", num);
        } else {
            StStbprpBDO object1 = this.stStbprpBMapper.get(object);
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
    public int batchInsert(List<StStbprpBDO> insertObjects) {
        return this.stStbprpBMapper.batchInsert(insertObjects);
    }

    /**
    * 插入
    * @param object 要插入你的实体
    * @return 插入的记录数
    */
    @Override
    public int insert(StStbprpBDO object) {
    List<StStbprpBDO> objects = new ArrayList<StStbprpBDO>();
        objects.add(object);
        return this.batchInsert(objects);
    }

    /**
    * 更新操作
    * @param object 要更新的实体
    * @return 更新记录数
    */
    @Override
    public int update(StStbprpBDO object) {
    List<StStbprpBDO> objects = new ArrayList<StStbprpBDO>();
        objects.add(object);
        return this.batchUpdate(objects);
    }

    /**
    * 批量更新
    * @param objects 要更新的实体集合
    * @return 更新的记录数
    */
    @Override
    public int batchUpdate(List<StStbprpBDO> objects) {
        return this.stStbprpBMapper.batchUpdate(objects);
    }

    /**
    * 查询单个结果
    * @param object 内含要获取实体的ID信息
    * @return 实体信息
    */
    @Override
    public StStbprpBDO get(StStbprpBDO object) {
        return this.stStbprpBMapper.get(object);
    }

    /**
     * 统计站点降雨信息
     * @param parameterSet 参数
     * @return 站点降雨信息
     */
    @Override
    public List<StStbprpBDTO> countStationRain(Map<String, Object> parameterSet) {
        return this.stStbprpBMapper.countStationRain(parameterSet);
    }
}