package com.yaoli.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Admin;
import com.yaoli.beans.AdminArea;
import com.yaoli.beans.Area;
import com.yaoli.service.IAdminService;
import com.yaoli.service.IAreaService;
import com.yaoli.service.ISewageService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.AreaVO;

@Controller
@RequestMapping("/areas")
public class AreaController {
	
	@Resource
	private IAreaService iAreaService;
	
	@Resource
	private ISewageService iSewageService;
	
	@Resource
	private IAdminService iAdminService;
	
	@RequestMapping("/gotoshowarea.do")
	public String gotoAreas(HttpServletRequest request,HttpServletResponse response){
		return "/areas/showareas";
	}
	
	@RequestMapping("/getareas.do")
	public void showAreas(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		List<AreaVO> areaList = iAreaService.getAreasByPaging(map);
		
		int count = iAreaService.getTotalAreaCount();
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(areaList);
		
		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoaddnewarea.do")
	public String gotoAddNewAreas(Model model){
		List<Admin> admins = iAdminService.getAllAdmins();
		model.addAttribute("admins", admins);
		return "/areas/addnewarea";
	}
	
	@RequestMapping("/addnewarea.do")
	public void addNewArea(@RequestBody AreaVO areaVO,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		//转换成Area
		Area area = mapper.map(areaVO, Area.class);
		int areaCount = iAreaService.insertSelective(area);
		
		//添加管理员 地区关系表记录
		AdminArea adminArea = new AdminArea();
		adminArea.setAdminid(Integer.valueOf(areaVO.getAdminId()));
		adminArea.setAreaid(area.getId());
		int adminAreaCount = iAreaService.insert(adminArea);
		
		if(areaCount != 1 && adminAreaCount != 1){
			throw new Exception("添加地区失败");
		}
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
	@RequestMapping("/getallareas.do")
	public void getAllAreas(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Area> areas = iAreaService.getAllAreas();
		String json = JSON.toJSONString(areas);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	@RequestMapping("/deleteseletedareas.do")
	public void deleteSeletedAdmin(@RequestBody AreaVO areaVO,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Integer> idList = areaVO.getSelectedIds();
		JsonMessage jsondata = new JsonMessage();
		
		//已经绑定地区id
		List<Integer> hasBindingAreas = new ArrayList<Integer>();
		for (int i = 0; i < idList.size(); i++) {
			//检查 是不是有污水站点跟他关联？
			int sewageCount = iSewageService.getSewageCountLinktoAreaBy(idList.get(i));
			if(sewageCount > 0){
				hasBindingAreas.add(idList.get(i));
				//不能 删除 因为 已经有污水站点绑定！
			}else {
				int count = iAreaService.deleteByPrimaryKey(idList.get(i));
				int count2 = iAreaService.deleteAdminAreaByAreaId(idList.get(i));
				if(count != 1 && count2 != 1){
					throw new Exception("删除地区失败！");
				}
				
			}
		}
		jsondata.setKey("pass");
		jsondata.setData(hasBindingAreas);
		
		
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
	@RequestMapping("/gotoupdatearea.do")
	public String gotoupdateArea(HttpServletRequest request,HttpServletResponse response,Model model){
		List<Admin> admins = iAdminService.getAllAdmins();
		model.addAttribute("admins", admins);

		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		Area area = iAreaService.selectByPrimaryKey(Integer.valueOf(areaid));
		model.addAttribute("area", area);
		
		AdminArea adminArea = iAreaService.getAdminAreaByAreaid(Integer.valueOf(areaid));
		model.addAttribute("adminArea", adminArea);
		
		return "/areas/addnewarea";
	}
	
	@RequestMapping("/updatearea.do")
	public void updateArea(@RequestBody AreaVO areaVO, HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		//转换成Area
		Area area = mapper.map(areaVO, Area.class);
		
		int count = iAreaService.updateByPrimaryKeySelective(area);
		
		int count1 = iAreaService.deleteAdminAreaByAreaId(area.getId());
		
		//插入新的用户 和 地区关系
		AdminArea adminArea = new AdminArea();
		adminArea.setAdminid(Integer.valueOf(areaVO.getAdminId()));
		adminArea.setAreaid(area.getId());
		int count2 = iAreaService.insert(adminArea);
		
		if(count != 1 && count1 != 1 && count2 != 1){
			throw new Exception("更新失败");
		}
		
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
}
