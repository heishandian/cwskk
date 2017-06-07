package com.yaoli.service;

import com.yaoli.beans.WithoutElectricDataAbnormal;
import com.yaoli.controller.WarmingDayLog;
import com.yaoli.vo.WarmingDayLogVO;
import com.yaoli.vo.WithoutElectricDataAbnormalVO;

import java.util.List;
import java.util.Map;

public interface IWithoutElectricDataAbnormalService {
    int deleteByPrimaryKey(Long id);

    int insert(WithoutElectricDataAbnormal record);

    int insertSelective(WithoutElectricDataAbnormal record);

    WithoutElectricDataAbnormal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WithoutElectricDataAbnormal record);

    int updateByPrimaryKey(WithoutElectricDataAbnormal record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<WithoutElectricDataAbnormal> selectByPaingAndCondition(Map<String, String> map);

    List<WarmingDayLogVO> getWarmingDayLogsByCondition(Map<String,String> map);

    int getWarmingDayLogsCountByCondition(Map<String,String> map);

    /**
     * 一次性获取所有的数据，而不采用分页的方法
     */
    List<WarmingDayLogVO> getWarmingDayLogsByConditionWithoutPaing(Map<String,String> map);

    //报警日志 统计
    List<WarmingDayLogVO> getWarmingLogReportByCondition(Map<String,String> map);

    int getWarmingLogReportCountByCondition(Map<String,String> map);

    //报警日志 没有分页
    List<WarmingDayLogVO> getWarmingLogReportByConditionWithOutPaging(Map<String,String> map);
}