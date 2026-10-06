/**
 * 
 */
package com.ems.dao.impl;

import com.ems.dao.EmployeeDAO;
import com.ems.entity.Employees;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private final EmployeeRepository employeeRepository;

	@Override
	public Page<Employees> getEmployees(int firstRecord, int size) throws EMSException {

		Pageable pageable = PageRequest.of(firstRecord, size);
		Page<Employees> employeeList = employeeRepository.findAll(pageable);
		return employeeList;
	}

	@Override
	public Employees getEmployeeById(Long employeeId) throws EMSException, ResourceNotFoundException {
		Employees employees = employeeRepository.findById(employeeId);
		return employees;
	}

	@Override
	public void addEmployee(Employees employees) throws EMSException {
		employeeRepository.save(employees);
	}

	@Override
	public void updateEmployee(Employees employees) throws EMSException {
		employeeRepository.save(employees);
	}

	@Override
	public void deleteEmployee(Employees employees) throws EMSException {
		employeeRepository.deleteById(employees.getEmpNo());
	}
}
