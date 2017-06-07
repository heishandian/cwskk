package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.Statistic;

public interface StatisticMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    Statistic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<Statistic> selectByPaingAndCondition(Map<String, String> map);
    
    //根据站点id 执行昨天的统计任务
    int executeStatisticYesterday(int sewageid);
    /**
     * 获取当天流量总和
     * @param sewageid
     * @return
     */
    double getSewageDayTotalWaterFlow(int sewageid);
}