package com.ems.dao;

import org.springframework.data.domain.Page;

import com.ems.entity.Employees;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;

public interface EmployeeDAO {

	Page<Employees> getEmployees(int pageNo, int size) throws EMSException;

	Employees getEmployeeById(Long employeeId) throws EMSException, ResourceNotFoundException;

	void addEmployee(Employees employees) throws EMSException;
}
