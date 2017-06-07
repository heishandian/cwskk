package com.yaoli.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.Message;
import com.yaoli.beans.MessageLinker;
import com.yaoli.beans.SerialBean;
import com.yaoli.beans.SysMenu;
import com.yaoli.beans.SysParam;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.service.IMessageLinkerService;
import com.yaoli.service.IMessageService;
import com.yaoli.service.IRunDataService;
import com.yaoli.service.ISysParamService;
import com.yaoli.util.InfoUtil;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.MessageLinkerVO;
import com.yaoli.vo.MessageSettingVO;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	private static Logger logger = Logger.getLogger(MessageController.class);
	@Resource
	private IAreaService iAreaService;
	
	@Resource
	private IMessageLinkerService iMessageLinkerService;
	
	@Resource
	private ISysParamService iSysParamService;
	
	@RequestMapping("/gotolinkermanager.do")
	public String gotoLinkerManager(Model model){
		return "/message/linkermanager";
	}
	
	@Resource
	private IMessageService iMessageService;
	
	
	@RequestMapping("/gotosettingparameter.do")
	public String gotoSettingParameter(Model model){
		//获取intervaltime 主键 为4
		SysParam sysParam = iSysParamService.selectByPrimaryKey(4);
		//获取messagesendbegintime 主键 为6
		SysParam messagesendbegintime = iSysParamService.selectByPrimaryKey(6);
		//获取messagesendendtime 主键 为7
		SysParam messagesendendtime = iSysParamService.selectByPrimaryKey(7);
		
		model.addAttribute("model", sysParam);
		model.addAttribute("messagesendbegintime", messagesendbegintime);
		model.addAttribute("messagesendendtime", messagesendendtime);
		return "/message/settingparameter";
	}
	
	@RequestMapping("/getlinkers.do")
	public void getLinkers(@RequestBody MessageLinkerVO linkerVO, HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException{
		List<MessageLinker> messageLinkers = iMessageLinkerService.getMessageLinkers(String.valueOf(linkerVO.getAreaid()));
		Class<?> messageLinkerVOClass = Class.forName("com.yaoli.vo.MessageLinkerVO");
		MessageLinkerVO linkerVO2 = new MessageLinkerVO();
		for (int i = 0; i < messageLinkers.size(); i++) {
			Field linker = messageLinkerVOClass.getDeclaredField("linker"+(i+1));
			linker.setAccessible(true);
			linker.set(linkerVO2, messageLinkers.get(i).getName());
			
			Field linktel = messageLinkerVOClass.getDeclaredField("linkertel"+(i+1));
			linktel.setAccessible(true);
			linktel.set(linkerVO2, messageLinkers.get(i).getTel());
		}
		
		String jsons = JSON.toJSONString(linkerVO2);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	@RequestMapping("/addlinkers.do")
	public void addLinkers(@RequestBody MessageLinkerVO linkerVO, HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		Class<?> messageLinkerVOClass = Class.forName("com.yaoli.vo.MessageLinkerVO");
		
		iMessageLinkerService.deleteMessagerLinkerByAreaid(String.valueOf(linkerVO.getAreaid()));
		for (int i = 1; i <=5 ; i++) {
			String linker ;
			String linkertel;
			
			Method getLinker = messageLinkerVOClass.getDeclaredMethod("getLinker"+i);
			linker = getLinker.invoke(linkerVO).equals("")?null:String.valueOf(getLinker.invoke(linkerVO));
			
			Method getLinkerTel = messageLinkerVOClass.getDeclaredMethod("getLinkertel"+i);
			linkertel = getLinkerTel.invoke(linkerVO).equals("")?null:String.valueOf(getLinkerTel.invoke(linkerVO));
			
			if(linkertel != null){
				MessageLinker messageLinker = new MessageLinker();
				messageLinker.setAreaid(String.valueOf(linkerVO.getAreaid()));
				messageLinker.setName(linker);
				messageLinker.setTel(linkertel);
				
				iMessageLinkerService.insertSelective(messageLinker);
			}
		}
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/getabnormaltype.do")
	public void getAbnormalType(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<SysParam> sysParams = iSysParamService.getAbnormalType("abnormaltype");
		List<SysMenu> abnormaltypeList = new ArrayList<SysMenu>();
		SysMenu sysMenu =null;
		for (SysParam sysParam2 : sysParams) {
			sysMenu = new SysMenu();
			sysMenu.setId(sysParam2.getId());
			sysMenu.setName(sysParam2.getValue());
			
			if(sysParam2.getRemark() != null){
				if(sysParam2.getRemark().equals("true")){
					sysMenu.setChecked(true);
				}else {
					sysMenu.setChecked(false);
				}
			}
			abnormaltypeList.add(sysMenu);
			
		}
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setData(abnormaltypeList);
		
		logger.debug(JSON.toJSONString(jsonMessage));
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/getsendarea.do")
	public void getSendArea(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Area> areas = iAreaService.getAllAreas();
		List<SysMenu> abnormaltypeList = new ArrayList<SysMenu>();
		SysMenu sysMenu =null;
		for (Area area : areas) {
			sysMenu = new SysMenu();
			sysMenu.setId(area.getId());
			sysMenu.setName(area.getName());
			
			if(area.getIssendarea() != null){
				if(area.getIssendarea().equals("true")){
					sysMenu.setChecked(true);
				}else {
					sysMenu.setChecked(false);
				}
			}
			abnormaltypeList.add(sysMenu);
			
		}
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setData(abnormaltypeList);
		
		logger.debug(JSON.toJSONString(jsonMessage));
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/addmessagesetting.do")
	public void addMessageSetting(@RequestBody MessageSettingVO  messageSettingVO,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String intervaltime = messageSettingVO.getIntervaltime();
		List<Integer> a = messageSettingVO.getAbnormaltypeIds();
		List<Integer> b = messageSettingVO.getAreaIds();
		
		iSysParamService.updateAbnormalTypeToFalse();
		iAreaService.updateSendAreaToFalse();
		
		iSysParamService.updateIntervaltime(intervaltime);
		
		iSysParamService.updateMessagesendbegintime(messageSettingVO.getMessagesendbegintime());
		iSysParamService.updateMessagesendendtime(messageSettingVO.getMessagesendendtime());
		
		for (int i = 0; i < a.size(); i++) {
			iSysParamService.updateAbnormalTypeToTrueById(a.get(i).toString());
		}
		for (int i = 0; i < b.size(); i++) {
			iAreaService.updateSendAreaToTrueByAreaid(b.get(i).toString());
		}
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		
		logger.debug(JSON.toJSONString(jsonMessage));
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/gototestmessage.do")
	public String gotoTestMessage(){
		return "/message/testmessage";
	}
	
	@Resource
	private IRunDataService iRunDataService;
	
	@Resource
	private IDetectionDataService iDetectionDataService;
	
	@RequestMapping("/testmessage.do")
	public void testMessage(@RequestBody MessageSettingVO messageSettingVO, HttpServletRequest request,HttpServletResponse response) throws Exception{
		JsonMessage jsonMessage = new JsonMessage();
		Message message = new Message();
		//SendMessageTask.putMessageToQueue(message);
		
		try {
			SerialBean serialBean = new SerialBean(Integer.valueOf(messageSettingVO.getSerialPort()));
			//初始化
			serialBean.initialize();
			
			InfoUtil.sendMessage(serialBean, messageSettingVO.getSendTelelphone(), messageSettingVO.getMessageContent());
			
			serialBean.closePort();
			jsonMessage.setKey("pass");
		} catch (Exception e) {
			jsonMessage.setKey("error");
			e.printStackTrace();
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
		
	}
	
	@RequestMapping("/gotoshowmessages.do")
	public String gotoShowMessagaes(Model model){
		return "/message/showmessages";
	}
	
	@RequestMapping("/getmessages.do")
	public void getMessages(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
		String tel = String.valueOf(request.getParameter("tel")).equals("")?null:String.valueOf(request.getParameter("tel"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("tel", tel);
		
		List<Message> list= iMessageService.selectByPaingAndCondition(map);// irolesService.getRolesByPaging(map);
		int count = iMessageService.selectByPaingAndConditionCount(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);
		

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
