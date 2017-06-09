package com.yaoli.service.impl;

import com.yaoli.beans.Goods_status;
import com.yaoli.dao.Goods_statusMapper;
import com.yaoli.service.IGoods_status;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value="goods_statusService")
public class Goods_statusServiceImpl implements IGoods_status {
    @Resource
    private Goods_statusMapper goods_statusMapper;
    @Override
    public List<Goods_status> getAllGoods_status() {
        return goods_statusMapper.getAllGoods_status();
    }
}