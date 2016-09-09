package com.ymf.ssh.service;

import java.util.List;

import com.ymf.ssh.dao.EmployeeDao;
import com.ymf.ssh.entity.Employee;

public class EmployeeService {
	private EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public List<Employee> getAll(){
		return employeeDao.getAll();
	}
	
	public void delete(Integer id){
		employeeDao.delete(id);
	}
	
	public void saveOrUpdate(Employee employee){
		employeeDao.saveOrUpdate(employee);
	}

	public Employee get(Integer id) {
		System.out.println(employeeDao.get(id)+"结果");
		return employeeDao.get(id);
	}

	public boolean lastNameIsValid(String lastName){
		return employeeDao.getEmployeeByLastName(lastName) == null;
	}
}
