package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.RemoteControl;
import com.yaoli.beans.RunData;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IRemoteControlService;
import com.yaoli.service.IRunDataService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SendUpdateSewageIdTaskClient;
import com.yaoli.vo.RemoteControlVO;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * Created by will on 2016/10/17.
 */
@Controller
@RequestMapping("/remoteControl")
public class RemoteControlDeviceController {

    @Resource
    private IRunDataService iRunDataService;

    @Resource
    private IAreaService iAreaService;

    @Resource
    private IRemoteControlService iRemoteControlService;

    @RequestMapping("/gotoRemoteControllDevice.do")
    public String gotoRemoteControllDevice(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        List<Area> areas = iAreaService.getAllAreas();
        model.addAttribute("allAreas",areas);
        return "/monitorManager/remoteControlDevice";
    }

    @RequestMapping("/updateRemoteControllDevice.do")
    public void updateRemoteControllDevice(@RequestBody RemoteControlVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, InterruptedException {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

        final RemoteControl remoteControl = mapper.map(vo, RemoteControl.class);

        int runDataCount = 0;

        RunData runData = new RunData();
        runData.setTestingtime(new Date());
        runData.setSewageid(vo.getSewageid());
        runData.setEquipment1state(Byte.valueOf(String.valueOf(vo.getEquip1())));
        runData.setEquipment2state(Byte.valueOf(String.valueOf(vo.getEquip2())));
        runData.setEquipment3state(Byte.valueOf(String.valueOf(vo.getEquip3())));
        runData.setEquipment4state(Byte.valueOf(String.valueOf(vo.getEquip4())));
        runData.setEquipment5state(Byte.valueOf(String.valueOf(vo.getEquip5())));

        runDataCount = iRunDataService.insert(runData);


        RemoteControl rc = iRemoteControlService.selectByPrimaryKey(remoteControl.getSewageid());

        int count = 0;

        try {


            if(rc == null){
                count = iRemoteControlService.insert(remoteControl);
            }else{
                count = iRemoteControlService.updateByPrimaryKey(remoteControl);
            }
            // 0 表示关，1表示开
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //发送数据
                        SendUpdateSewageIdTaskClient.controlEquip(remoteControl);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } finally {
            JsonMessage jsonMessage  = new JsonMessage();
            jsonMessage.setKey("pass");
            String jsons = JSON.toJSONString(jsonMessage);
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(jsons);
        }

    }

    @RequestMapping("/getRemoteControllDeviceBySewageid.do")
    public void getRemoteControllDeviceBySewageid(@RequestBody RemoteControlVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        JsonMessage jsonMessage  = new JsonMessage();

        RemoteControl rc = iRemoteControlService.selectByPrimaryKey(vo.getSewageid());

        int count = 0;

        //如果为空 则直接 设置为set
        if(rc == null){
            jsonMessage.setKey("set");
        }else{
            jsonMessage.setKey("pass");
            jsonMessage.setData(rc);
        }

        String jsons = JSON.toJSONString(jsonMessage);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(jsons);
    }
}
