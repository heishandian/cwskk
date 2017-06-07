package com.yaoli.dao;

import java.util.List;

import com.yaoli.beans.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> getAllMenus();

    List<SysMenu> getMenusByUserId(int userId);
    
    /**
     * 根据roleid获取 所有的菜单
     * @param roleid
     * @return
     */
    List<SysMenu> getMenusByRoleId(int roleid);
}