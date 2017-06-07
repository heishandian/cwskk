package com.yaoli.aspect;

import com.yaoli.aspect.exceptions.NotFindHttpServletRequestException;
import com.yaoli.aspect.exceptions.NotFindModelAndViewException;
import com.yaoli.util.GetRandomStr;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by will on 2016/11/18.
 * 用于为表单添加token以防止表单重复提交
 */
@Aspect
@Component
public class FormAddTokenAop {
    //定义切点，指定方法进行改进
    @Pointcut("@annotation(com.yaoli.aspect.annotation.FormAddTokenAnnotation)")
    public void addToken(){}


    //进入方法之前 获取request 的session，在session添加唯一的token
    @Around("addToken()")
    public Object aroundAddToken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        HttpServletRequest request = null;
        int httpServletRequestPosition = -1;

        ModelAndView modelAndView = null;
        int modelAndViewPosition = -1;

        //HttpServletRequest
        for(int i = 0; i < args.length ; i++){
            if(args[i] instanceof ShiroHttpServletRequest){
                request = (ShiroHttpServletRequest) args[i];
                httpServletRequestPosition = i;
            }
            if(args[i] instanceof ModelAndView){
                modelAndView = (ModelAndView) args[i];
                modelAndViewPosition = i;
            }
        }

        if(request == null){
            throw new NotFindHttpServletRequestException("you use @FormAddTokenAnnotation Annotation,but you don't set HttpServletRequest parameter");
        }

        if(modelAndView == null){
            throw new NotFindModelAndViewException("you use @FormAddTokenAnnotation Annotation,but you don't set ModelAndView parameter");
        }

        String token = GetRandomStr.getRandomStr();
        request.getSession().setAttribute("token",token);
        modelAndView.addObject("token",token);

        args[httpServletRequestPosition] = request;
        args[modelAndViewPosition] = modelAndView;

        return proceedingJoinPoint.proceed(args);
    }

//    //声明后置通知 有用可以在这里添加课外参数
//    @AfterReturning(pointcut = "doMethod()", returning = "result")
//    public void doAfterReturning(ModelAndView result) {
//        System.out.println("后置通知");
//        System.out.println("---" + result.getViewName() + "---");
//    }



//    @Around("doMethod()")
//    public void around(ProceedingJoinPoint pjp) throws Throwable {
//        //Object args [] = pjp.getArgs();
//        pjp.proceed();
//    }
}
