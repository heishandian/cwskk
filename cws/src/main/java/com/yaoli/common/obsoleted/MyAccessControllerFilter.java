package com.yaoli.common.obsoleted;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

public class MyAccessControllerFilter extends AccessControlFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String [] roles = (String [])mappedValue;
		if(roles == null){
			return true;
		}
		for(String role : roles	){
			if(getSubject(request, response).hasRole(role)){
				
			}
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
