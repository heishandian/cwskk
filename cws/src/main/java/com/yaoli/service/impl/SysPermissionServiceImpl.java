package com.yaoli.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.SysPermission;
import com.yaoli.dao.SysPermissionMapper;
import com.yaoli.service.ISysPermissionService;

@Service("sysPermissionService")
public class SysPermissionServiceImpl implements ISysPermissionService {

	@Resource
	private SysPermissionMapper sysPermissionMapper;

	@Override
	public List<SysPermission> getPermissionsByUserId(int userId) {
		List<SysPermission> permissions = sysPermissionMapper.getPermissionsByUserId(userId);
		return permissions;
	}

	@Override
	public List<SysPermission> getPermissionsByRoleId(int roleId) {
		List<SysPermission> permissions = sysPermissionMapper.getPermissionsByUserId(roleId);
		return permissions;
	}

}
