package com.yaoli.service.impl;


import com.yaoli.beans.Goods_abnormal_type;
import com.yaoli.dao.Goods_abnormal_typeMapper;
import com.yaoli.service.IGoods_abnormal_type;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "goods_abnormal_typeService")
public class Goods_abnormal_typeServiceImpl implements IGoods_abnormal_type {
    @Resource
    private Goods_abnormal_typeMapper goods_abnormal_typeMapper;
    @Override
    public List<Goods_abnormal_type> getAllGoods_abnormal_type() {
        return goods_abnormal_typeMapper.getAllGoods_abnormal_type();
    }
}