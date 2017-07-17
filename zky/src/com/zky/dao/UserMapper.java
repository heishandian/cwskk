package com.zky.dao;

import com.zky.model.User;

public interface UserMapper {
	
	public User selectByUserName(String name);
	
}
