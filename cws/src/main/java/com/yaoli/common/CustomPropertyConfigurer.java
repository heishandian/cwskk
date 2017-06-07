package com.yaoli.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.yaoli.config.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

public class CustomPropertyConfigurer extends PropertyPlaceholderConfigurer{
    private static Map<String,String> properties = new HashMap<String,String>();
    
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(DEFAULT_PLACEHOLDER_PREFIX, DEFAULT_PLACEHOLDER_SUFFIX, DEFAULT_VALUE_SEPARATOR, false);
        for(Entry<Object,Object> entry:props.entrySet()){
            String stringKey = String.valueOf(entry.getKey());
            String stringValue = String.valueOf(entry.getValue());

            //配置系统标识
            if (stringKey.equals("systemno")){
                if(stringValue.equals("0")){
                    SystemConfig.site = new SiteWuXi();
                }else if(stringValue.equals("1")){
                    SystemConfig.site = new SiteJiangDu();
                }else if(stringValue.equals("2")){
                    SystemConfig.site = new SiteMianYuan();
                }else if(stringValue.equals("3")){
                    SystemConfig.site = new SiteHuiShan();
                }else if(stringValue.equals("4")){
                    SystemConfig.site = new SiteSuZhou();
                }else if(stringValue.equals("HanJiang")){
                    SystemConfig.site = new SiteHanJiang();
                }else if(stringValue.equals("JiangYin")){
                    SystemConfig.site = new SiteJiangYin();
                }else if(stringValue.equals("XiShan")){
                    SystemConfig.site = new SiteXiShan();
                }else{
                    SystemConfig.site = new SiteWuXi();
                }
            }

            //设备名称
            else if (stringKey.startsWith("equipment")){
                SystemConfig.equips.put(stringKey,stringValue);
            }

            //水质参数
            else if (stringKey.startsWith("detection")){
                SystemConfig.detections.put(stringKey,stringValue);
            }

            //其他配置
            else {
                SystemConfig.others.put(stringKey,stringValue);
            }

            //以下 两句 进口可以忽略
            stringValue = helper.replacePlaceholders(stringValue, props);
            properties.put(stringKey, stringValue);
        }

        //配置站点地图
        if(Boolean.valueOf(properties.get("configmap")) == true){
            SystemConfig.siteMapConfig =new SiteMapConfig.Builder().
                    setLongitude(Double.valueOf(properties.get("longitude"))).
                    setLatitude(Double.valueOf(properties.get("latitude"))).
                    setAreaName(properties.get("areaname")).
                    setZoom(Integer.valueOf(properties.get("zoom"))).
                    setUseAreaName(Boolean.valueOf(properties.get("useAreaName"))).build();
        }


        super.processProperties(beanFactoryToProcess, props);
    }
     
    public static Map<String, String> getProperties() {
        return properties;
    }
     
    public static String getProperty(String key){
        return properties.get(key);
    }
}
