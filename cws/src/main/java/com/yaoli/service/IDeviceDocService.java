package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.DeviceDoc;

public interface IDeviceDocService {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceDoc record);

    int insertSelective(DeviceDoc record);

    DeviceDoc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceDoc record);

    int updateByPrimaryKey(DeviceDoc record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<DeviceDoc> selectByPaingAndCondition(Map<String, String> map);
    
    int getPaingAndConditionTotalCountBySewageid(Map<String, String> map);
    
    //获取所有的deviceDocs();
    List<DeviceDoc> getAllDeviceDocs();
}