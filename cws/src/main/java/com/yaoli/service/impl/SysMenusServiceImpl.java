package com.yaoli.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.SysMenu;
import com.yaoli.dao.SysMenuMapper;
import com.yaoli.service.ISysMenusService;

@Service("sysMenusServiceImpl")
public class SysMenusServiceImpl implements ISysMenusService{
	
	@Resource
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysMenu> getMenusByUserId(int userId) {
		List<SysMenu> sysMenus = sysMenuMapper.getMenusByUserId(userId);
		return sysMenus;
	}

	@Override
	public List<SysMenu> getAllMenus() {
		List<SysMenu> sysMenus = sysMenuMapper.getAllMenus();
		return sysMenus;
	}

	@Override
	public List<SysMenu> getMenusByRoleId(int roleid) {
		return sysMenuMapper.getMenusByRoleId(roleid);
	}
	
	
	

}
