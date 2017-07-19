package com.yaoli.dao;

import com.yaoli.beans.CarManage;
import com.yaoli.vo.huangkai.CarKMChangeDiaramSearchConditon;
import com.yaoli.vo.huangkai.CarManageSearchConditionVO;

import java.util.List;

public interface CarManageMapper {
    CarManage isCarManageMessageExisted(CarManage carManage);

    List<CarManage> getAllCarKMByCondition(CarKMChangeDiaramSearchConditon carKMChangeDiaramSearchConditon);

    int getAllCarManageMessageCount(CarManageSearchConditionVO carManageSearchCondition);

    List<CarManage> selectAllCarManageMessagesByPaging(CarManageSearchConditionVO carManageSearchCondition);

    CarManage selectByCarNumber(String carNumber);

    int deleteByPrimaryKey(Integer id);

    int insert(CarManage record);

    int insertSelective(CarManage record);

    CarManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarManage record);

    int updateByPrimaryKey(CarManage record);
}