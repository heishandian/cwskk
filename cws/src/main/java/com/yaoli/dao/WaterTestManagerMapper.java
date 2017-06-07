package com.yaoli.dao;

import com.yaoli.beans.WaterTestManager;
import com.yaoli.vo.WaterTestManagerVO;

import java.util.List;
import java.util.Map;

public interface WaterTestManagerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaterTestManager record);

    int insertSelective(WaterTestManager record);

    WaterTestManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaterTestManager record);

    int updateByPrimaryKey(WaterTestManager record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<WaterTestManager> selectByPaingAndCondition(Map<String, String> map);
    
    List<WaterTestManagerVO> getWaterTestManagerVOsByCondition(Map<String, String> map);
    
    int getWaterTestManagerVOsCountByCondition(Map<String, String> map);
    
    List<WaterTestManagerVO>  getTop15WaterTestManager(Map<String, String> map);
}