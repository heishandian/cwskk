package com.yaoli.service;

import com.yaoli.beans.Goods;

import java.util.List;

/**
 * Created by kk on 2017/6/9.
 */
public interface IGoodsService {

    List<String> getAllGoodsNames();

    Goods selectGoodsByGoodsNameAndNumber(Goods goods);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}
