package com.yaoli.service;

import com.yaoli.beans.Devicemessage;
import com.yaoli.vo.huangkai.ProductMessageSearchConditionVO;

import java.util.List;

/**
 * Created by kk on 2017/6/28.
 */
public interface IDeviceMessageService {
    List<Devicemessage> selectAllEquipmentMessage(ProductMessageSearchConditionVO productMessageSearchConditionVO);

    List<String> selectAllEquipmentStatus();

    List<String> selectAllRecdepartment();

    Devicemessage selectBySerialnumber(String serialNumber);

    int updateBySerialnumber(Devicemessage record);

    int deleteByPrimaryKey(Integer id);

    int insert(Devicemessage record);

    int insertSelective(Devicemessage record);

    Devicemessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Devicemessage record);

    int updateByPrimaryKey(Devicemessage record);
}
