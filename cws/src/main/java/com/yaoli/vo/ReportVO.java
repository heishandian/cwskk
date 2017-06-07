package com.yaoli.vo;

/**
 * Created by will on 2016/9/11.
 */
public class ReportVO {
    private String testingtime;
    private String sewageid;
    private String areaid;
    private String reporttype;

    public String getSewageid() {
        return sewageid;
    }

    public void setSewageid(String sewageid) {
        this.sewageid = sewageid;
    }

    public String getTestingtime() {
        return testingtime;
    }

    public void setTestingtime(String testingtime) {
        this.testingtime = testingtime;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

}
