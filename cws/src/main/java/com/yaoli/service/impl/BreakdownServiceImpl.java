package com.yaoli.service.impl;

import com.yaoli.beans.Breakdown;
import com.yaoli.dao.BreakdownMapper;
import com.yaoli.service.IBreakdownService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.yaoli.vo.BreakdownVO;
import org.springframework.stereotype.Service;

@Service("BreakdownServiceImpl")
public class BreakdownServiceImpl implements IBreakdownService {
    @Resource
    public BreakdownMapper breakdownMapper;

    public int deleteByPrimaryKey(Integer id) {
        return breakdownMapper.deleteByPrimaryKey(id);
    }

    public int insert(Breakdown record) {
        return breakdownMapper.insert(record);
    }

    public int insertSelective(Breakdown record) {
        return breakdownMapper.insertSelective(record);
    }

    public Breakdown selectByPrimaryKey(Integer id) {
        return breakdownMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Breakdown record) {
        return breakdownMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Breakdown record) {
        return breakdownMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return breakdownMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return breakdownMapper.getCountByParameterName(parameterName);
    }

    public List<Breakdown> selectByPaingAndCondition(Map<String, String> map) {
        return breakdownMapper.selectByPaingAndCondition(map);
    }

    public Breakdown selectByItemno(String itemno){
        return breakdownMapper.selectByItemno(itemno);
    }

    @Override
    public List<BreakdownVO> showFaultReview(Map<String, String> map) {
        return breakdownMapper.showFaultReview(map);
    }

    @Override
    public int showFaultReviewCount(Map<String, String> map) {
        return breakdownMapper.showFaultReviewCount(map);
    }

    @Override
    public List<BreakdownVO> getFaultStatisticReport(Map<String, String> map) {
        return breakdownMapper.getFaultStatisticReport(map);
    }

    @Override
    public int getFaultStatisticReportCount(Map<String, String> map) {
        return breakdownMapper.getFaultStatisticReportCount(map);
    }

    @Override
    public List<BreakdownVO> getAllFaultStatisticReport(Map<String, String> map) {
        return breakdownMapper.getAllFaultStatisticReport(map);
    }


}