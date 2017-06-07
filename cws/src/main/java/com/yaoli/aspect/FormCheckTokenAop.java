package com.yaoli.aspect;

import com.alibaba.fastjson.JSON;
import com.yaoli.aspect.exceptions.NotFindHttpServletRequestException;
import com.yaoli.aspect.exceptions.NotFindModelAndViewException;
import com.yaoli.util.GetRandomStr;
import com.yaoli.util.JsonMessage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by will on 2016/11/18.
 * 用于检查表单的token是否已经提交
 */
@Aspect
@Component
public class FormCheckTokenAop {
    //定义切点，指定方法进行改进
    @Pointcut("@annotation(com.yaoli.aspect.annotation.FormCheckTokenAnnotation)")
    public void checkToken(){}

    //进入方法之前 获取request 的session，在session添加唯一的token
    @Around("checkToken()")
    public void aroundCheckToken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        HttpServletRequest request = null;
        int httpServletRequestPosition = -1;

        for(int i = 0; i < args.length ; i++){
            if(args[i] instanceof  HttpServletRequest){
                request = (HttpServletRequest) args[i];
                httpServletRequestPosition = i;
            }
        }

        if(request == null){
            throw new NotFindHttpServletRequestException("you use @FormAddTokenAnnotation Annotation,but you don't set HttpServletRequest parameter");
        }

        String token = (String) request.getSession().getAttribute("token");
        String parameterToken = request.getParameter("token");
        request.getSession().removeAttribute("token");

        if(token == null || parameterToken == null){
            request.getSession().setAttribute("formcheck","unpass");
        }else{
            if(token.equals(parameterToken)){
                request.getSession().setAttribute("formcheck","pass");
            }else{
                request.getSession().setAttribute("formcheck","unpass");
            }
        }

        args[httpServletRequestPosition] = request;
        proceedingJoinPoint.proceed(args);
    }
}
