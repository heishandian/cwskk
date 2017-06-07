package com.yaoli.vo;

import com.yaoli.beans.Breakdown;
import com.yaoli.beans.BreakdownState;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BreakdownVO implements Serializable{
    private int num;

    //送检数量
    private String numSongJian;

    //在处理数量
    private String numZaiChuLi;

    //报废数量
    private String numBaoFei;

    //维修完成数量
    private String numWeiXiuWanChen;

    private Breakdown breakdown;
    private List<BreakdownState> breakdownStateList;

    private String operator;
    private String source;
    private String breakdowntype;
    private String itemstate;
    private Date updatetime;


    private String begintime;
    private String endtime;

    private Integer itemstateid;

    private Integer breakdownid;

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

    public Integer getBreakdownid() {
        return breakdownid;
    }

    public void setBreakdownid(Integer breakdownid) {
        this.breakdownid = breakdownid;
    }

    public Integer getItemstateid() {
        return itemstateid;
    }

    public void setItemstateid(Integer itemstateid) {
        this.itemstateid = itemstateid;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBreakdowntype() {
        return breakdowntype;
    }

    public void setBreakdowntype(String breakdowntype) {
        this.breakdowntype = breakdowntype;
    }

    public String getItemstate() {
        return itemstate;
    }

    public void setItemstate(String itemstate) {
        this.itemstate = itemstate;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public List<BreakdownState> getBreakdownStateList() {
        return breakdownStateList;
    }

    public void setBreakdownStateList(List<BreakdownState> breakdownStateList) {
        this.breakdownStateList = breakdownStateList;
    }

    public Breakdown getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(Breakdown breakdown) {
        this.breakdown = breakdown;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNumSongJian() {
        return numSongJian;
    }

    public void setNumSongJian(String numSongJian) {
        this.numSongJian = numSongJian;
    }

    public String getNumZaiChuLi() {
        return numZaiChuLi;
    }

    public void setNumZaiChuLi(String numZaiChuLi) {
        this.numZaiChuLi = numZaiChuLi;
    }

    public String getNumBaoFei() {
        return numBaoFei;
    }

    public void setNumBaoFei(String numBaoFei) {
        this.numBaoFei = numBaoFei;
    }

    public String getNumWeiXiuWanChen() {
        return numWeiXiuWanChen;
    }

    public void setNumWeiXiuWanChen(String numWeiXiuWanChen) {
        this.numWeiXiuWanChen = numWeiXiuWanChen;
    }
}