package com.yaoli.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yaoli.beans.SysUser;
import com.yaoli.vo.SysUserVO;

@Service()
public interface ISysUserService {
	public SysUser getUserByLoginName(String loginName);
	
	public List<SysUserVO> getAllUserWithRoleName(Map<String, String> map);
	
	public int getTotalCount();
	
	int insert(SysUser record);
	
	public int selectUserCountByUserloginname(String name);
	
    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    int deleteByPrimaryKey(Integer id);
    
    int resetAllUserPwd();
    
    int resetUserPwdByUserId(int userid);
    
    public SysUser selectByPrimaryKey(Integer userid);
}
