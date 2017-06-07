package com.yaoli.util;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;

/**
 * Created by will on 2016/10/29.
 */
public class RequestParameterParse {

    private Class clazz;

    private HttpServletRequest request;

    private boolean setNull;


    public RequestParameterParse(){

    }

    /**
     * 根据传进来的clazz返回该对象
     * @param clazz
     * @param request
     */
    public RequestParameterParse(Class clazz, HttpServletRequest request){
        this.clazz = clazz;
        this.request = request;
        this.setNull = false;
    }

    public RequestParameterParse(Class clazz, HttpServletRequest request,boolean setNull){
        this.clazz = clazz;
        this.request = request;
        this.setNull = setNull;
    }

    public Object getParameterMap() throws Exception {
        Map<String,String[]> map = request.getParameterMap();

        Class<?> instance = Class.forName(clazz.getName());
        Object object = instance.newInstance();//先生成一个对象



        Set<Map.Entry<String,String[]>> entry = map.entrySet();
        Iterator<Map.Entry<String,String[]>> it = entry.iterator();
        while(it.hasNext()){
            Map.Entry<String,String[]> me = it.next();
            String key = me.getKey();
            String [] value = me.getValue();
            if(!value[0].equals("")){
                //System.out.println(key);
                Field field = getFieldByName(key);

                String fieldName = key.substring(0,1).toUpperCase()+ key.substring(1);
                Method method = instance.getDeclaredMethod("set"+fieldName,field.getType());
                method.invoke(object,map.get(field.getName()));
            }
        }




//        Field[] fields = clazz.getDeclaredFields();
//        for(Field field : fields){
//            String fieldName = field.getName().substring(0,1).toUpperCase()+ field.getName().substring(1);
//            Method method = instance.getDeclaredMethod("set"+fieldName,field.getType());
//            method.invoke(object,map.get(field.getName()));
//        }

        return object;
    }

    public Field getFieldByName(String fieldName){
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            String name = field.getName();
            if(fieldName.equals(name)){
                return field;
            }
        }

        return null;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
