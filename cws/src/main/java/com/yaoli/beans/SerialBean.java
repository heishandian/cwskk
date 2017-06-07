package com.yaoli.beans;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;


public class SerialBean {
	private static final Logger logger  = Logger.getLogger(SerialBean.class.getName());
	
	private static String portName;
	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private static OutputStream out;
	private static InputStream in;
	
	
	public SerialBean(int portId){
		portName = "COM"+portId;
	}
	
	public int initialize(){
		int initSuccess = 1;
		int initFail = -1;
		
		try {
			
			portId = CommPortIdentifier.getPortIdentifier(portName);
			try {
				serialPort = (SerialPort) portId.open("Serial_Communication", 2000);
			} catch (Exception e) {
				logger.info("获取串口号serialPort失败");
				e.printStackTrace();
				return initFail;
			}
			
			try {
				serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			} catch (Exception e) {
				logger.info("设置串口号参数失败");
				e.printStackTrace();
				return initFail;
			}
			
			try {
				serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN|SerialPort.FLOWCONTROL_RTSCTS_OUT);
			} catch (Exception e) {
				logger.info("设置串口流控制模式失败");
				e.printStackTrace();
				return initFail;
			}
			
			try {
				in = serialPort.getInputStream();
				out = serialPort.getOutputStream();
			} catch (Exception e) {
				logger.info("串口设置输入输出流失败");
				e.printStackTrace();
				return initFail;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return initFail;
		}
		logger.info("串口初始化成功");
		return initSuccess;
	}
	
	
	public String readPort(){
		String msg = "";
		byte [] b = new byte[1024];
		int length = 0;
		try {
			length = in.read(b);
			for (int i=0; i<length; i++){
				msg = msg + (char)b[i];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	public void writePort(String msg){
		try {
			for (int i=0; i<msg.length(); i++){
				out.write(msg.charAt(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeH(String msg){
		try {
			for (int i=0; i<msg.length(); i++){
				out.write(msg.charAt(i));
			}
			out.write(0x1a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeH(List<Integer> datas) throws IOException{
		for (Integer i : datas){
				out.write(i);
		}
		out.write(0x00);
		out.write(0x1a);
		
	}
	
	public void closePort(){
		serialPort.close();
	}
	
}
