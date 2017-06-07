package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.DetectionDataAbnormal;
import com.yaoli.dao.DetectionDataAbnormalMapper;
import com.yaoli.service.IDetectionDataAbnormalService;
import com.yaoli.vo.DetectionDataAbnormalVO;

@Service("DetectionDataAbnormalServiceImpl")
public class DetectionDataAbnormalServiceImpl implements IDetectionDataAbnormalService {
    @Resource
    public DetectionDataAbnormalMapper detectionDataAbnormalMapper;

    public int deleteByPrimaryKey(Long id) {
        return detectionDataAbnormalMapper.deleteByPrimaryKey(id);
    }

    public int insert(DetectionDataAbnormal record) {
        return detectionDataAbnormalMapper.insert(record);
    }

    public int insertSelective(DetectionDataAbnormal record) {
        return detectionDataAbnormalMapper.insertSelective(record);
    }

    public DetectionDataAbnormal selectByPrimaryKey(Long id) {
        return detectionDataAbnormalMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DetectionDataAbnormal record) {
        return detectionDataAbnormalMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(DetectionDataAbnormal record) {
        return detectionDataAbnormalMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return detectionDataAbnormalMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return detectionDataAbnormalMapper.getCountByParameterName(parameterName);
    }

    public List<DetectionDataAbnormalVO> selectByPaingAndCondition(Map<String, String> map) {
        return detectionDataAbnormalMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<DetectionDataAbnormal> getLatestSewageDetectionDataAbnormal() {
		return detectionDataAbnormalMapper.getLatestSewageDetectionDataAbnormal();
	}
	
	@Override
	public int getPaingAndConditionTotal(Map<String, String> map){
		return detectionDataAbnormalMapper.getPaingAndConditionTotal(map);
	}

	@Override
	public List<DetectionDataAbnormal> getDetectionDataAbnormalsForMessage() {
		return detectionDataAbnormalMapper.getDetectionDataAbnormalsForMessage();
	}
}