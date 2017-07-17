package com.zky.service;

import com.zky.model.Student;

public interface IStudentService {

	public Student getStudentById(int id);
	
	public int upDateStudentById(int id);
	
	public Student selectByPrimaryKey(int id);
}
