package com.yaoli.service.impl;

import com.yaoli.beans.Devicemessage;
import com.yaoli.dao.DevicemessageMapper;
import com.yaoli.service.IDeviceMessageService;
import com.yaoli.vo.huangkai.ProductMessageSearchConditionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kk on 2017/6/28.
 */
@Service(value="deviceMessageService")
public class DeviceMessageServiceImpl implements IDeviceMessageService{
   @Resource
   private DevicemessageMapper deviceMessageMapper;

    @Override
    public List<Devicemessage> selectAllEquipmentMessage(ProductMessageSearchConditionVO productMessageSearchConditionVO) {
        return deviceMessageMapper.selectAllEquipmentMessage(productMessageSearchConditionVO);
    }

    @Override
    public List<String> selectAllEquipmentStatus() {
        return deviceMessageMapper.selectAllEquipmentStatus();
    }

    @Override
    public List<String> selectAllRecdepartment() {
        return deviceMessageMapper.selectAllRecdepartment();
    }

    @Override
    public Devicemessage selectBySerialnumber(String serialNumber) {
        return deviceMessageMapper.selectBySerialnumber(serialNumber);
    }

    @Override
    public int updateBySerialnumber(Devicemessage record) {
        return deviceMessageMapper.updateBySerialnumber(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return deviceMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Devicemessage record) {
        return deviceMessageMapper.insert(record);
    }

    @Override
    public int insertSelective(Devicemessage record) {
        return deviceMessageMapper.insertSelective(record);
    }

    @Override
    public Devicemessage selectByPrimaryKey(Integer id) {
        return deviceMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Devicemessage record) {
        return deviceMessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Devicemessage record) {
        return deviceMessageMapper.updateByPrimaryKey(record);
    }
}
