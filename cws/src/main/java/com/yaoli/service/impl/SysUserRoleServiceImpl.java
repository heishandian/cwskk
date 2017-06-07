package com.yaoli.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.SysUserRole;
import com.yaoli.dao.SysUserRoleMapper;
import com.yaoli.service.ISysUserRoleService;

@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements ISysUserRoleService{
	
	@Resource
	SysUserRoleMapper sysUserRoleMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int count = sysUserRoleMapper.deleteByPrimaryKey(id);
		return count;
	}

	@Override
	public int insert(SysUserRole record) {
		int count =sysUserRoleMapper.insert(record);
		return count;
	}

	@Override
	public int deleteUserRoleByUserId(Integer userId) {
		int count = sysUserRoleMapper.deleteUserRoleByUserId(userId);
		return count;
	}


	
}
