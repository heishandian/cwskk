package com.yaoli.dao;

import java.util.List;

import com.yaoli.beans.SysPermission;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
    
	public List<SysPermission> getPermissionsByUserId(int userId);
	
	public List<SysPermission> getPermissionsByRoleId(int roleId);
}