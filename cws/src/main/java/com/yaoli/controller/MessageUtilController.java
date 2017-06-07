package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.yaoli.message.MessageUtil;
import com.yaoli.message.SerialMessageBean;
import com.yaoli.message.SerialUtilBean;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IMessageLinkerService;
import com.yaoli.service.IMessageService;
import com.yaoli.service.ISysParamService;
import com.yaoli.util.JsonMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/11.
 */
@Controller("/messageUtil")
@RequestMapping("/messageUtil")
public class MessageUtilController {
    @Resource
    private IAreaService iAreaService;

    @Resource
    private IMessageLinkerService iMessageLinkerService;

    @Resource
    private ISysParamService iSysParamService;

    @Resource
    private IMessageService iMessageService;

    /**
     * 发送短信
     * @param bean
     * @param request
     * @param response
     */
    @RequestMapping("/sendSerialMessage.do")
    public void sendSerialMessage(@RequestBody SerialMessageBean bean, HttpServletRequest request, HttpServletResponse response){

        SerialUtilBean serialUtilBean = new SerialUtilBean();
        serialUtilBean.initialize();
        //MessageUtil.sendMessage(bean, "15306174917","江都 出现的了 断电断线 下线");
        try {
            MessageUtil.sendMessage(serialUtilBean,bean.getTelephone(),bean.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        serialUtilBean.closePort();

        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setKey("pass");
        response.setContentType("text/html; charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJSONString(jsonMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param bean
     * @param request
     * @param response
     */
    @RequestMapping("/getAllSerialMessage.do")
    public void getAllSerialMessage(@RequestBody SerialMessageBean bean, HttpServletRequest request, HttpServletResponse response){


    }




}
