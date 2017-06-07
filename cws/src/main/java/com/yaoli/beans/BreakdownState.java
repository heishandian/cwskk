package com.yaoli.beans;

import java.util.Date;

public class BreakdownState {
    private Integer id;

    private Integer breakdownid;

    private Date updatetime;

    private Integer itemstateid;

    private Integer operatorid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBreakdownid() {
        return breakdownid;
    }

    public void setBreakdownid(Integer breakdownid) {
        this.breakdownid = breakdownid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getItemstateid() {
        return itemstateid;
    }

    public void setItemstateid(Integer itemstateid) {
        this.itemstateid = itemstateid;
    }

    public Integer getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(Integer operatorid) {
        this.operatorid = operatorid;
    }
}