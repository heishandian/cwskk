package com.yaoli.beans;

import java.util.Date;

public class Message {
    private Long messageid;

    private String tel;

    private String messagedetail;

    private Date sendtime;

    private Integer sewageid;

    private Integer abnormaltype;
    
    private Date testingtime;
    
    public Date getTestingtime() {
		return testingtime;
	}

	public void setTestingtime(Date testingtime) {
		this.testingtime = testingtime;
	}

	public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMessagedetail() {
        return messagedetail;
    }

    public void setMessagedetail(String messagedetail) {
        this.messagedetail = messagedetail == null ? null : messagedetail.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getSewageid() {
        return sewageid;
    }

    public void setSewageid(Integer sewageid) {
        this.sewageid = sewageid;
    }

    public Integer getAbnormaltype() {
        return abnormaltype;
    }

    public void setAbnormaltype(Integer abnormaltype) {
        this.abnormaltype = abnormaltype;
    }

	@Override
	public String toString() {
		return "["+messageid+","+tel+","+messagedetail+","+sewageid+","+abnormaltype+"]";
	}
    
    
}