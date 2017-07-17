package com.zky.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zky.model.User;
import com.zky.service.IUserService;
import com.zky.service.impl.StudentServiceImpl;
import com.zky.vo.UserVO;

@Controller
@RequestMapping("/system")
public class UserLoginController {

	@Autowired
	private StudentServiceImpl iStudentService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IUserService iUserService;

	@RequestMapping("/gotoLoginPage.do")
	public String gotoLoginPage() {
		// System.out.println(iStudentService.selectByPrimaryKey(2));
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, HttpServletResponse response, UserVO user,
			HashMap<String, String> map) {
		// 从数据库中读取出用户名和密码与页面传递过来的用户名和密码对比
		// 如果有该用户，那么转入home页面
		// 如果没有该用户，返回登陆界面，并显示出用户不存在
		User dbuser = iUserService.selectByUserName(user.getName());
		if (dbuser == null) {
			map.put("message", "userNotExisted");
			return "login";
		} else {
			if (user.getPassword().equals(dbuser.getPassword())) {
				map.put("message", "success");
				return "home";
			} else {
				map.put("message", "passwordError");
				return "login";
			}
		}

	}

}
