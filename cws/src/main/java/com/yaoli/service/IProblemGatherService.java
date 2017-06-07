package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.ProblemGather;
import com.yaoli.vo.ProblemGatherVO;

public interface IProblemGatherService {
    int deleteByPrimaryKey(Integer id);

    int insert(ProblemGather record);

    int insertSelective(ProblemGather record);

    ProblemGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProblemGather record);

    int updateByPrimaryKey(ProblemGather record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<ProblemGather> selectByPaingAndCondition(Map<String, String> map);
    
    
    List<ProblemGatherVO> getProblemGatherVOsByCondition(Map<String, String> map);
    
    int getProblemGatherVOsCountByCondition(Map<String, String> map);
}