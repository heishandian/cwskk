package com.yaoli.beans;

import java.util.Date;

public class ProblemGather {
    private Integer id;

    private String title;

    private String description;

    private String finder;

    private Date findtime;

    private Integer sewageid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFinder() {
        return finder;
    }

    public void setFinder(String finder) {
        this.finder = finder == null ? null : finder.trim();
    }

    public Date getFindtime() {
        return findtime;
    }

    public void setFindtime(Date findtime) {
        this.findtime = findtime;
    }

    public Integer getSewageid() {
        return sewageid;
    }

    public void setSewageid(Integer sewageid) {
        this.sewageid = sewageid;
    }
}