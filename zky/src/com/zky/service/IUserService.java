package com.zky.service;

import com.zky.model.User;

public interface IUserService {
	public User selectByUserName(String name);
}
