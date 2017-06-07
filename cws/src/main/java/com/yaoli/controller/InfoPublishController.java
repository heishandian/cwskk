package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.aspect.annotation.CustomerRequestBodyAnnotation;
import com.yaoli.aspect.annotation.CustomerValueObjectAnnotation;
import com.yaoli.aspect.annotation.FormAddTokenAnnotation;
import com.yaoli.aspect.annotation.FormCheckTokenAnnotation;
import com.yaoli.beans.*;
import com.yaoli.service.IInfoPublishService;
import com.yaoli.service.ISysUserService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.RequestParameterParse;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.InfoPublishVO;
import com.yaoli.vo.RequestParameterVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by will on 2016/11/14.
 */
@Controller
@RequestMapping("/infoPub")
public class InfoPublishController {
    @Resource
    public IInfoPublishService iInfoPublishService;

    @Resource
    private ISysUserService iSysUserService;

    @FormAddTokenAnnotation(description = "添加token")
    @RequestMapping("/gotoNoticePublish.do")
    public ModelAndView gotoNoticePublish(HttpServletResponse response, HttpServletRequest request,ModelAndView modelAndView){
        modelAndView.setViewName("/infoPublish/addNoticePublish");
        return modelAndView;
    }

    @FormCheckTokenAnnotation(description = "检查token")
    @CustomerRequestBodyAnnotation
    @RequestMapping("/addNoticePublish.do")
    public void addNoticePublish(@CustomerValueObjectAnnotation InfoPublishVO infoPublishVO, HttpServletResponse response, HttpServletRequest request) throws IOException {
        JsonMessage message = new JsonMessage();

        if(request.getSession().getAttribute("formcheck").equals("pass")){

            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            //转换成 InfoPublish
            InfoPublish infoPublish = mapper.map(infoPublishVO, InfoPublish.class);
            infoPublish.setInserttime(new Date());

            Subject subject = SecurityUtils.getSubject();
            //获取用户名
            String userLoginName = subject.getPrincipal().toString();
            //根据用户名获取用户id，用户查询
            SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);

            infoPublish.setPublisherid(sysUser.getId());

            int count = iInfoPublishService.insert(infoPublish);

            message.setKey("pass");

        }else{
            message.setKey("submited");
        }
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/gotoSearchNoticePublish.do")
    public String gotoSearchNoticePublish(Model model){
        return "/infoPublish/showSearchNoticePublish";
    }

    @RequestMapping("/showSearchNoticePublish.do")
    public void showSearchNoticePublish(HttpServletResponse response, HttpServletRequest request) throws Exception {
        RequestParameterVO vo = (RequestParameterVO)(new RequestParameterParse(RequestParameterVO.class,request).getParameterMap());


        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", vo.getRows());
        map.put("pageNum", vo.getPage());
        map.put("publisher", vo.getPublisher());
        map.put("begintime", vo.getBegintime());
        map.put("endtime", vo.getEndtime());

        List<InfoPublishVO> lists = iInfoPublishService.getInfoPublisherVO(map);

        int count = iInfoPublishService.getInfoPublisherVOCount(map);

        SysPagingUtil sysPagingUtil = new SysPagingUtil();
        sysPagingUtil.setTotal(String.valueOf(count));
        sysPagingUtil.setRows(lists);

        String jsondata = JSON.toJSONString(sysPagingUtil);

        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/getSearchNoticePublish.do")
    public void getSearchNoticePublish(HttpServletResponse response, HttpServletRequest request) throws Exception {
        List<InfoPublishVO> lists = iInfoPublishService.getSearchNoticePublish(null);

        String jsondata = JSON.toJSONString(lists);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/deleteSearchNoticePublishById.do")
    public void deleteSearchNoticePublishById(HttpServletResponse response, HttpServletRequest request) throws Exception {
        RequestParameterVO vo = (RequestParameterVO)(new RequestParameterParse(RequestParameterVO.class,request).getParameterMap());

        int count = iInfoPublishService.deleteByPrimaryKey(Integer.valueOf(vo.getId()));

        JsonMessage message = new JsonMessage();
        if(count == 1){
            message.setKey("pass");
        }else{
            message.setKey("error");
        }
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

}
