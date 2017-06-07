package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysRoleMenu;

public interface ISysRoleService {
	
	public SysRole getRoleById(int id);
	
	public List<SysRole> getUserRolesByUserId(int userId);
	
	public List<SysRole> getAllRoles();
	
	public int insert(SysRole record);
	
	public int insertRoleMenu(SysRoleMenu record);
	
	public List<SysRole> getRolesByPaging(Map<String, String> map);
	
	public int getTotalCount();
	
	//获取角色绑定的用户数量
	public int getRoleBindingUserCount(int roleId);
	
	int deleteByPrimaryKey(Integer id);
	
	int deleteRoleMenuByRoleId(Integer roleid);
	
    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
