package com.yaoli.beans;

public class SysPermission {
    private Integer id;

    private String name;

    private String urllink;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrllink() {
        return urllink;
    }

    public void setUrllink(String urllink) {
        this.urllink = urllink == null ? null : urllink.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}