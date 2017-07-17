package com.zky.dao;

import com.zky.model.Student;

public interface StudentMapper {
	
	public int upDateStudentById(int id);
	
	public Student selectByPrimaryKey(int id);
	
}
