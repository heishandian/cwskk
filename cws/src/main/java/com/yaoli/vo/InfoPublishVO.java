package com.yaoli.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InfoPublishVO {
    private String name;

    private String publisher;

    private Integer id;

    private Date inserttime;

    private Integer publisherid;

    private String begintime;

    private String endtime;

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

    public String getPublishcontent() {
        return publishcontent;
    }

    public void setPublishcontent(String publishcontent) {
        this.publishcontent = publishcontent == null ? null : publishcontent.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}