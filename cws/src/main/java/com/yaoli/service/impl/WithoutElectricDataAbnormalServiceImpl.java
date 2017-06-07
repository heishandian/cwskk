package com.yaoli.service.impl;

import com.sun.java.swing.plaf.windows.WindowsToggleButtonUI;
import com.yaoli.beans.WithoutElectricDataAbnormal;
import com.yaoli.dao.WithoutElectricDataAbnormalMapper;
import com.yaoli.service.IWithoutElectricDataAbnormalService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.yaoli.vo.WarmingDayLogVO;
import org.springframework.stereotype.Service;

@Service("WithoutElectricDataAbnormalServiceImpl")
public class WithoutElectricDataAbnormalServiceImpl implements IWithoutElectricDataAbnormalService {
    @Resource
    public WithoutElectricDataAbnormalMapper withoutElectricDataAbnormalMapper;

    public int deleteByPrimaryKey(Long id) {
        return withoutElectricDataAbnormalMapper.deleteByPrimaryKey(id);
    }

    public int insert(WithoutElectricDataAbnormal record) {
        return withoutElectricDataAbnormalMapper.insert(record);
    }

    public int insertSelective(WithoutElectricDataAbnormal record) {
        return withoutElectricDataAbnormalMapper.insertSelective(record);
    }

    public WithoutElectricDataAbnormal selectByPrimaryKey(Long id) {
        return withoutElectricDataAbnormalMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WithoutElectricDataAbnormal record) {
        return withoutElectricDataAbnormalMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WithoutElectricDataAbnormal record) {
        return withoutElectricDataAbnormalMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return withoutElectricDataAbnormalMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return withoutElectricDataAbnormalMapper.getCountByParameterName(parameterName);
    }

    public List<WithoutElectricDataAbnormal> selectByPaingAndCondition(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.selectByPaingAndCondition(map);
    }

    @Override
    public List<WarmingDayLogVO> getWarmingDayLogsByCondition(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.getWarmingDayLogsByCondition(map);
    }

    @Override
    public int getWarmingDayLogsCountByCondition(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.getWarmingDayLogsCountByCondition(map);
    }

    @Override
    public List<WarmingDayLogVO> getWarmingDayLogsByConditionWithoutPaing(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.getWarmingDayLogsByConditionWithoutPaing(map);
    }

    @Override
    public List<WarmingDayLogVO> getWarmingLogReportByCondition(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.getWarmingLogReportByCondition(map);
    }

    @Override
    public int getWarmingLogReportCountByCondition(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.getWarmingLogReportCountByCondition(map);
    }

    @Override
    public List<WarmingDayLogVO> getWarmingLogReportByConditionWithOutPaging(Map<String, String> map) {
        return withoutElectricDataAbnormalMapper.getWarmingLogReportByConditionWithOutPaging(map);
    }
}