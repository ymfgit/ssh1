package com.ymf.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.ymf.ssh.entity.Employee;
import com.ymf.ssh.service.DepartmentService;
import com.ymf.ssh.service.EmployeeService;

public class EmployeeAction extends ActionSupport implements RequestAware,ModelDriven<Employee>,Preparable{
	private static final long serialVersionUID = 1L;

	private EmployeeService employeeService;
	private Integer id;
	private DepartmentService departmentService;
	private InputStream inputStream;
	private Employee model;
	private Map<String,Object> request;
	private String lastName;
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String validateLastName() throws UnsupportedEncodingException{
		if(employeeService.lastNameIsValid(lastName)){
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8")); 
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8")); 
		}
		
		return "ajax-success";
	}
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String list(){
		request.put("employees", employeeService.getAll());
		return "list";
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	//删除信息
	public String delete(){
		try {
			employeeService.delete(id);
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "delete";
	}
	
	//转到添加界面
	public String input(){
		request.put("departments", departmentService.getAll());
		return "input";
	}
	
	public void prepareInput(){
		if(id != null){
			model = employeeService.get(id);
		}
		
	}
	
	//添加信息
	public String save(){
		if(id == null){
			model.setCreateTime(new Date());
		}
		
		employeeService.saveOrUpdate(model);
		return SUCCESS;
	}
	
	public void prepareSave(){
		if(id == null){	
			model = new Employee();
		}else{
			model = employeeService.get(id);
		}
	}
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	
	@Override
	public void prepare() throws Exception {}
	
	
	
	
	@Override
	public Employee getModel() {
		return model;
	}
}
