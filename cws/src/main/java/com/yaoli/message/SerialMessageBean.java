package com.yaoli.message;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/8.
 *
 *
 */
public class SerialMessageBean {
    Long id;

    String content;

    String telephone;

    Date time;

    Boolean read;

    @Override
    public String toString() {
        return "{content:"+this.content+",telephone:"+this.telephone+",time:"+this.time+",read:"+this.read+"}";
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
