package com.zky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zky.dao.StudentMapper;
import com.zky.model.Student;
import com.zky.service.IStudentService;

@Service("studentServiceImpl")
public class StudentServiceImpl implements IStudentService{
	@Autowired
	private StudentMapper iStudentsDao;
	
	@Override
	public int upDateStudentById(int id) {
		return iStudentsDao.upDateStudentById(id);
	}

	@Override
	public Student getStudentById(int id) {
		return null;
	}

	@Override
	public Student selectByPrimaryKey(int id) {
		return iStudentsDao.selectByPrimaryKey(id);
	}

}
