package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.Admin;
import com.yaoli.beans.AdminArea;

public interface IAdminService {
    int deleteByPrimaryKey(Integer administratorid);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer administratorid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    public List<Admin> getAllAdmins();
    
    public List<Admin> getAdminsByPaing(Map<String, String> map);
    
    public int getTotalCount();
    
    int insert(AdminArea record);
}
