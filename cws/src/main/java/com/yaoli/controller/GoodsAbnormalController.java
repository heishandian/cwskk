package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Goods;
import com.yaoli.beans.Goods_abnormal_type;
import com.yaoli.beans.Goods_origin;
import com.yaoli.beans.Goods_status;
import com.yaoli.service.impl.GoodsServiceImpl;
import com.yaoli.service.impl.Goods_abnormal_typeServiceImpl;
import com.yaoli.service.impl.Goods_originServiceImpl;
import com.yaoli.service.impl.Goods_statusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kk on 2017/6/8. /goods_abnoral/gotogoods_abnoralinputpage.do
 */
@Controller
@RequestMapping(value={"/goods_abnoral"})
public class GoodsAbnormalController {
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;
    @Autowired
    private Goods_abnormal_typeServiceImpl goods_abnormal_typeServiceImpl;
    @Autowired
    private Goods_originServiceImpl goods_originServiceImpl;
    @Autowired
    private Goods_statusServiceImpl goods_statusServiceImpl;

    @RequestMapping(value={"/gotogoods_abnoralinputpage.do"})
    public String test(){
        return "huangkai/goodsabnormal";
    }

   @RequestMapping(value={"/getselectdata.do"})
    public void getSelectData(HttpServletRequest request, HttpServletResponse response)throws IOException{
       //1.从数据库中获取物品名称、物品编码、物品来源、故障类型
       List<Goods> goods = goodsServiceImpl.getAllGoods();
       List<Goods_origin> goods_origins = goods_originServiceImpl.getAllGoods_origin();
       List<Goods_status> goods_statuss = goods_statusServiceImpl.getAllGoods_status();
       List<Goods_abnormal_type> goods_abnormal_types= goods_abnormal_typeServiceImpl.getAllGoods_abnormal_type();

       //2.将这些数据封装成JSON格式发到goodsabnormal.jsp页面
       Map<String,Object> selectData = new HashMap<String,Object>();
       selectData.put("goods",goods);
       selectData.put("goods_origins",goods_origins);
       selectData.put("goods_statuss",goods_statuss);
       selectData.put("goods_abnormal_types",goods_abnormal_types);
       response.setContentType("text/html; charset=UTF-8");
       response.getWriter().write(JSON.toJSONString(selectData));
    }

}
