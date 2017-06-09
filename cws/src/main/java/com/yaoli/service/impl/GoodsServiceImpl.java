package com.yaoli.service.impl;

import com.yaoli.beans.Goods;
import com.yaoli.dao.GoodsMapper;
import com.yaoli.service.IGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kk on 2017/6/9.
 */
@Service(value="goodsService")
public class GoodsServiceImpl implements IGoodsService{
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }
}
