package com.yaoli.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.service.IUserLoginRecordService;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.UserLoginRecordVO;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userlogin")
public class UserLoginRecordController {
	
	@Resource
	private IUserLoginRecordService iUserLoginRecordService;
	
	@RequestMapping("/gotogetuserloginrecord.do")
	public String gotogetuserloginrecord(){
		return "/userloginrecord/showuserloginrecord";
	}
	
	@RequestMapping("/getUserLoginRecord.do")
	public void getUserLoginRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
		String username = String.valueOf(request.getParameter("username")).equals("")?null:String.valueOf(request.getParameter("username"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("username", username);
		
		List<UserLoginRecordVO> userLoginRecordVOs = iUserLoginRecordService.getUserLoginRecord(map);
		int count = iUserLoginRecordService.getUserLoginRecordTotal(map);
		
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(userLoginRecordVOs);
		

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}

	/**
	 * 获取用户登录的次数
	 * @param vo
	 * @param request
	 * @param response
	 * @throws IOException
     */
	@RequestMapping("/getUserLoginTimes.do")
	public void getUserLoginTimes(@RequestBody UserLoginRecordVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, String> map = new HashMap<String, String>();

		map.put("begintime", vo.getBegintime().equals("") ? null : vo.getBegintime());
		map.put("endtime", vo.getEndtime().equals("") ? null : vo.getEndtime());
		map.put("username", vo.getUsername().equals("")  ? null : vo.getUsername());

		int times = iUserLoginRecordService.getUserLoginTimes(map);

		String jsondata = JSON.toJSONString("登录记录共计"+times+"次");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
