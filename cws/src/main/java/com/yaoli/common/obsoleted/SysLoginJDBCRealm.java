package com.yaoli.common.obsoleted;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.yaoli.beans.SysUser;
import com.yaoli.dao.SysUserMapper;

/**
 * 
 * @author will
 * 
 */
@Component
public class SysLoginJDBCRealm extends JdbcRealm {

	@Override
	public String getName() {
		return "SysLoginJDBCRealm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UserNamePasswordToken类型的认证
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		// 用户名
		String loginname = (String) token.getPrincipal();
		// 密码
		String loginpwd = new String((char[]) token.getCredentials());
		// 获取SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = SysSpringContextUtil.getSessionFactory();
		// 打开session
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);

			SysUser user = sysUserMapper.getSysUserByLoginName(loginname);

			if (user == null) {
				throw new UnknownAccountException("用户不存在");
				
			} else if (!user.getLoginpwd().equals(loginpwd)) {
				throw new IllegalArgumentException("密码错误");
				
			}
		} finally {
			sqlSession.close();
		}

		return new SimpleAuthenticationInfo(loginname, loginpwd, getName());
	}
}
