package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysRoleMenu;
import com.yaoli.dao.SysRoleMapper;
import com.yaoli.dao.SysRoleMenuMapper;
import com.yaoli.service.ISysRoleService;


@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService{
	
	@Resource
	private SysRoleMapper rolesMapper;
	
	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	public SysRole getRoleById(int id) {
		return rolesMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysRole> getUserRolesByUserId(int userId) {
		List<SysRole> rolesList = rolesMapper.getUserRolesByUserId(userId);
		return rolesList;
	}

	@Override
	public List<SysRole> getAllRoles() {
		List<SysRole> roles  = rolesMapper.getAllRoles();
		return roles;
	}

	@Override
	public int insert(SysRole record) {
		int count = rolesMapper.insert(record);
		return count;
	}

	@Override
	public int insertRoleMenu(SysRoleMenu record) {
		int count = sysRoleMenuMapper.insert(record);
		return count;
	}

	@Override
	public List<SysRole> getRolesByPaging(Map<String, String> map) {
		List<SysRole> list = rolesMapper.getRolesByPaging(map);
		return list;
	}

	@Override
	public int getTotalCount() {
		int count = rolesMapper.getTotalCount();
		return count;
	}

	@Override
	public int getRoleBindingUserCount(int roleId) {
		int count = rolesMapper.getRoleBindingUserCount(roleId);
		return count;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int count = rolesMapper.deleteByPrimaryKey(id);
		return count;
	}

	@Override
	public int deleteRoleMenuByRoleId(Integer roleid) {
		int count = rolesMapper.deleteRoleMenuByRoleId(roleid);
		return count;
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole record) {
		return rolesMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		return rolesMapper.updateByPrimaryKey(record);
	}


}
