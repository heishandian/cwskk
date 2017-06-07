package com.yaoli.service.impl;

import com.yaoli.beans.BreakdownState;
import com.yaoli.dao.BreakdownStateMapper;
import com.yaoli.service.IBreakdownStateService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("BreakdownStateServiceImpl")
public class BreakdownStateServiceImpl implements IBreakdownStateService {
    @Resource
    public BreakdownStateMapper breakdownStateMapper;

    public int deleteByPrimaryKey(Integer id) {
        return breakdownStateMapper.deleteByPrimaryKey(id);
    }

    public int insert(BreakdownState record) {
        return breakdownStateMapper.insert(record);
    }

    public int insertSelective(BreakdownState record) {
        return breakdownStateMapper.insertSelective(record);
    }

    public BreakdownState selectByPrimaryKey(Integer id) {
        return breakdownStateMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BreakdownState record) {
        return breakdownStateMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BreakdownState record) {
        return breakdownStateMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return breakdownStateMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return breakdownStateMapper.getCountByParameterName(parameterName);
    }

    public List<BreakdownState> selectByPaingAndCondition(Map<String, String> map) {
        return breakdownStateMapper.selectByPaingAndCondition(map);
    }

    @Override
    public BreakdownState selectMaxStateIdByBreakdownId(Integer breakdownid) {
        return breakdownStateMapper.selectMaxStateIdByBreakdownId(breakdownid);
    }

    @Override
    public List<BreakdownState> getBreakdownStateListByBreakdownId(Integer breakdownid) {
        return breakdownStateMapper.getBreakdownStateListByBreakdownId(breakdownid);
    }

}