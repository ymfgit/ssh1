package com.ymf.ssh.dao;

import java.util.List;

import com.ymf.ssh.entity.Department;

public class DepartmentDao extends BaseDao {
	public List<Department> getAll(){
		String hql = "FROM Department";
		return getSession().createQuery(hql).list();
	}
}
