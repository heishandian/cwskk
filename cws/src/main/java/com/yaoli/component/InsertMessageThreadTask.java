package com.yaoli.component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.yaoli.beans.Message;
import com.yaoli.service.IMessageService;

@Component
public class InsertMessageThreadTask {
	private static final Logger logger  = Logger.getLogger(InsertMessageThreadTask.class.getName());
	
	private static BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>();
	
	private ThreadPoolTaskExecutor taskExecutor;
	

	public void setQueue(BlockingQueue<Message> queue) {
		InsertMessageThreadTask.queue = queue;
	}
	
	public static void putMessageToQueue(Message message) throws InterruptedException{
		InsertMessageThreadTask.queue.put(message);
	}
	
    public InsertMessageThreadTask(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
        InsertMessageThread temp = new InsertMessageThread();
		Thread sendmessage = new Thread(temp);
		taskExecutor.execute(sendmessage);	
    }
	
	@Resource
	private IMessageService iMessageService;
	
	public class InsertMessageThread implements Runnable {

		@Override
		public void run() {
			while(!Thread.interrupted()){
				Message message = new Message();
				try {
				message = queue.take();
				
				logger.info(message.getMessagedetail());
				iMessageService.insertSelective(message);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable t) {
					t.printStackTrace();
					logger.info("发送短信异常");
					taskExecutor.shutdown();
					logger.info("短信插入数据库程序中止，请重新启动web容器");
				}
			}
		}
	}
	
	
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

    public InsertMessageThreadTask() {}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
