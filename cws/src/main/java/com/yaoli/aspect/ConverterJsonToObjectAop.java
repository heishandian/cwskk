package com.yaoli.aspect;

import com.alibaba.fastjson.JSON;
import com.yaoli.aspect.annotation.CustomerValueObjectAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by will on 2016/11/20.
 */
@Aspect
@Component
public class ConverterJsonToObjectAop {
    //定义切点，指定方法进行改进
    @Pointcut("@annotation(com.yaoli.aspect.annotation.CustomerRequestBodyAnnotation)")
    public void converter(){}

    @Around("converter()")
    public void beginConverter(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object [] args = proceedingJoinPoint.getArgs();
        Signature signature = proceedingJoinPoint.getSignature();

        int parameterPosition = -1;

        //设置带有注解的参数
        Class<?> parameterClazz = null;

        Class clazz = Class.forName(signature.getDeclaringTypeName());
        Method [] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.getName().equals(signature.getName())){
                //获取注解
                Annotation [][] annotationss = method.getParameterAnnotations();
                //获取参数
                Class<?> [] clazzes = method.getParameterTypes();
                //注解的顺序与参数的顺序一一对应

                for(int i = 0; i < annotationss.length;i++){
                    for(int j = 0; j <annotationss[i].length;j++){
                        if(annotationss[i][j].annotationType().equals(CustomerValueObjectAnnotation.class)){
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
                break;
            }

        }

        //抛出异常
        if(parameterClazz == null){
            throw new NullPointerException("you set @CustomerRequestBodyAnnotation,but didn't find a parameter with @CustomerValueObjectAnnotation");
        }

        HttpServletRequest request = null;

        for(Object os : args){
            if(os instanceof HttpServletRequest){
                request = (HttpServletRequest)os;
            }
        }

        InputStream is = request.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        try {
            StringBuilder sb = new StringBuilder("");

            byte [] bytes = new byte[1024];

            int length = -1;

            while((length = bis.read(bytes)) != -1){
                sb.append(new String(bytes,0,length,"UTF-8"));
            }

            args[parameterPosition] = JSON.parseObject(sb.toString(),parameterClazz);

        }catch (Exception e){
            throw e;
        }

        proceedingJoinPoint.proceed(args);
    }
}
