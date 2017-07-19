package com.yaoli.service;

import com.yaoli.beans.CarManage;
import com.yaoli.vo.huangkai.CarKMChangeDiaramSearchConditon;
import com.yaoli.vo.huangkai.CarManageSearchConditionVO;

import java.util.List;

/**
 * Created by kk on 2017/7/5.
 */
public interface ICarManageService {
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
