package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.SysUser;
import com.yaoli.vo.SysUserVO;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
     
    /**
     * 根据用户登录名查找用户信息
     * @param loginName
     * @return
     */
    SysUser getSysUserByLoginName(String loginName);
    
    List<SysUserVO> getAllUserWithRoleName(Map<String, String> map);
    
    public int getTotalCount();
    
    public int selectUserCountByUserloginname(String name);
    
    int resetAllUserPwd();
    
    int resetUserPwdByUserId(int userid);
    
}