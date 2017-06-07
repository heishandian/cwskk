package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yaoli.beans.SysUser;
import com.yaoli.dao.SysUserMapper;
import com.yaoli.service.ISysUserService;
import com.yaoli.vo.SysUserVO;

@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService{

	private static Logger logger = Logger.getLogger(SysUserServiceImpl.class);
	
	@Resource
	private SysUserMapper sysUserMapper;
	
	@Override
	public SysUser getUserByLoginName(String loginName) {
		logger.debug("getSysUserByLoginName");
		return sysUserMapper.getSysUserByLoginName(loginName);
	}

	@Override
	public List<SysUserVO> getAllUserWithRoleName(Map<String, String> map) {
		List<SysUserVO> list = sysUserMapper.getAllUserWithRoleName(map);
		return list;
	}
	
	@Override
	public int getTotalCount(){
		int count = sysUserMapper.getTotalCount();
		return count;
	}

	@Override
	public int insert(SysUser record) {
		int count = sysUserMapper.insert(record);
		return count;
	}

	@Override
	public int selectUserCountByUserloginname(String name) {
		return sysUserMapper.selectUserCountByUserloginname(name);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUser record) {
		return sysUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysUser record) {
		return sysUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int resetAllUserPwd() {
		return sysUserMapper.resetAllUserPwd();
	}

	@Override
	public int resetUserPwdByUserId(int userid) {
		return sysUserMapper.resetUserPwdByUserId(userid);
	}

	@Override
	public SysUser selectByPrimaryKey(Integer userid) {
		return sysUserMapper.selectByPrimaryKey(userid);
	}
	
}
