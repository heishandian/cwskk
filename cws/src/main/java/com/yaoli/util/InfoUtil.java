package com.yaoli.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.yaoli.beans.SerialBean;

public class InfoUtil {
	private static final Logger logger  = Logger.getLogger(SerialBean.class.getName());
	
	//将汉字转成10进制
	public static List<Integer> toUnicode(String s){
		String as[] = new String[s.length()];
		String [] cover = {"","000","00","0"}; 
	    List<Integer> datas = new ArrayList<Integer>();
	    for (int i = 0; i < s.length(); i++){
	    	as[i] = Integer.toHexString(s.charAt(i) & 0xffff);
	        if (as[i].length() != 4){
	        	as[i] = cover[as[i].length()]+as[i];
	        }
	        datas.add(Integer.parseInt(as[i].substring(0, 2),16));
	        datas.add(Integer.parseInt(as[i].substring(2, 4),16));
	        }
	   return datas;
	}
	
	//发送短信
	public static void sendMessage(SerialBean serialBean,String tel,String msg) throws Exception{
		synchronized (InfoUtil.class) {
			String result = "";
			String cmd = "AT\r";
			serialBean.writePort(cmd);
			result = serialBean.readPort();
			logger.info(result);
			
			cmd = "AT+ZMSGL=6,4\r";
			serialBean.writePort(cmd);
			result = serialBean.readPort();
			logger.info(result);
			
			cmd = "AT+CMGS="+tel+"\r";
			serialBean.writePort(cmd);
			result = serialBean.readPort();
			logger.info(result);
			
			List<Integer> datas = InfoUtil.toUnicode(msg);
			serialBean.writeH(datas);
			result = serialBean.readPort();
			logger.info(result);
		}
	}

}
