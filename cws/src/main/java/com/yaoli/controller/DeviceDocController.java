package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.DeviceDoc;
import com.yaoli.beans.Sewage;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDeviceDocService;
import com.yaoli.service.ISewageService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.DeviceDocVO;

@Controller
@RequestMapping("/device")
public class DeviceDocController {

	@Resource
	public IAreaService isAreaService;
	
	@Resource
	public IDeviceDocService iDeviceDocService;
	
	@Resource
	ISewageService iSewageService;
	
	@RequestMapping("/gotoshowdevicedoc.do")
	public String gotoShowDeviceDoc(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/device/showdevicedoc";
	}
	
	@RequestMapping("/gotoadddevicedoc.do")
	public String gotoAddDeviceDoc(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/device/addnewdevice";
	}
	
	@RequestMapping("/adddevicedoc.do")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void addDeviceDoc(@RequestBody DeviceDoc deviceDoc,HttpServletResponse response,HttpServletRequest request) throws IOException{
		int count = iDeviceDocService.insertSelective(deviceDoc);
		JsonMessage jsonMessage = new JsonMessage();
		
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/getdevicedoc.do")
	public void getDeviceDoc(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		
		List<DeviceDoc> deviceDoc = iDeviceDocService.selectByPaingAndCondition(map);
		int count = iDeviceDocService.getPaingAndConditionTotalCountBySewageid(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(deviceDoc);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/deleteDeviceDocById.do")
	public void deleteDeviceDocById(@RequestBody DeviceDocVO deviceDocVO, HttpServletRequest request,HttpServletResponse response) throws IOException{
		JsonMessage jsonMessage = new JsonMessage();
		
		int count = iDeviceDocService.deleteByPrimaryKey(deviceDocVO.getId());
		
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		String jsondata = JSON.toJSONString(jsonMessage);
		
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoUpdateDeviceDoc.do")
	public String gotoUpdateDeviceDoc(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		String sewageid = String.valueOf(request.getParameter("id")).equals("")?null:String.valueOf(request.getParameter("id"));
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);

		DeviceDoc deviceDoc = iDeviceDocService.selectByPrimaryKey(Long.valueOf(sewageid));
		model.addAttribute("deviceDoc", deviceDoc);
		
		List<Sewage> sewages = iSewageService.getAllSewages();
		
		for (Sewage sewage : sewages) {
			if(sewage.getSewageid().equals(deviceDoc.getSewageid())){
				model.addAttribute("areaid", sewage.getAreaid());
			}
		}
		
		return "/device/addnewdevice";
	}
	
	
	@RequestMapping("/updateDeviceDoc.do")
	public void updateDeviceDoc(@RequestBody DeviceDoc deviceDoc, HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		int count = iDeviceDocService.updateByPrimaryKeySelective(deviceDoc);
		JsonMessage jsonMessage = new JsonMessage();
		
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
		
	}
	
	

	
}
