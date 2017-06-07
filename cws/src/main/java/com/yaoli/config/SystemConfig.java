package com.yaoli.config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by will on 2017/1/6.
 */
public class SystemConfig {

    /**
     * 当前系统 表示，默认为无锡
     */
    public static SewageSite site = new SiteWuXi();

    //设备名称
    public static Map<String,String> equips = new LinkedHashMap<String, String>();

    //水质参数
    public static Map<String,String> detections = new LinkedHashMap<String, String>();

    //其他配置
    public static Map<String,String> others = new LinkedHashMap<String, String>();

    //地图配置
    public static SiteMapConfig siteMapConfig = null;
}
