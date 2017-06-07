package com.yaoli.vo;

import java.io.Serializable;
import java.util.List;


public class RoleForm implements Serializable{
	private static final long serialVersionUID = -8828047956866869515L;
	
	//插入角色时候用到的角色姓名
	public String name;
	
	//插入角色时 角色所关联到的菜单id
	public List<Integer> meunIds;
	
	//删除角色时 所保存的角色id
	public List<Integer> delRoleIds;
	
	
	public List<Integer> getDelRoleIds() {
		return delRoleIds;
	}
	public void setDelRoleIds(List<Integer> delRoleIds) {
		this.delRoleIds = delRoleIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer> getMeunIds() {
		return meunIds;
	}
	public void setMeunIds(List<Integer> meunIds) {
		this.meunIds = meunIds;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
