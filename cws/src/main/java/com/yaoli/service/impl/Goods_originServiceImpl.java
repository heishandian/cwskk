package com.yaoli.service.impl;

import com.yaoli.beans.Goods_origin;
import com.yaoli.dao.Goods_originMapper;
import com.yaoli.service.IGoods_origin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "goods_originService")
public class Goods_originServiceImpl implements IGoods_origin {
    @Resource
    private Goods_originMapper goods_originMapper;
    @Override
    public List<Goods_origin> getAllGoods_origin() {
        return goods_originMapper.getAllGoods_origin();
    }
}