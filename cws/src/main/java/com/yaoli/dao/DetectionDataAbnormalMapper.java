package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.DetectionDataAbnormal;
import com.yaoli.vo.DetectionDataAbnormalVO;

public interface DetectionDataAbnormalMapper {
    int deleteByPrimaryKey(Long detectionid);

    int insert(DetectionDataAbnormal record);

    int insertSelective(DetectionDataAbnormal record);

    DetectionDataAbnormal selectByPrimaryKey(Long detectionid);

    int updateByPrimaryKeySelective(DetectionDataAbnormal record);

    int updateByPrimaryKey(DetectionDataAbnormal record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<DetectionDataAbnormal> getLatestSewageDetectionDataAbnormal();
    
    List<DetectionDataAbnormalVO> selectByPaingAndCondition(Map<String, String> map);
    
    int getPaingAndConditionTotal(Map<String, String> map);

    List<DetectionDataAbnormal> getDetectionDataAbnormalsForMessage();
}