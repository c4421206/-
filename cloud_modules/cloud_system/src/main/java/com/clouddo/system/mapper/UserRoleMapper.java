package com.clouddo.system.mapper;

import com.clouddo.system.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserRoleMapper {

	UserRole get(Long id);

	List<UserRole> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRole userRole);

	int update(UserRole userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int removeByRoleId(Long roleId);

	int batchSave(List<UserRole> list);

	int batchRemoveByUserId(Long[] ids);
}
