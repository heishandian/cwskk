package com.yaoli.service.impl;

import com.yaoli.beans.WaterTestManager;
import com.yaoli.dao.WaterTestManagerMapper;
import com.yaoli.service.IWaterTestManagerService;
import com.yaoli.vo.WaterTestManagerVO;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("WaterTestManagerServiceImpl")
public class WaterTestManagerServiceImpl implements IWaterTestManagerService {
    @Resource
    public WaterTestManagerMapper waterTestManagerMapper;

    public int deleteByPrimaryKey(Integer id) {
        return waterTestManagerMapper.deleteByPrimaryKey(id);
    }

    public int insert(WaterTestManager record) {
        return waterTestManagerMapper.insert(record);
    }

    public int insertSelective(WaterTestManager record) {
        return waterTestManagerMapper.insertSelective(record);
    }

    public WaterTestManager selectByPrimaryKey(Integer id) {
        return waterTestManagerMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WaterTestManager record) {
        return waterTestManagerMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(WaterTestManager record) {
        return waterTestManagerMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return waterTestManagerMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return waterTestManagerMapper.getCountByParameterName(parameterName);
    }

    public List<WaterTestManager> selectByPaingAndCondition(Map<String, String> map) {
        return waterTestManagerMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<WaterTestManagerVO> getWaterTestManagerVOsByCondition(
			Map<String, String> map) {
		return waterTestManagerMapper.getWaterTestManagerVOsByCondition(map);
	}

	@Override
	public int getWaterTestManagerVOsCountByCondition(Map<String, String> map) {
		return waterTestManagerMapper.getWaterTestManagerVOsCountByCondition(map);
	}

	@Override
	public List<WaterTestManagerVO> getTop15WaterTestManager(
			Map<String, String> map) {
		return waterTestManagerMapper.getTop15WaterTestManager(map);
	}
}