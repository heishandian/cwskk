package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Devicemessage;
import com.yaoli.service.impl.DeviceMessageServiceImpl;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.huangkai.ProductMessageSearchConditionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kk on 2017/6/27.
 */
@Controller
@RequestMapping(value = {"/producttracking"})
public class ProducttrackingController {
    @Autowired
    private DeviceMessageServiceImpl deviceMessageService;

    //跳转到产品信息页面
    @RequestMapping(value="/{flag}/gotoequipmentmessagepage.do",method = RequestMethod.GET)
    public String gotoEquipmentMessagePage(@PathVariable("flag") String flag,HashMap<String,String> map) {
        if("equipassemble".equals(flag)){//设备组装页面
            map.put("flag","equipassemble");
        } else if("equiptest".equals(flag)){//设备测试页面
            map.put("flag","equiptest");
        } else if("equipentry".equals(flag)){//设备入库页面
            map.put("flag","equipentry");
        }else if("equipdelivery".equals(flag)){//设备出库页面
            map.put("flag","equipdelivery");
        }else if("equipinstall".equals(flag)){//设备安装页面
            map.put("flag","equipinstall");
        }
        return "huangkai/producttracking/equipmentmessage";
    }

    //跳转到产品溯源页面
    @RequestMapping(value="/gotoequipmentmessagesearchpage.do",method = RequestMethod.GET)
    public String gotoEquipmentMessageSearch(HashMap<String,List<String>> map) {
       //1.获取设备状态和领用部门
        List<String> equipmentsStatus = deviceMessageService.selectAllEquipmentStatus();
        List<String> recdepartment = deviceMessageService.selectAllRecdepartment();

        //2.包装早model中返回
        map.put("equipmentsStatus",equipmentsStatus);
        map.put("recdepartment",recdepartment);
        return "huangkai/producttracking/equipmentmessagesearch";
    }


    //设备信息更新页面
    @RequestMapping(value="/updateproductmessage.do")
    public void updateEquipentMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody Devicemessage devicemessage) throws IOException {
       //1.在待测试状态下 新增设备基本信息， 在其他状态之下更新设备的基本信息
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setKey("success");
        try{
            if (devicemessage.getCurrentstatus().equals("待测试")) {
                if (deviceMessageService.selectBySerialnumber(devicemessage.getSerialnumber()) != null ){//如果数据库中已经存在设备信息，那么则更新现在有设备信息
                    deviceMessageService.updateBySerialnumber(devicemessage);
                } else {
                    deviceMessageService.insertSelective(devicemessage);
                }
            } else {
                // 根据序列号更新设备信息
                deviceMessageService.updateBySerialnumber(devicemessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMessage.setKey("failure");
        }
        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonMessage));
    }


    //设备信息查询页面
    @RequestMapping(value="/{serialnumber}/getequipemntmessage.do")
    public void getEquipemntMessage(HttpServletRequest request, HttpServletResponse response, @PathVariable("serialnumber") String serialnumber) throws IOException {
        Devicemessage devicemessage = null;
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setKey("success");
        try{
            devicemessage = deviceMessageService.selectBySerialnumber(serialnumber);
            jsonMessage.setData(devicemessage);
        }catch(Exception e) {
           e.printStackTrace();
            jsonMessage.setKey("failure");
       }
       if ( devicemessage == null) {
           jsonMessage.setKey("norecord");
       }
        System.out.println(devicemessage);
        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonMessage));
    }

    //设备信息查询页面
    @RequestMapping(value="/getallequipmentmessage.do")
    public void getAllEquipmentMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String serialnumber = String.valueOf(request.getParameter("serialnumber")).equals("") ? null : String.valueOf(request.getParameter("serialnumber"));
        String equipmentsstatus = String.valueOf(request.getParameter("equipmentsstatus")).equals("") ? null : String.valueOf(request.getParameter("equipmentsstatus"));
        String recdepartment = String.valueOf(request.getParameter("recdepartment")).equals("") ? null : String.valueOf(request.getParameter("recdepartment"));
        ProductMessageSearchConditionVO productMessageSearchConditionVO = new ProductMessageSearchConditionVO(serialnumber,equipmentsstatus,recdepartment);
        List<Devicemessage> equipmentMessages = deviceMessageService.selectAllEquipmentMessage(productMessageSearchConditionVO);
        SysPagingUtil sysPagingUtil = new SysPagingUtil();//包装结果发送给easyUI网格
        sysPagingUtil.setTotal(String.valueOf(12));
        sysPagingUtil.setRows(equipmentMessages);
        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(sysPagingUtil));
    }
}