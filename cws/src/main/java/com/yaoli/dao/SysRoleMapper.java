package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    public List<SysRole> getUserRolesByUserId(int userId) ;
    
    public List<SysRole> getAllRoles();
    
    public List<SysRole> getRolesByPaging(Map<String, String> map);
    
    public int getTotalCount();
    
	public int getRoleBindingUserCount(int roleId);
	
	public int deleteRoleMenuByRoleId(Integer roleid);

    
}