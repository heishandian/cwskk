package com.yaoli.component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.yaoli.beans.Message;
import com.yaoli.beans.SerialBean;
import com.yaoli.service.ISysParamService;
import com.yaoli.util.InfoUtil;

@Component
public class SendMessageThreadTask {
	private static final Logger logger  = Logger.getLogger(SendMessageThreadTask.class.getName());
	
	private static BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>();
	
	private ThreadPoolTaskExecutor taskExecutor;
	

	public void setQueue(BlockingQueue<Message> queue) {
		SendMessageThreadTask.queue = queue;
	}
	
	public static void putMessageToQueue(Message message) throws InterruptedException{
		SendMessageThreadTask.queue.put(message);
	}
	
    public SendMessageThreadTask(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
        SendMessageThread temp = new SendMessageThread();
		Thread sendmessage = new Thread(temp);
		taskExecutor.execute(sendmessage);	
    }
	
	@Resource
	private ISysParamService iSysParamService;
	
	public class SendMessageThread implements Runnable {

		@Override
		public void run() {
			while(!Thread.interrupted()){
				Message message = new Message();
				try {
				message = queue.take();
				
				int com = iSysParamService.getComPort();
				
				SerialBean serialBean = new SerialBean(com);

				serialBean.initialize();

				InfoUtil.sendMessage(serialBean, message.getTel(), message.getMessagedetail());
				logger.info(message.getMessagedetail());
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable t) {
					t.printStackTrace();
					logger.info("发送短信异常");
					taskExecutor.shutdown();
					logger.info("发送线程中止，请重新启动web容器");
				}
			}
		}
	}
	
	
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	public ISysParamService getiSysParamService() {
		return iSysParamService;
	}

	public void setiSysParamService(ISysParamService iSysParamService) {
		this.iSysParamService = iSysParamService;
	}
	
    public SendMessageThreadTask() {}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	
}
