package com.yaoli.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yaoli.beans.Message;
import com.yaoli.beans.SysParam;
import com.yaoli.service.IMessageService;
import com.yaoli.service.ISysParamService;
import com.yaoli.util.SewageVOUtils;

@Component
public class SendMessageJob {
	private static Logger logger = Logger.getLogger(SendMessageJob.class);
	
	@Resource
	private ISysParamService iSysParamService;
	
	@Resource
	private IMessageService iMessageService;
	

	

	protected void executeInternal() throws InterruptedException, ParseException{
		List<SysParam> sysParams = iSysParamService.getAbnormalTypeForMessage();
		
		/**
		 * 以下模块将 字符串转换成 当前时间
		 */
		//开始时间和结束时间参数对象
		SysParam begintimeParam = iSysParamService.selectByPrimaryKey(6);
		SysParam endtimeParam = iSysParamService.selectByPrimaryKey(7);
		
		//获取值
		if(begintimeParam != null && endtimeParam != null){
			String begintimeString = begintimeParam.getValue();
			String endtimeString = endtimeParam.getValue();
			
			//只有在不为空的时候进入
			if(!SewageVOUtils.isBlank(begintimeString) && !SewageVOUtils.isBlank(endtimeString)){
		        Calendar now = Calendar.getInstance();  
		        int year = now.get(Calendar.YEAR);
		        int month = now.get(Calendar.MONTH) + 1;
		        int day = now.get(Calendar.DAY_OF_MONTH);

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				begintimeString = year+"-"+month+"-"+day+" "+begintimeString;
				endtimeString = year+"-"+month+"-"+day+" "+endtimeString;
				
				Date beginTime = format.parse(begintimeString);
				Date endTime = format.parse(endtimeString);
				//获取当前时间
				Date nowDate = new Date();
				//当前时间 小于等于 结束时间 和 当前时间大于等于 开始时间
				if(nowDate.getTime() <= endTime.getTime() && nowDate.getTime() >= beginTime.getTime()){
					List<Message> runDataAbnormalsForMesages = new ArrayList<Message>();
					List<Message> detectionDataAbnormalsForMessages = new ArrayList<Message>();
					List<Message> duandianduanxianForMessages = new ArrayList<Message>();
					
					for (SysParam sysParam : sysParams) {
						if(sysParam.getValue().equals("设备故障")){
							runDataAbnormalsForMesages = iMessageService.getRunDataAbnormalForMessage();
						}else if(sysParam.getValue().equals("水质异常")){
							detectionDataAbnormalsForMessages = iMessageService.getDetectionAbnormalForMessage();
						}else if(sysParam.getValue().equals("网络故障")){
							duandianduanxianForMessages = iMessageService.getWithoutElectricForMessage();
						}
					}
					
					// 注意 Message对象有个sendtime属性，这里sendtime设置为插入数据库的时间，故为数据库表中的sendtime 设置了默认值 getdate()
					for (Message message1 : detectionDataAbnormalsForMessages) {
						synchronized (this) {
							if(iMessageService.messageNeedToSend(message1)== 0){
								InsertMessageThreadTask.putMessageToQueue(message1);
								SendMessageThreadTask.putMessageToQueue(message1);
							}
						}
					}
					
					
					for (Message message2 : runDataAbnormalsForMesages) {
						synchronized (this) {
							if(iMessageService.messageNeedToSend(message2)== 0){
								InsertMessageThreadTask.putMessageToQueue(message2);
								SendMessageThreadTask.putMessageToQueue(message2);
							}	
						}
					}
					
					for (Message message3 : duandianduanxianForMessages) {
						synchronized (this) {
							if(iMessageService.messageNeedToSend(message3)== 0){
								InsertMessageThreadTask.putMessageToQueue(message3);
								SendMessageThreadTask.putMessageToQueue(message3);
							}
						}
					}
				}
			}
			//end
		}
	}
	
	public static void main(String[] args) throws ParseException {
        Calendar now = Calendar.getInstance();  
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aa = year+"-"+month+"-"+day+" "+"10:10:10";
		System.out.println(aa);
		System.out.println(formatter1.parse(aa));
	}
}
