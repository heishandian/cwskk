package com.yaoli.aspect.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface MustValidated {
/*	int count() default Integer.MAX_VALUE;
	
	long time() default 60000;*/
}
