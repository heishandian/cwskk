package com.yaoli.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.yaoli.quartzjob.InsertMessageTask;
import com.yaoli.quartzjob.SendMessageTask;

@Deprecated
public class InitSendMessageTask implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if(bean instanceof SendMessageTask){
			Thread sendmessage = new Thread(((SendMessageTask)bean).new SendMessageThread());
			sendmessage.setUncaughtExceptionHandler((SendMessageTask)bean);
			sendmessage.start();
		}else if (bean instanceof InsertMessageTask) {
			Thread insertmessage = new Thread((InsertMessageTask)bean);
			insertmessage.start();
		}
		return bean;
	}

}
