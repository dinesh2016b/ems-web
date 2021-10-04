package com.ems.dao;

import org.springframework.data.domain.Page;

import com.ems.entity.Employees;
import com.ems.exception.APIException;
import com.ems.exception.ResourceNotFoundException;

public interface EmployeeDAO {

	public Page<Employees> getEmployees(int pageNo, int size) throws APIException;

	public Employees getEmployeeById(Long employeeId) throws APIException, ResourceNotFoundException;

	public void addEmployee(Employees employees) throws APIException;
}
