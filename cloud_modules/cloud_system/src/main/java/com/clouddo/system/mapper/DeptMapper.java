package com.clouddo.system.mapper;

import com.clouddo.system.model.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/15下午5:06
 */
@Mapper
public interface DeptMapper {

    Dept get(Long deptId);

    List<Dept> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Dept dept);

    int update(Dept dept);

    int remove(Long deptId);

    int batchRemove(Long[] deptIds);

    Long[] listParentDept();

    int getDeptUserNumber(Long deptId);
}
