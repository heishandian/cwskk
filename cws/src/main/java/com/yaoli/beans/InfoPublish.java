package com.yaoli.beans;

import java.util.Date;

public class InfoPublish {
    private Integer id;

    private Date inserttime;

    private Integer publisherid;

    private Date begintime;

    private Date endtime;

    private String publishcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getPublishcontent() {
        return publishcontent;
    }

    public void setPublishcontent(String publishcontent) {
        this.publishcontent = publishcontent == null ? null : publishcontent.trim();
    }
}