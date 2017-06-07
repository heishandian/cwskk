package com.yaoli.service;

import com.yaoli.beans.BreakdownState;
import java.util.List;
import java.util.Map;

public interface IBreakdownStateService {
    int deleteByPrimaryKey(Integer id);

    int insert(BreakdownState record);

    int insertSelective(BreakdownState record);

    BreakdownState selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BreakdownState record);

    int updateByPrimaryKey(BreakdownState record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<BreakdownState> selectByPaingAndCondition(Map<String, String> map);

    BreakdownState selectMaxStateIdByBreakdownId(Integer breakdownid);

    List<BreakdownState> getBreakdownStateListByBreakdownId(Integer breakdownid);

}