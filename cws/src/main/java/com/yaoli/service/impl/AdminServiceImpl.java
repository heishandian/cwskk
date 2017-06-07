package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.Admin;
import com.yaoli.beans.AdminArea;
import com.yaoli.dao.AdminAreaMapper;
import com.yaoli.dao.AdminMapper;
import com.yaoli.service.IAdminService;

@Service("administratorService")
public class AdminServiceImpl implements IAdminService{

	@Resource
	public AdminMapper adminiMapper;
	
	@Resource
	public AdminAreaMapper adminAreaMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		int count = adminiMapper.deleteByPrimaryKey(id);
		return count;
	}

	@Override
	public int insert(Admin record) {
		return adminiMapper.insert(record);
	}

	@Override
	public int insertSelective(Admin record) {
		
		return 0;
	}

	@Override
	public Admin selectByPrimaryKey(Integer administratorid) {
		
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Admin record) {
		
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Admin record) {
		
		return 0;
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminiMapper.getAllAdmins();
	}

	@Override
	public List<Admin> getAdminsByPaing(Map<String, String> map) {
		return adminiMapper.getAdminsByPaing(map);
	}

	@Override
	public int getTotalCount() {
		return adminiMapper.getTotalCount();
	}

	@Override
	public int insert(AdminArea record) {
		return adminAreaMapper.insert(record);
	}
	

	
}
