package com.yaoli.common.obsoleted;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.yaoli.controller.AdminController;

public class SysSpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SysSpringContextUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		Object object = applicationContext.getBean(name);
		return object;
	}
	
	public static <T> Object getBean(Class<T> clazz) {
		Object object = applicationContext.getBean(clazz);
		return object;
	}

	public static SqlSessionFactory getSessionFactory(){
		DefaultSqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = (DefaultSqlSessionFactory) SysSpringContextUtil.getBean("sqlSessionFactory");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("没有找到相应的SqlSessionFactoryBean配置，请检查该静态方法下的bean参数是否与xml配置一致");
		} catch (Exception e){
			throw new IllegalArgumentException(e.toString());
		}
		return sqlSessionFactory;
	}
	
	public static Map<String, AdminController>  getSendMessageTask(){
		Map<String, AdminController>  sendMessageTask = null;
		try {
			sendMessageTask = applicationContext.getBeansOfType(AdminController.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("没有找到相应的SqlSessionFactoryBean配置，请检查该静态方法下的bean参数是否与xml配置一致");
		}
		return sendMessageTask;
	}

}
