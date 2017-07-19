package com.yaoli.service.impl;

import com.yaoli.beans.CarManage;
import com.yaoli.dao.CarManageMapper;
import com.yaoli.service.ICarManageService;
import com.yaoli.vo.huangkai.CarKMChangeDiaramSearchConditon;
import com.yaoli.vo.huangkai.CarManageSearchConditionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kk on 2017/7/5.
 */
@Service("carManageService")
public class CarManageServiceImpl implements ICarManageService {
    @Resource
    private CarManageMapper carManageMapper;

    @Override
    public CarManage isCarManageMessageExisted(CarManage carManage) {
        return carManageMapper.isCarManageMessageExisted(carManage);
    }

    @Override
    public List<CarManage> getAllCarKMByCondition(CarKMChangeDiaramSearchConditon carKMChangeDiaramSearchConditon) {
        return carManageMapper.getAllCarKMByCondition(carKMChangeDiaramSearchConditon);
    }

    @Override
    public int getAllCarManageMessageCount(CarManageSearchConditionVO carManageSearchCondition) {
        return carManageMapper.getAllCarManageMessageCount(carManageSearchCondition);
    }

    @Override
    public List<CarManage> selectAllCarManageMessagesByPaging(CarManageSearchConditionVO carManageSearchCondition) {
        return carManageMapper.selectAllCarManageMessagesByPaging(carManageSearchCondition);
    }

    @Override
    public CarManage selectByCarNumber(String carNumber) {
        return carManageMapper.selectByCarNumber(carNumber);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return carManageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CarManage record) {
        return carManageMapper.insert(record);
    }

    @Override
    public int insertSelective(CarManage record) {
        return carManageMapper.insertSelective(record);
    }

    @Override
    public CarManage selectByPrimaryKey(Integer id) {
        return carManageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CarManage record) {
        return carManageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CarManage record) {
        return carManageMapper.updateByPrimaryKey(record);
    }
}
