package com.ymf.ssh.dao;

import java.util.List;

import org.hibernate.Query;

import com.ymf.ssh.entity.Employee;

public class EmployeeDao extends BaseDao {
	
	public void delete(Integer id){
		String hql = "DELETE FROM Employee e WHERE e.id = ?";
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	public List<Employee> getAll(){
		String hql = "FROM Employee e LEFT OUTER JOIN FETCH e.dept";
		
		return getSession().createQuery(hql).list();
	}
	
	public void saveOrUpdate(Employee employee){
		getSession().saveOrUpdate(employee);
	}
	
	public Employee get(Integer id){
		return (Employee) getSession().get(Employee.class, id);
	}

	public Employee getEmployeeByLastName(String lastName){
		String hql = "FROM Employee e WHERE e.lastName = ?";
		Query query = getSession().createQuery(hql).setString(0, lastName);
		Employee employee = (Employee) query.uniqueResult();
		System.out.println(employee.getDept().getClass().getName());
		return employee;
	}
}
