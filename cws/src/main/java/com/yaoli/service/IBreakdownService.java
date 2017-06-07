package com.yaoli.service;

import com.yaoli.beans.Breakdown;
import com.yaoli.vo.BreakdownVO;

import java.util.List;
import java.util.Map;

public interface IBreakdownService {
    int deleteByPrimaryKey(Integer id);

    int insert(Breakdown record);

    int insertSelective(Breakdown record);

    Breakdown selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Breakdown record);

    int updateByPrimaryKey(Breakdown record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<Breakdown> selectByPaingAndCondition(Map<String, String> map);

    Breakdown selectByItemno(String itemno);

    List<BreakdownVO> showFaultReview(Map<String, String> map);

    int showFaultReviewCount(Map<String, String> map);

    List<BreakdownVO> getFaultStatisticReport(Map<String, String> map);

    int getFaultStatisticReportCount(Map<String, String> map);

    List<BreakdownVO> getAllFaultStatisticReport(Map<String, String> map);
}