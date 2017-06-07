package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.RunData;
import com.yaoli.beans.RunDataAbnormal;

public interface IRunDataService {
    int deleteByPrimaryKey(Long id);

    int insert(RunData record);

    int insertSelective(RunData record);

    RunData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RunData record);

    int updateByPrimaryKey(RunData record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<RunData> selectByPaingAndCondition(Map<String, String> map);
    
    RunData getLatestRunData(int sewageid);
    
    List<RunDataAbnormal> getLatestSewageRunDataAbnormal();
    
    List<RunData> getLatestSewageRunData();
    
    List<RunData> getLatestSewageRunDataByAreaid(int areaid);
    
    //获取设备运行记录
    List<RunData> getEquipmentRunDataRecord(Map<String, String> map);
    
    //获取设备运行记录总数
    int getEquipmentRunDatasRecordTotal(Map<String, String> map);
}