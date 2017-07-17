package com.zky.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zky.dao.UserMapper;
import com.zky.model.User;
import com.zky.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User selectByUserName(String name) {
		return userMapper.selectByUserName(name);
	}

}
