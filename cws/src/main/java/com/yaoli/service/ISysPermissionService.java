package com.yaoli.service;

import java.util.List;

import com.yaoli.beans.SysPermission;

public interface ISysPermissionService {
	public List<SysPermission> getPermissionsByUserId(int userId);
	
	public List<SysPermission> getPermissionsByRoleId(int roleId);
}
