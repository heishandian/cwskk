package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.*;
import com.yaoli.service.*;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.util.huangkai.DateUtil;
import com.yaoli.vo.huangkai.CarKMChangeDiaramSearchConditon;
import com.yaoli.vo.huangkai.CarManageSearchConditionVO;
import com.yaoli.vo.huangkai.WorkGroupMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kk on 2017/6/27.
 */
@Controller
@RequestMapping(value = {"/operationsmanage"})
public class OperationsManageController {
    @Autowired
    private IWorkGroupMessageService iWorkGroupMessageService;

    @Autowired
    private IWorkGroupMemberService iWorkGroupMemberService;

    @Autowired
    private IGoodsOriginService iGoodsOriginService;

    @Autowired
    private IGroupWorkLoadService iGroupWorkLoadService;

    @Autowired
    private ICarManageService iCarManageService;
    //跳转到工作组管理界面

    @RequestMapping(value = "/gotoworkgroupmanagerpage.do")
    public String gotoWorkGroupManagerPage(HashMap<String, List<Goods_origin>> map) {
        List<Goods_origin> oringins = iGoodsOriginService.getAllGoods_origin();
        map.put("areas", oringins);
        return "huangkai/operationsmanage/workgroupmanager";
    }

    @RequestMapping(value = "/gotoworkloadinputpage.do")
    public String gotoWorkloadInputPage(HashMap<String, List<Goods_origin>> map) {
        List<Goods_origin> oringins = iGoodsOriginService.getAllGoods_origin();
        map.put("areas", oringins);
        return "huangkai/operationsmanage/workloadinput";
    }

    @RequestMapping(value = "/gotocarmanagepage.do")
    public String gotoCarManagePage(HashMap<String, List<GroupMessage>> map) {
        List<GroupMessage> groups = iWorkGroupMessageService.selectAllGroupMessage();
        map.put("groups", groups);
        return "huangkai/operationsmanage/carmanage";
    }

    @RequestMapping(value = "/gotocarkmchangediagrampage.do")
    public String gotoCarKMChangeDiagramPage(HashMap<String, List<GroupMessage>> map) {
        List<GroupMessage> groups = iWorkGroupMessageService.selectAllGroupMessage();
        map.put("groups", groups);
        return "huangkai/operationsmanage/carkmchangediagram";
    }

    @RequestMapping(value = "/{areaid}/getgroupnamebyareaid.do")
    public void getGroupNameByAreaId(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer areaid) throws IOException {
        List<GroupMessage> groups = iWorkGroupMessageService.selectByAreaId(areaid);
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setData(groups);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(JSON.toJSONString(jsonMessage)));
    }

    @RequestMapping(value = "/insertgroupworkload.do", method = {RequestMethod.POST})
    public void insertGroupWorkload(HttpServletRequest request, HttpServletResponse response, @RequestBody GroupWorkLoad groupWorkLoad) throws IOException {
        //1.如果不发生异常则插入成功，如果发生异常则通知插入失败，请重新插入
        String result = "success";
        try {
            iGroupWorkLoadService.insertSelective(groupWorkLoad);
        } catch (Exception e) {
            result = "failure";
            e.printStackTrace();
        }
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    //跳转到工作组管理界面
    @RequestMapping(value = "/isgroupnameexisted.do", method = {RequestMethod.GET})
    public void isGroupNameExisted(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean result = false;
        String groupName = request.getParameter("name");
        groupName = new String(groupName.getBytes("iso-8859-1"), "utf-8");
        if (iWorkGroupMessageService.selectByGroupName(groupName) != null) {
            result = true;
        }
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }


    @RequestMapping(value = "/insertworkgroupmessage.do")
    public void workGroupManager(HttpServletRequest request, HttpServletResponse response, @RequestBody WorkGroupMessageVO[] workGroupMessage) throws IOException {
        //1. 取出小组信息
        String result = "success";
        try {
            GroupMessage temp = null;
            for (int i = 0; i < workGroupMessage.length; i++) {
                if (i == 0) {
                    if (iWorkGroupMessageService.selectByGroupName(workGroupMessage[0].getGroupName()) == null) {
                        GroupMessage groupMessage = new GroupMessage(workGroupMessage[0].getGroupName(), workGroupMessage[0].getAreaId(), workGroupMessage[0].getGroupLeader(),
                                workGroupMessage[0].getMemberTelephone(), workGroupMessage[0].getCarType(), workGroupMessage[0].getCarNumber(), workGroupMessage[0].getRegion(), workGroupMessage[0].getNumberOfStation());
                        iWorkGroupMessageService.insertSelective(groupMessage);
                        temp = iWorkGroupMessageService.selectByGroupName(workGroupMessage[0].getGroupName());  // 根据工作组的名字查找出工作组的id
                    }
                }
                if (temp != null) {//插入工作组中的成员
                    if (workGroupMessage[i].getMemberName() != null && !"".equals(workGroupMessage[i].getMemberName())) {
                        GroupMember groupMember = new GroupMember(workGroupMessage[i].getMemberName(), workGroupMessage[i].getMemberPosition(),
                                workGroupMessage[i].getMemberTelephone(), temp.getId());
                        iWorkGroupMemberService.insertSelective(groupMember);
                    }
                }
            }
        } catch (Exception e) {
            result = "failure";
            e.printStackTrace();
        }
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }

    // 根据车牌号和时间查询数据库中是否存在记录，如果存在记录，提醒用户是否需要更新，或者说是取消操作
    @RequestMapping(value = "/iscarmanagemessageexisted.do", method = {RequestMethod.POST})
    public void isCarManageMessageExisted(HttpServletRequest request, HttpServletResponse response, @RequestBody CarManage carManage) throws IOException {
        JsonMessage jsonMessage = new JsonMessage();
        String key = "existed";
        CarManage carMessage = iCarManageService.isCarManageMessageExisted(carManage);//数据库中的信息
        if (carMessage == null) {//说明不存在
            key = "unexisted";
        }
        jsonMessage.setKey(key);
        jsonMessage.setData(carMessage);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(JSON.toJSONString(jsonMessage)));
    }

    @RequestMapping(value = "/insertcarmanagemessage.do", method = {RequestMethod.POST})
    public void insertCarManageMessage(HttpServletRequest request, HttpServletResponse response, @RequestBody CarManage carManage) throws IOException {
        String flag= request.getParameter("flag");
        String result = "success";
        BigDecimal startkm = new BigDecimal(0);
        try {
            if ("update".equals(flag)) { // 只是更新endtime
                iCarManageService.updateByPrimaryKey(carManage);
            } else {
                CarManage carMessage = iCarManageService.selectByCarNumber(carManage.getCarnumber());
                if (carMessage != null) {
                    startkm = carMessage.getEndkm();
                }
                carManage.setStartkm(startkm);
                iCarManageService.insertSelective(carManage); //插入
            }
        } catch (Exception e) {
            result = "failure";
            e.printStackTrace();
        }
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }


    //设备信息查询页面
    @RequestMapping(value = "/getcarmanagemessage.do")
    public void getCarManageMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String carnumber = String.valueOf(request.getParameter("carnumber")).equals("") ? null : String.valueOf(request.getParameter("carnumber"));
        String starttime = String.valueOf(request.getParameter("starttime")).equals("") ? null : String.valueOf(request.getParameter("starttime"));
        String endtime = String.valueOf(request.getParameter("endtime")).equals("") ? null : String.valueOf(request.getParameter("endtime"));
        //获取页码和每一页条数
        String page = String.valueOf(request.getParameter("page"));
        String rows = String.valueOf(request.getParameter("rows"));
        CarManageSearchConditionVO carManageSearchCondition = new CarManageSearchConditionVO(carnumber, starttime, endtime, Integer.parseInt(page), Integer.parseInt(rows));
        List<CarManage> carManages = iCarManageService.selectAllCarManageMessagesByPaging(carManageSearchCondition);

        SysPagingUtil sysPagingUtil = new SysPagingUtil();//包装结果发送给easyUI网格
        sysPagingUtil.setTotal(String.valueOf(iCarManageService.getAllCarManageMessageCount(carManageSearchCondition))); //获取总条数
        sysPagingUtil.setRows(carManages);  //
        response.setContentType("text/json; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(sysPagingUtil));
    }

    @RequestMapping(value = "/getcarkmchangediaramdata.do", method = RequestMethod.POST)
    public void getCarKMChangeDiaramData(HttpServletRequest request, HttpServletResponse response, @RequestBody CarKMChangeDiaramSearchConditon carKMChangeDiaramSearchConditon) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<CarManage> carKMs = iCarManageService.getAllCarKMByCondition(carKMChangeDiaramSearchConditon);
        int numberOfdays = DateUtil.getDaysOfMonth(sdf.parse(carKMChangeDiaramSearchConditon.getTime() + "-01"));
        Integer[] days = new  Integer[numberOfdays];//天数
        float[] kms = null;//每天对应的里程
        String[] drivers = null;//每天对应的司机
        String[] applications = null;//每天对应的司机
        if (carKMs.size() > 0) {
            System.out.println(carKMs.get(carKMs.size()-1).getTime());
            int maxDays = Integer.parseInt(carKMs.get(carKMs.size()-1).getTime().substring(8,10));//查询条件下，数据库中最大的天数
            kms = new  float[maxDays];//每天对应的里程
            drivers = new  String[maxDays];//每天对应的司机
            applications = new  String[maxDays];//每天对应的司机
        }
        for (int i = 1; i <= numberOfdays; i++) {
            days[i-1] = i;
        }
        Iterator iterator = carKMs.iterator();
        int Pointer = 0;
        CarManage temp = null;
        while( iterator.hasNext() ){
            temp = (CarManage)iterator.next();
            Pointer = Integer.parseInt((temp.getTime()).trim().substring(8,10));
            kms[Pointer-1] = Float.parseFloat(String.valueOf(temp.getEndkm()));//特别注意，这里获得的结束里程数，其实是数据库里的endtime - starttime 而不是数据库中的endtime ,
            drivers[Pointer-1] = temp.getDriver();                            // 这里是为了使用CarManage这个对象方便，直接用endtime 代表了行驶里程数，行驶里程数 = 结束里程 - 开始里程
            applications[Pointer-1] = temp.getApplication();
        }
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("days",days);
        map.put("kms",kms);
        map.put("drivers",drivers);
        map.put("applications",applications);
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setData(map);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(JSON.toJSONString(jsonMessage)));
    }

}