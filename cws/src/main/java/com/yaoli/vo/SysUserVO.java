package com.yaoli.vo;

import java.util.List;

import com.yaoli.beans.SysUser;

public class SysUserVO extends SysUser{
	//登录名
	public String loginusername;
	
	public String loginuserpwd;
	
	//登录名验证码
	public String logincode;
	
	//用户的角色名称
	public String rolename;
	
	//用户角色id
	public int roleid;
	
	//保存选中的用户id
	public List<Integer> selectIds;
	
	//重置所有用户的密码 标志 为 yes时，重置所有人密码
	public String resetAllUserPwdFlag;
	
	public String getResetAllUserPwdFlag() {
		return resetAllUserPwdFlag;
	}

	public void setResetAllUserPwdFlag(String resetAllUserPwdFlag) {
		this.resetAllUserPwdFlag = resetAllUserPwdFlag;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public List<Integer> getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(List<Integer> selectIds) {
		this.selectIds = selectIds;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getLoginusername() {
		return loginusername;
	}

	public void setLoginusername(String loginusername) {
		this.loginusername = loginusername;
	}

	public String getLoginuserpwd() {
		return loginuserpwd;
	}

	public void setLoginuserpwd(String loginuserpwd) {
		this.loginuserpwd = loginuserpwd;
	}

	public String getLogincode() {
		return logincode;
	}

	public void setLogincode(String logincode) {
		this.logincode = logincode;
	}
	
}
