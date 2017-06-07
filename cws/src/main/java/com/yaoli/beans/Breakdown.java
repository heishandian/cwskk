package com.yaoli.beans;

import java.io.Serializable;
import java.util.Date;

public class Breakdown implements Serializable{
    private Integer id;

    private String itemname;

    private String itemno;

    private Integer sourceid;

    private String breakdowndescription;

    private Integer breakdowntypeid;

    private Date breakdowntime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno == null ? null : itemno.trim();
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getBreakdowndescription() {
        return breakdowndescription;
    }

    public void setBreakdowndescription(String breakdowndescription) {
        this.breakdowndescription = breakdowndescription == null ? null : breakdowndescription.trim();
    }

    public Integer getBreakdowntypeid() {
        return breakdowntypeid;
    }

    public void setBreakdowntypeid(Integer breakdowntypeid) {
        this.breakdowntypeid = breakdowntypeid;
    }

    public Date getBreakdowntime() {
        return breakdowntime;
    }

    public void setBreakdowntime(Date breakdowntime) {
        this.breakdowntime = breakdowntime;
    }
}