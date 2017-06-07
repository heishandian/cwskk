package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.AdminArea;
import com.yaoli.beans.Area;
import com.yaoli.dao.AdminAreaMapper;
import com.yaoli.dao.AreaMapper;
import com.yaoli.service.IAreaService;
import com.yaoli.vo.AreaVO;

@Service("areaServiceImpl")
public class AreaServiceImpl implements IAreaService{

	@Resource
	public AreaMapper areaMapper;
	
	@Resource
	public AdminAreaMapper adminAreaMapper;
	
	@Override
	public List<Area> getAllAreas(){
		List<Area> allAreas = areaMapper.getAllAreas();
		return allAreas;
	}
	
	@Override
	public Area selectByPrimaryKey(Integer id){
		Area area = areaMapper.selectByPrimaryKey(id);
		return area;
	}

	@Override
	public List<AreaVO> getAreasByPaging(Map<String, String> map) {
		List<AreaVO> areaList= areaMapper.getAreaVOByPaging(map);
		return areaList;
	}

	@Override
	public int getTotalAreaCount() {
		int count = areaMapper.getTotalAreaCount();
		return count;
	}

	@Override
	public int insert(Area record) {
		int count = areaMapper.insert(record);
		return count;
	}

	@Override
	public int insert(AdminArea record) {
		int count = adminAreaMapper.insert(record);
		return count;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		int count = areaMapper.deleteByPrimaryKey(id);
		return count;
	}

	@Override
	public int updateSendAreaToFalse() {
		return areaMapper.updateSendAreaToFalse();
	}

	@Override
	public int updateSendAreaToTrueByAreaid(String areaid) {
		return areaMapper.updateSendAreaToTrueByAreaid(areaid);
	}

	@Override
	public int insertSelective(Area record) {
		return areaMapper.insertSelective(record);
	}

	@Override
	public int deleteAdminAreaByAreaId(Integer areaid) {
		return adminAreaMapper.deleteAdminAreaByAreaid(areaid);
	}

	@Override
	public AdminArea getAdminAreaByAreaid(Integer areaid) {
		// TODO Auto-generated method stub
		return adminAreaMapper.getAdminAreaByAreaid(areaid);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		return areaMapper.updateByPrimaryKeySelective(record);
	}

	
	
}
