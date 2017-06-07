package com.yaoli.common.obsoleted;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class SysPermissionJDBCRealm extends AuthorizingRealm {
	private static Logger logger = Logger.getLogger(SysPermissionJDBCRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("进入 SysPermissionJDBCRealm 查询是否授权");

		//throw new UnauthorizedException("没有相关权限");
		return new SimpleAuthenticationInfo("", "", getName());
	}

}
