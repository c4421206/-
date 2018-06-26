package com.clouddo.system.model;

import java.io.Serializable;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/4下午5:27
 */
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -6656361459804066869L;
    private Long id;
    private Long  roleId;
    private Long menuId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenu{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }



}
