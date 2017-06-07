package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysUser;
import com.yaoli.beans.SysUserRole;
import com.yaoli.service.ISysRoleService;
import com.yaoli.service.ISysUserRoleService;
import com.yaoli.service.ISysUserService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.SysUserVO;

@Controller
@RequestMapping("/users")
public class SysUserController {
	
	@Resource
	private ISysUserService iSysUserService;
	
	@Resource
	private ISysRoleService iSysRoleService;
	
	@Resource
	private ISysUserRoleService iSysUserRoleService;
	
	@RequestMapping("/showusers.do")
	public String showUserList(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		return "/users/showusers";
		//return "/pages/users/showusers2";
	}
	
	@RequestMapping("/getuserdata.do")
	public void getGridData(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		List<SysUserVO> list= iSysUserService.getAllUserWithRoleName(map);
		int count = iSysUserService.getTotalCount();
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/deleteselectedusers.do")
	public void deleteSelectedUsers(@RequestBody SysUserVO sysUserVO,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Integer>  ids = sysUserVO.getSelectIds();
		
		for (int i = 0; i < ids.size(); i++) {
			//在这里删除用户 在sysuser表中删除用户
			int deleteUserCount = iSysUserService.deleteByPrimaryKey(ids.get(i));
			//在sysuserrole表中 删除用户
			int deleteUserRoleCount = iSysUserRoleService.deleteUserRoleByUserId(ids.get(i));
			if(deleteUserCount != 1 && deleteUserRoleCount !=1){
				throw new Exception("删除用户失败");
			}
		}
		response.setContentType("html/json;charset=UTF-8");
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/gotoaddnewusers.do")
	public String gotoAddNewUsers(HttpServletRequest request,HttpServletResponse response,Model model){
		List<SysRole> roles = iSysRoleService.getAllRoles();
		model.addAttribute("roles", roles);
		return "/users/addnewusers";
	}
	
	@RequestMapping("/addnewusers.do")
	public void addNewUsers(@RequestBody SysUserVO sysUserVO,HttpServletResponse response,HttpServletRequest request) throws Exception{
		//采用dozer转换对象
		SysUser sysUser = new SysUser();
		sysUser.setDepartment(sysUserVO.getDepartment());
		//初始密码与登录名一致
		sysUser.setLoginpwd(sysUserVO.getLoginusername());
		sysUser.setLoginname(sysUserVO.getLoginusername());
		sysUser.setTelephone(sysUserVO.getTelephone());
		sysUser.setUseremail(sysUserVO.getUseremail());
		sysUser.setUsername(sysUserVO.getUsername());
		//以上是封装对象封装代码
		int userCount = iSysUserService.insert(sysUser);
		
		if(userCount != 1){
			throw new Exception("插入用户发生错误");
		}
		
		int roleid = sysUserVO.getRoleid();
		
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setRoleid(roleid);
		sysUserRole.setUserid(sysUser.getId());
		
		int roleCount = iSysUserRoleService.insert(sysUserRole);
		if(roleCount != 1){
			throw new Exception("插入用户角色发生错误！");
		}
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
		
	}
	
	@RequestMapping("/getallroles.do")
	public void getAllRoles(HttpServletResponse response) throws IOException{
		List<SysRole> list = iSysRoleService.getAllRoles();
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(list)));
	}
	
	@RequestMapping("/ajaxcheckexist.do")
	public void ajaxCheckExist(@RequestBody SysUserVO sysUserVO,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name = sysUserVO.getLoginusername();
		int count = iSysUserService.selectUserCountByUserloginname(name);
		JsonMessage jsonMessage = new JsonMessage();
		
		if(count == 0){
			jsonMessage.setKey("pass");
		}else {
			jsonMessage.setKey("exist");
		}
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	@Deprecated
	@RequestMapping("/ajaxallowupdateloginname.do")
	public void ajaxAllowUpdateLoginname(@RequestBody SysUserVO sysUserVO,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String name = sysUserVO.getLoginusername();
		int count = iSysUserService.selectUserCountByUserloginname(name);
		JsonMessage jsonMessage = new JsonMessage();
		
		if(count == 0){
			jsonMessage.setKey("pass");
		}else {
			jsonMessage.setKey("exist");
		}
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	
	
	@RequestMapping("/updatepassword.do")
	public void updatePassword(@RequestBody SysUserVO sysUserVO ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		//获取用户名
		String userLoginName = subject.getPrincipal().toString();
		//根据用户名获取用户id，用户查询
		SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
		
		sysUser.setLoginpwd(sysUserVO.getLoginuserpwd());
		
		int count = iSysUserService.updateByPrimaryKeySelective(sysUser);
		if(count != 1){
			throw new Exception("更新用戶密碼錯誤");
		}
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	@RequestMapping("/updateuserinfo.do")
	public void updateUserInfo(@RequestBody SysUserVO sysUserVO,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		//获取用户名
		String userLoginName = subject.getPrincipal().toString();
		//根据用户名获取用户id，用户查询
		SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
		
		//sysUser.setLoginname(sysUserVO.getLoginusername());
		sysUser.setUsername(sysUserVO.getUsername());
		sysUser.setDepartment(sysUserVO.getDepartment());
		sysUser.setTelephone(sysUserVO.getTelephone());
		
		int count = iSysUserService.updateByPrimaryKeySelective(sysUser);
		if(count != 1){
			throw new Exception("更新用戶信息錯誤");
		}
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	@RequestMapping("/gotoupdateuserinfo.do")
	public String gotoUpdateUserInfo(Model model){
		Subject subject = SecurityUtils.getSubject();
		//获取用户名
		String userLoginName = subject.getPrincipal().toString();
		//根据用户名获取用户id，用户查询
		SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
		model.addAttribute("userinfo", sysUser);
		return "/users/updateuserinfo";
	}
	@RequestMapping("/gotoupdatepassword.do")
	public String gotoUpdateUserPwd(){
		return "/users/updateuserpwd";
	}
	
	
	@RequestMapping("/ajaxcheckpwdright.do")
	public void ajaxCheckPwdRight(@RequestBody SysUserVO sysUserVO,HttpServletResponse response) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		//获取用户名
		String userLoginName = subject.getPrincipal().toString();
		//根据用户名获取用户id，用户查询loginuserpwd
		SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
		
		JsonMessage jsonMessage = new JsonMessage();
		
		if(!sysUser.getLoginpwd().equals(sysUserVO.getLoginuserpwd())){
			jsonMessage.setKey("error");
		}else {
			jsonMessage.setKey("pass");
		}
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	@RequestMapping("/resetuserpwd.do")
	public void resetUserPwd(@RequestBody SysUserVO sysUserVO,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String flag = sysUserVO.getResetAllUserPwdFlag();
		int count = 0;
		if(flag.equals("no")){
			//重置部分人密码
			List<Integer> ids = sysUserVO.getSelectIds();
			for (int i = 0; i < ids.size(); i++) {
				count = iSysUserService.resetUserPwdByUserId(ids.get(i));
				if (count != 1) {
					throw new Exception("重置用户密码失败");
				}
			}
		}else {
			//重置所有人密码
			count = iSysUserService.resetAllUserPwd();
			if (count == 0) {
				throw new Exception("重置用户密码失败");
			}
		}
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	@RequestMapping("/updateUser.do")
	public void updateUser(@RequestBody SysUserVO sysUserVO,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		//转换成sysUser
		SysUser sysUser = mapper.map(sysUserVO, SysUser.class);
		//更新用户信息
		int count = iSysUserService.updateByPrimaryKeySelective(sysUser);
		
		//删除原来的用户角色信息
		int deleteUserRoleCount = iSysUserRoleService.deleteUserRoleByUserId(sysUser.getId());
		
		//更新用户信息
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setRoleid(sysUserVO.getRoleid());
		sysUserRole.setUserid(sysUser.getId());
		int insertUserRoleCount = iSysUserRoleService.insert(sysUserRole);
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		if (count == 0 || deleteUserRoleCount == 0 || insertUserRoleCount == 0) {
			throw new Exception("更新用户失败");
		}
		jsonMessage.setKey("pass");
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(JSON.toJSON(jsonMessage)));
	}
	
	@RequestMapping("/gotoupdateUser.do")
	public String gotoupdateUser(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		Integer userid = Integer.valueOf(request.getParameter("userid"));
		//获取用户信息
		SysUser sysUser = iSysUserService.selectByPrimaryKey(userid);
		//获取用户角色信息
		SysRole sysRole = iSysRoleService.getUserRolesByUserId(userid).get(0);
		
		List<SysRole> roles = iSysRoleService.getAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("user", sysUser);
		model.addAttribute("role", sysRole);
		return "/users/addnewusers";
	}
}
