package com.yaoli.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;

public class SysJDBCRealm extends AuthorizingRealm {

	// 获取用户的所有的角色
	private String getUserRolesByUserId = "SELECT dbo.sysrole.id FROM sysrole ,sysuserrole,sysuser WHERE sysuser.id = sysuserrole.userid AND sysuserrole.roleid = sysrole.id AND sysuser.loginname = ?";

	// 获取用户的所有的权限
	private String getUserPermissionByUserId = "SELECT syspermission.name FROM syspermission,sysrolepermission WHERE sysrolepermission.permissionid = syspermission.id AND sysrolepermission.roleid = ?";

	// 获取用户的密码
	private String getUserPasswordByUserId = "SELECT dbo.sysuser.loginpwd FROM dbo.sysuser WHERE dbo.sysuser.loginname = ?";

	public DataSource dataSource;

	public boolean permissionsLookupEnabled = false;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean isPermissionsLookupEnabled() {
		return permissionsLookupEnabled;
	}

	public void setPermissionsLookupEnabled(boolean permissionsLookupEnabled) {
		this.permissionsLookupEnabled = permissionsLookupEnabled;
	}

	// 一次获取权限集合，只有退出之后才会重新获取
	public Set<String> permissionSet = null;

	// 一次获取角色集合，只有退出之后才会重新获取
	public Set<String> roleSet = null;

	/**
	 * 用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}

		String username = (String) getAvailablePrincipal(principals);

		Connection conn = null;
		// Set<String> roleNames = null;
		// Set<String> permissions = null;
		try {
			conn = dataSource.getConnection();

			// 获取用户的角色名，保证不重复。
			//if (roleSet == null) {
				roleSet = getRoleNamesForUser(conn, username);
			//}

			// 权限查找是否开启
			if (permissionsLookupEnabled) {
				//if (permissionSet == null) {
					permissionSet = getPermissions(conn, username, roleSet);
				//}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(conn);
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleSet);
		info.setStringPermissions(permissionSet);
		return info;
	}

	/**
	 * 验证用户名和密码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;

		String username = userToken.getUsername();
		String password = new String(userToken.getPassword());

		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}

		Connection conn = null;
		SimpleAuthenticationInfo info = null;

		try {
			conn = dataSource.getConnection();

			// 从数据库中查询密码
			String queryPassword = getPasswordForUser(conn, username);

			// 密码不存在
			if (queryPassword == null) {
				throw new UnknownAccountException("用户不存在");
			}

			// 密码不匹配
			if (!password.equals(queryPassword)) {
				throw new IncorrectCredentialsException("密码不正确");
			}

			info = new SimpleAuthenticationInfo(username,
					queryPassword.toCharArray(), getName());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(conn);
		}

		return info;
	}

	protected Set<String> getPermissions(Connection conn, String username,
			Collection<String> roleNames) throws SQLException {
		PreparedStatement ps = null;
		Set<String> permissions = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(getUserPermissionByUserId);
			for (String roleName : roleNames) {

				ps.setString(1, roleName);

				ResultSet rs = null;

				try {
					rs = ps.executeQuery();
					while (rs.next()) {
						String permissionString = rs.getString(1);
						permissions.add(permissionString);
					}
				} finally {
					JdbcUtils.closeResultSet(rs);
				}
			}
		} finally {
			JdbcUtils.closeStatement(ps);
		}
		return permissions;
	}

	protected Set<String> getRoleNamesForUser(Connection conn, String username)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<String> roleNames = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(getUserRolesByUserId);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				String roleName = rs.getString(1);
				if (roleName != null) {
					roleNames.add(roleName);
				}
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}
		return roleNames;
	}

	private String getPasswordForUser(Connection conn, String username)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		try {
			ps = conn.prepareStatement(getUserPasswordByUserId);
			ps.setString(1, username);

			rs = ps.executeQuery();

			boolean foundResult = false;
			while (rs.next()) {

				// 确保只有一个用户
				if (foundResult) {
					throw new AuthenticationException(
							"More than one user row found for user ["
									+ username + "]. Usernames must be unique.");
				}

				result = rs.getString(1);

				foundResult = true;
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}

		return result;
	}

}
