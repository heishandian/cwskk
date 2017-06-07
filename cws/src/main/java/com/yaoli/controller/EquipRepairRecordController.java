package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.DeviceDoc;
import com.yaoli.beans.EquipRepairRecord;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDeviceDocService;
import com.yaoli.service.IEquipRepairRecordService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.EquipRepairRecordVO;

@Controller
@RequestMapping("/equiprepairrecord")
public class EquipRepairRecordController {
	@Resource
	public IAreaService isAreaService;
	
	@Resource
	public IDeviceDocService iDeviceDocService;
	
	@Resource
	public IEquipRepairRecordService iEquipRepairRecordService;
	
	@RequestMapping("/gotoequiprepairrecord.do")
	public String gotoEquipRepairRecord(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		
		List<DeviceDoc> deviceDocs = iDeviceDocService.getAllDeviceDocs();
		model.addAttribute("deviceDocs", deviceDocs);
		return "/equiprepairrecord/addnewrepairrecord";
	}
	
	@RequestMapping("/addequiprepairrecord.do")
	public void addEquipRepairRecord(@RequestBody EquipRepairRecord record,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int count = iEquipRepairRecordService.insertSelective(record);
		JsonMessage jsonMessage = new JsonMessage();
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/showrepaired.do")
	public String showrepaired(HttpServletRequest request,HttpServletResponse response,Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/equiprepairrecord/showrepairrecord";
	}
	
	@RequestMapping("/getrepairedrecord.do")
	public void getRepairedRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
		String repairman = String.valueOf(request.getParameter("repairman")).equals("")?null:String.valueOf(request.getParameter("repairman"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("repairman", repairman);
		
		
		List<EquipRepairRecordVO> list = iEquipRepairRecordService.selectByPaingAndCondition(map);//selectByPaingAndCondition(map);//iDeviceDocService.selectByPaingAndCondition(map);
		int count = iEquipRepairRecordService.selectByPaingAndConditionTotalCount(map);//getPaingAndConditionTotalCountBySewageid(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);

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
	@RequestMapping("/getEquipRepairedTimes.do")
	public void getEquipRepairedTimes(@RequestBody EquipRepairRecordVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, String> map = new HashMap<String, String>();

		String beginDateTimeString = "";
		String endDateTimeString = "";
		if(vo.getBegintime() !=null){
			DateTime begintime = new DateTime(vo.getBegintime());
			beginDateTimeString = begintime.toString("yyyy-MM-dd");
		}
		if(vo.getEndtime() != null){
			DateTime endtime = new DateTime(vo.getEndtime());
			beginDateTimeString = endtime.toString("yyyy-MM-dd");
		}

		map.put("begintime", beginDateTimeString.equals("") ? null : beginDateTimeString);
		map.put("endtime", endDateTimeString.equals("") ? null : endDateTimeString);
		map.put("sewageid", String.valueOf(vo.getSewageid()).equals("null")  ? null : String.valueOf(vo.getSewageid()));
		map.put("areaid", String.valueOf(vo.getAreaid()).equals("null")  ? null : String.valueOf(vo.getAreaid()));
		map.put("repairman", vo.getRepairman().equals("")  ? null : vo.getRepairman());

		int times = iEquipRepairRecordService.getEquipRepairedTimes(map);

		String jsondata = JSON.toJSONString("共"+times+"条记录");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);

	}

	/**
	 * 根据id删除
	 * @param request
	 * @param response
	 * @throws IOException
     */
	@RequestMapping("/ajaxdeleteByid.do")
	public void ajaxdeleteByid(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer count = iEquipRepairRecordService.deleteByPrimaryKey(Long.valueOf(request.getParameter("id")==null?"-1":request.getParameter("id")));

		JsonMessage jsonMessage = new JsonMessage();

		if(count == 1){
			jsonMessage.setKey("pass");
		}else {
			jsonMessage.setKey("error");
		}

		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
}
