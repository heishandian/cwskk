package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.Area;
import com.yaoli.vo.AreaVO;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKeyWithBLOBs(Area record);

    int updateByPrimaryKey(Area record);
    
    public List<Area> getAllAreas();
    
    public List<AreaVO> getAreaVOByPaging(Map<String, String> map);
    
    public int getTotalAreaCount();
    
    int updateSendAreaToFalse();
    
    int updateSendAreaToTrueByAreaid(String areaid);
}