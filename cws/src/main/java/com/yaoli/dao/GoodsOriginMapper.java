package com.yaoli.dao;

import com.yaoli.beans.Goods_origin;

import java.util.List;

public interface GoodsOriginMapper {
    List<Goods_origin> getAllGoods_origin();

    Goods_origin selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_origin record);

    int insertSelective(Goods_origin record);

    Goods_origin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_origin record);

    int updateByPrimaryKey(Goods_origin record);
}