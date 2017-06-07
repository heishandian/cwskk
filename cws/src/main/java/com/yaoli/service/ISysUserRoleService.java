package com.yaoli.service;

import com.yaoli.beans.SysUserRole;


public interface ISysUserRoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);
    
    public int deleteUserRoleByUserId(Integer userId);

}
