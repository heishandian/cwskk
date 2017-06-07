package com.yaoli.common.obsoleted;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SysLoginPwdInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = Logger.getLogger(SysLoginPwdInterceptor.class);

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
		// 获取登录标志，可以是登录名，也可以是 已经登录的用户凭证 比如一串校验码
		String loginUserName = (String) request.getParameter("loginusername");
		String loginPassword = (String) request.getParameter("loginuserpwd");
		// m/role/role.do?loginusername=yaoli&loginuserpwd=yaoli&id=1
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(loginUserName, loginPassword);
		
		SecurityManager securityManager = SecurityUtils.getSecurityManager();
		
		SecurityUtils.getSecurityManager();
		
		//securityManager.isPermitted(securityManager.,);
		//securityManager.
		try {
			subject.login(token);
			
		} catch (AccountException e) {
			logger.info("用户名/密码错误");
			request.getRequestDispatcher("/common/login.do").forward(request, response);
		} 
		// 正确的话跳转到下一个拦截器
		return false;

	}

}
