package com.yaoli.config;

/**
 * Created by will on 2017/1/13.
 */
public class SiteMapConfig {
    private double longitude;
    private double latitude;
    private String areaName;
    private int zoom;
    //使用 区域名称 或者 经纬度
    private boolean useAreaName;

    public static class Builder{
        private double longitude = 0;
        private double latitude = 0;
        private String areaName = "";
        private int zoom = 10;
        private boolean useAreaName;

        public Builder setLongitude(double val){
            this.longitude = val;
            return this;
        }

        public Builder setLatitude(double val){
            this.latitude = val;
            return this;
        }

        public Builder setAreaName(String val){
            this.areaName = val;
            return this;
        }

        public Builder setZoom(int val){
            this.zoom = val;
            return this;
        }

        public Builder setUseAreaName(boolean val){
            this.useAreaName = val;
            return this;
        }

        public SiteMapConfig build(){
            return new SiteMapConfig(this);
        }
    }

    private SiteMapConfig(Builder builder){
        this.areaName = builder.areaName;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
        this.zoom = builder.zoom;
        this.useAreaName = builder.useAreaName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public boolean isUseAreaName() {
        return useAreaName;
    }

    public void setUseAreaName(boolean useAreaName) {
        this.useAreaName = useAreaName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
