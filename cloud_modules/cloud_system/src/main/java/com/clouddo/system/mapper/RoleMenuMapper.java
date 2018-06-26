package com.clouddo.system.mapper;

import com.clouddo.system.model.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * @author zhongming
 * @since 3.0
 * 2018/5/4下午5:26
 */
@Mapper
public interface RoleMenuMapper {
    RoleMenu get(Long id);

    List<RoleMenu> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleMenu roleMenu);

    int update(RoleMenu roleMenu);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listMenuIdByRoleId(Long roleId);

    int removeByRoleId(Long roleId);

    int removeByMenuId(Long menuId);

    int batchSave(List<RoleMenu> list);
}
