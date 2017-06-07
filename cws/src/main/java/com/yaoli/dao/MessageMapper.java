package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Long messageid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long messageid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<Message> selectByPaingAndCondition(Map<String, String> map);
    
    int selectByPaingAndConditionCount(Map<String, String> map);
    
    //获取当日运行设备不正常的 短信
    List<Message> getRunDataAbnormalForMessage();
    
    //获取当日水质异常的站点
    List<Message> getDetectionAbnormalForMessage();
    
    
    //判断这条短信是否需要发送
    int messageNeedToSend(Message message);
    
    //获取当断点断线的站点
    List<Message> getWithoutElectricForMessage();
}