package com.ems.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.bean.DepartmentsBean;
import com.ems.bean.EmployeesBean;
import com.ems.bean.SalariesBean;
import com.ems.dao.EmployeeDAO;
import com.ems.entity.Employees;
import com.ems.service.DepartmentService;
import com.ems.service.SalariesService;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
public class EmployeeServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SalariesService salariesService;

	@Autowired
	private EmployeeDAO employeeDAO;

	public List<EmployeesBean> getEmployees(int firstRecord, int size) throws Exception {

		Page<Employees> employeeList = employeeDAO.getEmployees(firstRecord, size);

		List<EmployeesBean> employeesBeans = new ArrayList<EmployeesBean>();
		EmployeesBean employeesBean = null;
		for (Employees employees : employeeList) {
			employeesBean = new EmployeesBean();
			employeesBean.setEmpNo(employees.getEmpNo());
			employeesBean.setFirstName(employees.getFirstName());
			employeesBean.setLastName(employees.getLastName());
			employeesBean.setBirthDate(employees.getBirthDate());
			
			String departmentId = "1001";
			DepartmentsBean departmentsBean = departmentService.getDepartmentsById(departmentId);
			logger.debug("---------> Departments :" + departmentsBean);
			
			employeesBean.setDepartmentsBean(departmentsBean);

			SalariesBean salariesBean = salariesService.getSalariesByEmployeeId(employees.getEmpNo());
			employeesBean.setSalariesBean(salariesBean);

			logger.debug("------------> getEmployeesById() : " + employeesBean);

			employeesBeans.add(employeesBean);
		}

		logger.debug("------------> getEmployees() : " + employeeList);

		return employeesBeans;
	}

	public EmployeesBean getEmployeesById(Long employeeId) throws Exception {

		EmployeesBean employeesBean = null;
		Employees employees = employeeDAO.getEmployeeById(employeeId);
		
		if (employees != null) {
			employeesBean = new EmployeesBean();
			employeesBean.setEmpNo(employees.getEmpNo());
			employeesBean.setFirstName(employees.getFirstName());
			employeesBean.setLastName(employees.getLastName());
			employeesBean.setBirthDate(employees.getBirthDate());
		}
		
		String departmentId = "1001";
		DepartmentsBean departmentsBean = departmentService.getDepartmentsById(departmentId);
		logger.debug("---------> Departments :" + departmentsBean);
		
		employeesBean.setDepartmentsBean(departmentsBean);

		SalariesBean salariesBean = salariesService.getSalariesByEmployeeId(employees.getEmpNo());
		employeesBean.setSalariesBean(salariesBean);

		logger.debug("------------> getEmployeesById() : " + employeesBean);

		return employeesBean;
	}

	public void addEmployee(EmployeesBean employeesBean) throws Exception {

		logger.debug("------------> getEmployeesById() : " + employeesBean.toString());
		
		Employees employees = new Employees();
		employees.setEmpNo(employeesBean.getEmpNo());
		employees.setFirstName(employeesBean.getFirstName());
		employees.setLastName(employeesBean.getLastName());
		employees.setBirthDate(employeesBean.getBirthDate());

		employeeDAO.addEmployee(employees);
	}
}
