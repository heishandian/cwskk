package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.AdminArea;
import com.yaoli.beans.Area;
import com.yaoli.vo.AreaVO;


public interface IAreaService {
/*    int deleteByPrimaryKey(Integer areaid);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKeyWithBLOBs(Area record);

    int updateByPrimaryKey(Area record);*/
    
    public List<Area> getAllAreas();
    
    Area selectByPrimaryKey(Integer areaid);
    
    public List<AreaVO> getAreasByPaging(Map<String, String> map);
    
    public int getTotalAreaCount();
    
    int insert(Area record);
    
    int insertSelective(Area record);
    
    int insert(AdminArea record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateSendAreaToFalse();
    
    int updateSendAreaToTrueByAreaid(String areaid);
    
    int deleteAdminAreaByAreaId(Integer areaid);
    
    AdminArea getAdminAreaByAreaid(Integer areaid);
    
    int updateByPrimaryKeySelective(Area record);
}
