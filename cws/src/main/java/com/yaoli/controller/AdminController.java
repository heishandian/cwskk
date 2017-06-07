package com.yaoli.controller;

import java.io.IOException;
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
import com.yaoli.beans.Admin;
import com.yaoli.service.IAdminService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.AdminVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	IAdminService iAdminService;
	
	@RequestMapping("/getalladmins.do")
	public void getAdminsByPaing(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Admin> admins = iAdminService.getAllAdmins();
		String json = JSON.toJSONString(admins);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	@RequestMapping("/getAdminsByPaing.do")
	public void getGridData(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		List<Admin> list= iAdminService.getAdminsByPaing(map);
		int count = iAdminService.getTotalCount();
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);

		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoshowadmin.do")
	public String gotoShowAdmin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "/admin/showadmins";
	}
	
	@RequestMapping("/gotoaddnewadmin.do")
	public String gotoAddNewAdmin(){
		return "/admin/addnewadmin";
	}
	
	@RequestMapping("/addnewadmin.do")
	public void addNewaAdmin(@RequestBody Admin admin,HttpServletResponse response) throws Exception{
		int count = iAdminService.insert(admin);
		if(count != 1){
			throw new Exception("添加区域管理员失败");
		}
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.setContentType("html/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/deleteseletedadmin.do")
	public void deleteSeletedAdmin(@RequestBody AdminVO adminVO,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Integer> idList = adminVO.getSelectedIds();
		for (int i = 0; i < idList.size(); i++) {
			int count = iAdminService.deleteByPrimaryKey(idList.get(i));
			if(count != 1){
				throw new Exception("删除管理员失败！");
			}
		}
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
}
