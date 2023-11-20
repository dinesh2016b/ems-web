package com.ems.service;

import java.util.List;

import com.ems.bean.EmployeesBean;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;

public interface EmployeeService {
	List<EmployeesBean> getEmployees(int firstRecord, int size) throws EMSException, ResourceNotFoundException;
	EmployeesBean getEmployeesById(Long employeeId) throws EMSException, ResourceNotFoundException;
	void addEmployee(EmployeesBean employeesBean) throws EMSException;
	void updateEmployee(EmployeesBean employeesBean) throws EMSException;
	void deleteEmployee(EmployeesBean employeesBean) throws EMSException;
}
