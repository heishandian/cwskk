package com.yaoli.vo;

import java.util.List;

public class MessageSettingVO {
	private String intervaltime;
	
	private List<Integer> abnormaltypeIds;
	
	private List<Integer> areaIds;
	
	private String serialPort;
	
	private String sendTelelphone;
	
	private String messageContent;
	
	private String messagesendbegintime;
	
	private String messagesendendtime;
	
	public String getMessagesendbegintime() {
		return messagesendbegintime;
	}

	public void setMessagesendbegintime(String messagesendbegintime) {
		this.messagesendbegintime = messagesendbegintime;
	}

	public String getMessagesendendtime() {
		return messagesendendtime;
	}

	public void setMessagesendendtime(String messagesendendtime) {
		this.messagesendendtime = messagesendendtime;
	}

	public String getSerialPort() {
		return serialPort;
	}

	public void setSerialPort(String serialPort) {
		this.serialPort = serialPort;
	}

	public String getSendTelelphone() {
		return sendTelelphone;
	}

	public void setSendTelelphone(String sendTelelphone) {
		this.sendTelelphone = sendTelelphone;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getIntervaltime() {
		return intervaltime;
	}

	public void setIntervaltime(String intervaltime) {
		this.intervaltime = intervaltime;
	}

	public List<Integer> getAbnormaltypeIds() {
		return abnormaltypeIds;
	}

	public void setAbnormaltypeIds(List<Integer> abnormaltypeIds) {
		this.abnormaltypeIds = abnormaltypeIds;
	}

	public List<Integer> getAreaIds() {
		return areaIds;
	}

	public void setAreaIds(List<Integer> areaIds) {
		this.areaIds = areaIds;
	}
	
	
}
