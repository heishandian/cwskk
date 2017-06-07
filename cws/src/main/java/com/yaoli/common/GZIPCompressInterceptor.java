package com.yaoli.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 暂时没有什么用
 * @author will
 *
 */
public class GZIPCompressInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
/*		PrintWriter pw = response.getWriter();
		pw.w*/
		//response.getWriter().write("asdf asd ");
/*		PrintWriter pw = response.getWriter();
		String a = "";
		pw.write(a);*/
/*	    PrintWriter os = response.getWriter();
	    String content = new ByteBufInputStream(os);
		response.setContentType("text/html; charset=UTF-8");
	    response.setHeader("Content-Encoding", "gzip");
        response.setCharacterEncoding("UTF-8");  
        //将内容转换成byte
	    byte [] a = GZIP.compress(content.getBytes("UTF-8"));
	    response.setContentLength(a.length);
	    response.getOutputStream().write(a);*/
	}

}
