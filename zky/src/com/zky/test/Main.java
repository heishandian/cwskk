package com.zky.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zky.service.IBookShopService;

public class Main {
	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("spring-mybatise.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
	}

	@After
	public void finish() {
		// System.out.println("finish...");
	}

	@Test
	public void testBookPurchase(){
		// 1.获取bookShopService bean
		IBookShopService bkservice = (IBookShopService) ctx.getBean("bookShopService");
		bkservice.purchase("kk", "book01");
	}
	
	

	@Test
	public void test() {
		// 需要完成自动装配，首先要把自己变成能够被spring容器管理的bean
		// 1.从spring容器中获取bean
		// Hello hello = ctx.getBean(Hello.class);
		//StudentServiceImpl studentServiceImpl = (StudentServiceImpl) ctx.getBean("studentService");
		//System.out.println(studentServiceImpl.getStudentById(1));
	}

	
	@Test
	public void testHe1lloworld() {
		// System.out.println("helloworld!");
	}
	
	
	//简单的更新操作
	@Test
	public void testUpdate(){
		String sql = "UPDATE student SET name='ss' where id=?";
		jdbcTemplate.update(sql,1);
	}
	
	//测试数据源是否连接上
	@Test
	public void testDataSource() throws SQLException {
			DataSource dataSource = (DataSource) ctx.getBean(DataSource.class);
			System.out.println(dataSource.getConnection());
	}
}
