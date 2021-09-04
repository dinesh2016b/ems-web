package com.ems.service;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ems.bean.EmployeesBean;

public interface EmployeeService {

	// @GetMapping(path = "/employees/pageNo/{pageNo}/size/{size}")
	@GetMapping(path = "/backend-employee-service/employees/pageNo/{pageNo}/size/{size}")
	List<EmployeesBean> getEmployees(@PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "size") int size)
			throws Exception;

	// @GetMapping(path = "/employees/{id}")
	@GetMapping(path = "/backend-employee-service/employees/{id}")
	EmployeesBean getEmployeeById(@PathVariable(value = "id") Long employeeId) throws Exception;

	// @PostMapping(path = "/employees")
	@PostMapping(path = "/backend-employee-service/employees")
	EmployeesBean addEmployee(@RequestBody EmployeesBean employeesBean) throws Exception;
}
