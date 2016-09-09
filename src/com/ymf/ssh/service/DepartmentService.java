package com.ymf.ssh.service;

import java.util.List;

import com.ymf.ssh.dao.DepartmentDao;
import com.ymf.ssh.entity.Department;

public class DepartmentService {
	private DepartmentDao departmentDao;
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	public List<Department> getAll(){
		return departmentDao.getAll();
	}
}
