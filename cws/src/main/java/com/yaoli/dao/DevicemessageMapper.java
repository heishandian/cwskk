package com.yaoli.dao;

import com.yaoli.beans.Devicemessage;
import com.yaoli.vo.huangkai.ProductMessageSearchConditionVO;

import java.util.List;

public interface DevicemessageMapper {
    List<Devicemessage> selectAllEquipmentMessage(ProductMessageSearchConditionVO productMessageSearchConditionVO);

    List<String> selectAllEquipmentStatus();

    List<String> selectAllRecdepartment();

    int updateBySerialnumber(Devicemessage record);

    Devicemessage selectBySerialnumber(String serialNumber);

    int deleteByPrimaryKey(Integer id);

    int insert(Devicemessage record);

    int insertSelective(Devicemessage record);

    Devicemessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicemessage record);

    int updateByPrimaryKey(Devicemessage record);
}