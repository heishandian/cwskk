package com.yaoli.dao;

import java.util.List;

import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysUserRole;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
    
    public List<SysRole> getUserRolesByUserId(int userId);

	public int deleteUserRoleByUserId(Integer userId);
    
}