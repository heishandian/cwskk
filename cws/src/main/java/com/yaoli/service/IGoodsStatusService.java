package com.yaoli.service;

import com.yaoli.beans.Goods_status;

import java.util.List;

public interface IGoodsStatusService {
    List<Goods_status> getAllGoods_status();

    Goods_status selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_status record);

    int insertSelective(Goods_status record);

    Goods_status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_status record);

    int updateByPrimaryKey(Goods_status record);
}