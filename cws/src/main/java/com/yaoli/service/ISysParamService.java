package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.SysParam;

public interface ISysParamService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysParam record);

    int insertSelective(SysParam record);

    SysParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysParam record);

    int updateByPrimaryKey(SysParam record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<SysParam> selectByPaingAndCondition(Map<String, String> map);
    
    List<SysParam> getAbnormalType(String keyname);
    
    int updateAbnormalTypeToFalse();
    
    int updateAbnormalTypeToTrueById(String id);
    
    int updateIntervaltime(String intervaltime);
    
    List<SysParam> getAbnormalTypeForMessage();
    
    //判断今天是否发送过短信
    int existSendMessageDay(Map<String, String> map);
    
    //获取com端口
    int getComPort();
    
    int updateMessagesendbegintime(String messagesendbegintime);
    
    int updateMessagesendendtime(String messagesendendtime);
}