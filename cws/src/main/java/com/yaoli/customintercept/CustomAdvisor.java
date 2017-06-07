
package com.yaoli.customintercept;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
@SuppressWarnings({"unchecked"})
@Aspect
public class CustomAdvisor extends StaticMethodMatcherPointcutAdvisor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Class<? extends Annotation>[] au = new Class[]{
		RequireUser.class
	};
	
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void getAll(){
		
	}
}
