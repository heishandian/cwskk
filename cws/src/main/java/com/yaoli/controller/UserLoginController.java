package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysUser;
import com.yaoli.service.ISysUserService;
import com.yaoli.service.IUserLoginRecordService;
import com.yaoli.util.JsonMessage;
import com.yaoli.vo.SysUserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/system")
public class UserLoginController {
	
	@Resource
	ISysUserService iSysUserService;
	
	@Resource
	IUserLoginRecordService iUserLoginRecordService;
	
	/**
	 * 登陆成功进入主界面
	 * @param request
	 * @return
	 */
	@RequestMapping("index.do")
	public String toIndex(HttpServletRequest request,Model model){
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipal() == null){
			return "login";
		}
		SysUser user = iSysUserService.getUserByLoginName((String)subject.getPrincipal());
		model.addAttribute("user", user);
		return "index";
	}
	
	/**
	 * 首页登陆界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.do")
	public String goLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		return "login";
	}
	
	
	@RequestMapping("/outlogin.do")
	public String outLogin(HttpServletRequest request,HttpServletResponse response){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "login";
	}

	/**
	 * 点击登陆按钮
	 * @param sysUserVO
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/loginCheck.do")
	public void userLogin(@RequestBody SysUserVO sysUserVO,
			HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		// sysUserVo 封装了要页面传递过来的对象
		JsonMessage jsonMessage = new JsonMessage();

		// 获取 ValidImageController 生成的验证码
		String validatecode = ((StringBuffer) request.getSession()
				.getAttribute("logincode")).toString();

		//一下代到时候取消注释
		if (sysUserVO.getLogincode() == null || !sysUserVO.getLogincode().toLowerCase()
				.equals(validatecode.toLowerCase())) {
			jsonMessage.setKey("errorcode");
			response.getWriter().write(JSON.toJSONString(jsonMessage));
			return;
		}

		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(
				sysUserVO.getLoginusername(), sysUserVO.getLoginuserpwd());

		try {
			subject.login(token);
			SysUser sysUser = iSysUserService.getUserByLoginName(sysUserVO.getLoginusername());
			/*UserLoginRecord userLoginRecord = new UserLoginRecord();
			userLoginRecord.setUserid(sysUser.getId());
			userLoginRecord.setUserlogintime(new Date());
			iUserLoginRecordService.insert(userLoginRecord);*/

			// 验证通过
			jsonMessage.setKey("pass");

			//System.out.println(JSON.toJSONString(jsonMessage));
			response.getWriter().write(JSON.toJSONString(jsonMessage));
		} catch (Exception e) {
			// 返回账号不存在
			if (e instanceof UnknownAccountException) {
				jsonMessage.setKey("accounterror");
				// 返回密码错误
			} else if (e instanceof IncorrectCredentialsException) {
				jsonMessage.setKey("passworderror");
			}
			response.getWriter().write(JSON.toJSONString(jsonMessage));
		}
	}

	/**
	 * 此方法用于登录整合页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
     * @throws Exception
     */
	@RequestMapping("/main.do")
	public String goToMain(HttpServletRequest request,
						  HttpServletResponse response, Model model) throws Exception {
		return "main";
	}

}
