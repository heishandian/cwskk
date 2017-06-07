package com.yaoli.beans;

import java.util.Date;

public class UserLoginRecord {
    private Integer id;

    private Integer userid;

    private Date userlogintime;

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
}