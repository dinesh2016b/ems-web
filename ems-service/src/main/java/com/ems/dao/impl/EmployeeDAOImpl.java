/**
 * 
 */
package com.ems.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ems.dao.EmployeeDAO;
import com.ems.entity.Employees;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repositories.EmployeeRepository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Page<Employees> getEmployees(int firstRecord, int size) throws EMSException {

		Pageable pageable = PageRequest.of(firstRecord, size);
		Page<Employees> employeeList = employeeRepository.findAll(pageable);
		return employeeList;
	}

	@Override
	public Employees getEmployeeById(Long employeeId) throws EMSException, ResourceNotFoundException {
		Employees employees = ((Employees) employeeRepository.findById(employeeId));
		return employees;
	}

	@Override
	public void addEmployee(Employees employees) throws EMSException {
		employeeRepository.save(employees);
	}

}
