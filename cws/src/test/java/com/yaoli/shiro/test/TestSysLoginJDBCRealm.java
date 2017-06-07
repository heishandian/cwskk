package com.yaoli.shiro.test;

import javax.annotation.Resource;

import com.yaoli.beans.SysUser;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysRole;
import com.yaoli.controller.RoleController;
import com.yaoli.service.ISysRoleService;
import com.yaoli.service.ISysUserService;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/application-springmvc.xml","file:src/test/resources/application-shiro.xml", "file:src/test/resources/application-dao.xml"})
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-springmvc.xml","file:src/main/webapp/WEB-INF/application-shiro.xml", "file:src/main/webapp/WEB-INF/application-dao.xml"})

//添加对数据库 回滚的支持
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TestSysLoginJDBCRealm {
	private static Logger logger = Logger.getLogger(TestSysLoginJDBCRealm.class);
	@Resource
	private ISysRoleService iRolesService = null;
	@Resource
	private RoleController roleController = null;

	@Resource
	private ISysUserService iSysUserService = null;

	@Test
	public void test1() {
		int count = iSysUserService.getTotalCount();
		SysUser user = new SysUser();
		user.setLoginname("testUser1");
		user.setDepartment("testUser1");
		user.setLoginpwd("testUser1");
		user.setUsername("testUser1");

		iSysUserService.insert(user);

		logger.info(JSON.toJSONString(user));
		logger.info(user.getId());
	}

	@Test
	public void insertTest() {
		SysRole user = new SysRole();
		logger.info(JSON.toJSONString(user));
	}

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void setUp() {
		request = new MockHttpServletRequest();

		response = new MockHttpServletResponse();

		//sysLoginJDBCRealm = new SysLoginJDBCRealm();
	}

	@Test
	public void testGetAuthenticationToken() {
		try {
			//sysLoginInterceptor.preHandle(request, response, null);

			AuthenticationToken token = new UsernamePasswordToken("yaoli","yaoli2");

			//切记 切记，junit中的所有 实例化对象全部交给框架
			//SimpleAuthenticationInfo info = (SimpleAuthenticationInfo)sysLoginJDBCRealm.getAuthenticationToken(token);

			//JSON.toJSONString(info);
			//assertEquals(true, sysLoginInterceptor.preHandle(request, response, null));
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}