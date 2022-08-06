package com.ems.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ems.bean.EmployeesBean;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.service.impl.EmployeeServiceImpl;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "https://localhost:8080", maxAge = 3600, allowCredentials="true")
@Slf4j
public class EmployeesController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping(path = ApplicationConstants.ENDPOINT_GET_EMPLOYEES, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeesBean>> getEmployees(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "size") int size) throws EMSException, ResourceNotFoundException {

		log.info("------------> getEmployees()");
		List<EmployeesBean> employeesBeans = null;
		try {
			employeesBeans = employeeService.getEmployees(pageNo, size);
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		// return employeesBeans;

		return new ResponseEntity<List<EmployeesBean>>(employeesBeans, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_GET_EMPLOYEE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws EMSException, ResourceNotFoundException {
		EmployeesBean employeesBean = null;
		try {
			employeesBean = employeeService.getEmployeesById(employeeId);
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw e;
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		// return employeesBean;
		return new ResponseEntity<EmployeesBean>(employeesBean, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_CREATE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> addEmployee(@RequestBody EmployeesBean employeesBean) throws EMSException {

		log.debug("--------> addEmployee() :" + employeesBean.toString());

		try {
			employeeService.addEmployee(employeesBean);
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeesBean.getEmpNo()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_UPDATE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> updateEmployee(@RequestBody EmployeesBean employeesBean) throws EMSException {

		log.debug("--------> updateEmployee() :" + employeesBean.toString());

		try {
			employeeService.addEmployee(employeesBean);
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeesBean.getEmpNo()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_DELETE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> deleteEmployee(@RequestBody EmployeesBean employeesBean) throws EMSException {

		log.debug("--------> updateEmployee() :" + employeesBean.toString());

		try {
			employeeService.addEmployee(employeesBean);
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeesBean.getEmpNo()).toUri();

		return ResponseEntity.created(location).build();
	}

}