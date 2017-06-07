package com.yaoli.service;

import java.util.List;

import com.yaoli.beans.SysMenu;

public interface ISysMenusService {
	
	List<SysMenu> getMenusByUserId(int userId);
	
	//获取所有菜单
	List<SysMenu> getAllMenus();
	
	List<SysMenu> getMenusByRoleId(int roleid);
}
