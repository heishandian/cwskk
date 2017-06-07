package com.yaoli.common.obsoleted;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SysTestInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(SysTestInterceptor.class);

	@SuppressWarnings("unused")
	private Subject subject = null;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		subject = null;
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Subject subject = SecurityUtils.getSubject();

		boolean hasRole = subject.isPermitted("system:admin:*");
		
		System.out.println(hasRole);
		try {
			
			return true;
		} catch (AccountException e) {
			logger.info("没有权限");
			//m/role/common/login.do
			request.getRequestDispatcher("/common/login.do").forward(request, response);
			//response.sendRedirect("/common/login.do");
			return false;
		} 
		// 正确的话跳转到下一个拦截器

	}

}
