package com.yaoli.service;

import com.yaoli.beans.GroupWorkLoad;

/**
 * Created by kk on 2017/7/4.
 */
public interface IGroupWorkLoadService {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupWorkLoad record);

    int insertSelective(GroupWorkLoad record);

    GroupWorkLoad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupWorkLoad record);

    int updateByPrimaryKey(GroupWorkLoad record);
}
