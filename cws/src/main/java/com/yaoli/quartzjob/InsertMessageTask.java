package com.yaoli.quartzjob;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.yaoli.beans.Message;
import com.yaoli.beans.SerialBean;
import com.yaoli.service.IMessageService;

@Deprecated
public class InsertMessageTask implements Runnable{
	private static final Logger logger  = Logger.getLogger(SerialBean.class.getName());
	
	private static BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>();
	
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Message> queue) {
		InsertMessageTask.queue = queue;
	}
	
	public static void putMessageToQueue(Message message) throws InterruptedException{
		InsertMessageTask.queue.put(message);
	}
	
	@Resource
	private IMessageService iMessageService;
	
	@Override
	public void run() {
		while(!Thread.interrupted()){
			Message message = null;
			try {
			message = queue.take();
			
			logger.info(message.getMessagedetail());
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				logger.info(message.toString());
				iMessageService.insertSelective(message);
			}
		}
	}

}
