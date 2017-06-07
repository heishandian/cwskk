package com.yaoli.aspect;


import com.alibaba.fastjson.JSON;
import com.yaoli.aspect.annotation.CustomerValueObjectAnnotation;
import com.yaoli.aspect.annotation.validate.ValidateBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by will on 2016/12/19.
 */
@Aspect
@Component
public class CheckValidationAop {
    //定义切点，指定方法进行改进
    @Pointcut("@annotation(com.yaoli.aspect.annotation.validate.MustValidated)")
    public void converter(){}

    @Around("converter()")
    public void beginConverter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        Type returnType = null;




        //获取参数
        Object [] args = proceedingJoinPoint.getArgs();
        //获取参数签名
        Signature signature = proceedingJoinPoint.getSignature();

        int parameterPosition = -1;

        //设置带有注解的参数，这里为 bean类
        Class<?> parameterClazz = null;

        Class clazz = Class.forName(signature.getDeclaringTypeName());
        //获取类的方法
        Method[] methods = clazz.getMethods();

        //遍历所有方法
        for(Method method : methods){
            if(method.getName().equals(signature.getName())){
                //获取注解
                Annotation[][] annotationss = method.getParameterAnnotations();
                //获取参数
                Class<?> [] clazzes = method.getParameterTypes();
                //注解的顺序与参数的顺序一一对应


                //begin 下面两个循环为一对，parameterClazz
                for(int i = 0; i < annotationss.length;i++){
                    for(int j = 0; j <annotationss[i].length;j++){
                        if(annotationss[i][j].annotationType().equals(ValidateBody.class)){
                            parameterClazz = clazzes[i];
                        }
                    }
                }
                for(int i = 0; i < args.length ; i++){
                    if(args[i].getClass()==parameterClazz){
                        parameterPosition = i;
                        break;
                    }
                }

                returnType = method.getGenericReturnType();
                //end
                break;
            }
        }

        //抛出异常
        if(parameterClazz == null){
            throw new NullPointerException("you set @MustValidated,but didn't find a parameter with @ValidateBody");
        }

        //如果返回类型是void
        if(!returnType.toString().equals("void")){
            throw new Exception("本校验器只适用于无返回类型的方法");
        }







        //遍历参数，获取BindingResult类
        BindingResult bindingResult = null;
        HttpServletResponse  response = null;

        for(Object arg : args){
            if(arg instanceof BindingResult){
                bindingResult = (BindingResult)arg;
            }else if (arg instanceof HttpServletResponse){
                response = (HttpServletResponse) arg;
            }
        }

        if(response == null){
            throw new Exception("在方法参数上未能找到 HttpServletResponse 对象");
        }

        //这里用来检验 vo 对象是否有问题
        if (bindingResult != null){
            if(bindingResult.getErrorCount() != 0){
                List<ObjectError> errors = bindingResult.getAllErrors();
                for(ObjectError oe: errors){
                    String a = oe.getObjectName();
                    String b = oe.getCode();
                }

                //直接返回 HttpServletRespone
                response.getWriter().write("");
            }
        }

        //调用方法处理
        proceedingJoinPoint.proceed(args);

        //这里来检验方法是否有问题
        if (bindingResult != null){
            if(bindingResult.getErrorCount() != 0){
                List<ObjectError> errors = bindingResult.getAllErrors();
                for(ObjectError oe: errors){
                    String a = oe.getObjectName();
                    String b = oe.getDefaultMessage();


                    //直接返回 HttpServletRespone
                    response.getWriter().write("");
                }
            }
        }
    }
}
