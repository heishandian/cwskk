package com.yaoli.quartzjob;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

import javax.annotation.Resource;

import com.yaoli.beans.Message;
import com.yaoli.beans.SerialBean;
import com.yaoli.service.ISysParamService;
import com.yaoli.util.InfoUtil;

@Deprecated
/**
 * 该类全部废弃，不再使用
 * @author will
 *
 */
public class SendMessageTask implements UncaughtExceptionHandler{
	private static final Logger logger  = Logger.getLogger(SerialBean.class.getName());
	
	private static BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>();
	
	public BlockingQueue<Message> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Message> queue) {
		SendMessageTask.queue = queue;
	}
	
	public static void putMessageToQueue(Message message) throws InterruptedException{
		SendMessageTask.queue.put(message);
	}
	
	@Resource
	private ISysParamService iSysParamService;
	
    public void uncaughtException(Thread t, Throwable e) {
    	logger.info("发送短信程序异常,请联系程序员");
		Thread sendmessage = new Thread(new SendMessageThread());
		sendmessage.setUncaughtExceptionHandler(new SendMessageTask());
		sendmessage.start();
    	//WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
    	
    	try {
    		//Map<String, AdminController> smt = SysSpringContextUtil.getSendMessageTask();
    		//System.err.println(smt);
    		//System.out.println(smt);
    		//Thread sendmessage = new Thread(smt);
    		//sendmessage.setUncaughtExceptionHandler(smt);
    		//sendmessage.start();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

    }  
    
    @Deprecated
	public class SendMessageThread implements Runnable {

		@Override
		public void run() {
			while(!Thread.interrupted()){
				Message message = new Message();
				try {
				//message = queue.take();
				
				int com = iSysParamService.getComPort();
				
				SerialBean serialBean = new SerialBean(com);

				serialBean.initialize();

				InfoUtil.sendMessage(serialBean, message.getTel(), message.getMessagedetail());
				logger.info(message.getMessagedetail());
				} catch (ExecutionException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
