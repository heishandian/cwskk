package com.yaoli.innovation;

import java.util.ArrayList;
import java.util.List;


public class MenuNode extends Menu{
	
	private List<Menu> submenus = new ArrayList<Menu>();

	@Override
	public void addMenu(Menu menu) {
		this.submenus.add(menu);
	}

	@Override
	public List<Menu> getChildren() {
		return submenus;
	}
	
}
