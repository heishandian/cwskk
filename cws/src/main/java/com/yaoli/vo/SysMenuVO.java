package com.yaoli.vo;

import java.io.Serializable;

/**
 * 用于返回菜单的数据格式
 * @author will
 *
 */
public class SysMenuVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481362782540201858L;
	
	
	public int level;
	
	public String name;
	
	public String ico;
	
	public String link;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
}
