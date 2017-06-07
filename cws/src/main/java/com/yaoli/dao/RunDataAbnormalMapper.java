package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.RunDataAbnormal;
import com.yaoli.beans.RunDataAbnormal_back;
import com.yaoli.vo.RunDataAbnormalVO;

public interface RunDataAbnormalMapper {
    int deleteByPrimaryKey(Long runid);

    int insert(RunDataAbnormal_back record);

    int insertSelective(RunDataAbnormal_back record);

    RunDataAbnormal_back selectByPrimaryKey(Long runid);

    int updateByPrimaryKeySelective(RunDataAbnormal_back record);

    int updateByPrimaryKey(RunDataAbnormal_back record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<RunDataAbnormalVO> selectByPaingAndCondition(Map<String, String> map);
    
    //根据某个站点的id和用户选择的开始时间和结束时间 获取设备故障的数量 设备故障处理
    int getHandlePagingAndConditionTotalCount(Map<String,String> map);
    
    int updateHandleRunDataAbnormalByRunId(Long runid);
    
    //设备故障报警
    List<RunDataAbnormalVO> getEquipmentAbnormalByPagingAndConditionMap(Map<String, String> map);
    
    int getEquipmentAbnormalByPagingAndConditionMapTotalCount(Map<String, String> map);
    
    List<RunDataAbnormal> getLatestSewageRunDataAbnormal();
    
    //获取 当天不正常的地区 然后发送 短信
    List<RunDataAbnormal> getRunDataAbnormalsForMessage();
}