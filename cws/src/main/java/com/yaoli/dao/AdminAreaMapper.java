package com.yaoli.dao;

import com.yaoli.beans.AdminArea;

public interface AdminAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminArea record);

    int insertSelective(AdminArea record);

    AdminArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminArea record);

    int updateByPrimaryKey(AdminArea record);
    
    int deleteAdminAreaByAreaid(Integer areaid);

    AdminArea getAdminAreaByAreaid(Integer areaid);
}