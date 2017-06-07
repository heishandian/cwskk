package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminid);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    public List<Admin> getAllAdmins();
    
    public List<Admin> getAdminsByPaing(Map<String, String> map);

    public int getTotalCount();
}