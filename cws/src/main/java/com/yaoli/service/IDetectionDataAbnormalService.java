package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.DetectionDataAbnormal;
import com.yaoli.vo.DetectionDataAbnormalVO;

public interface IDetectionDataAbnormalService {
    int deleteByPrimaryKey(Long id);

    int insert(DetectionDataAbnormal record);

    int insertSelective(DetectionDataAbnormal record);

    DetectionDataAbnormal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DetectionDataAbnormal record);

    int updateByPrimaryKey(DetectionDataAbnormal record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<DetectionDataAbnormalVO> selectByPaingAndCondition(Map<String, String> map);
    
    List<DetectionDataAbnormal> getLatestSewageDetectionDataAbnormal();
    
    int getPaingAndConditionTotal(Map<String, String> map);
    
    List<DetectionDataAbnormal> getDetectionDataAbnormalsForMessage();
}