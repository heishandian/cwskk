package com.yaoli.aspect.annotation;

import java.lang.annotation.*;

/**
 * Created by will on 2016/11/18.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormCheckTokenAnnotation {
    String description()  default "";
}
