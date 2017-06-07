package com.yaoli.vo;

import java.util.Date;

public class UserLoginRecordVO {
    private String loginname;

    private Integer id;

    private Integer userid;

    private Date userlogintime;
    
    private String username;
    
    private String begintime;
    
    private String endtime;
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getUserlogintime() {
        return userlogintime;
    }

    public void setUserlogintime(Date userlogintime) {
        this.userlogintime = userlogintime;
    }

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
}
