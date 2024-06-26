package com.ems.controller;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ems.bean.EmployeesBean;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.model.EmployeeRequest;
import com.ems.model.EmployeeResponse;
import com.ems.service.impl.EmployeeServiceImpl;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "https://localhost:8080", maxAge = 3600, allowCredentials = "true")
@Slf4j
public class EmployeesController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping(path = ApplicationConstants.ENDPOINT_GET_EMPLOYEES, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestBody EmployeeRequest employeeRequest)
			throws EMSException, ResourceNotFoundException {

		log.info("------------> getEmployees()");

		List<EmployeeResponse> employeeResponseList = new ArrayList<EmployeeResponse>();
		try {
			List<EmployeesBean> employeesBeans = employeeService.getEmployees(1,
					10);

			employeesBeans.forEach(employeesBean -> {
				EmployeeResponse employeeResponse = new EmployeeResponse();
				employeeResponse.setEmpNo(employeesBean.getEmpNo());
				employeeResponse.setFirstName(employeesBean.getFirstName());
				employeeResponse.setLastName(employeesBean.getLastName());
				employeeResponse.setBirthDate(employeesBean.getBirthDate());
				employeeResponse.setDepartmentsBean(employeesBean.getDepartmentsBean());
				employeeResponse.setSalariesBean(employeesBean.getSalariesBean());
				employeeResponse.setCreatedBy(employeesBean.getCreatedBy());
				employeeResponse.setCreatedDate(employeesBean.getCreatedDate());
				employeeResponse.setUpdatedBy(employeesBean.getUpdatedBy());
				employeeResponse.setUpdatedDate(employeesBean.getUpdatedDate());
				employeeResponseList.add(employeeResponse);
			});
		} catch (EMSException e) {
			e.printStackTrace();
			throw e;
		}
		return new ResponseEntity<List<EmployeeResponse>>(employeeResponseList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(path = ApplicationConstants.ENDPOINT_GET_EMPLOYEE_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeResponse> getEmployeeById(@RequestBody EmployeeRequest employeeRequest)
			throws EMSException, ResourceNotFoundException {

		EmployeeResponse employeeResponse = new EmployeeResponse();
		try {
			EmployeesBean employeesBean = employeeService.getEmployeesById(employeeRequest.getEmpNo());

			if (employeesBean != null) {
				employeeResponse.setEmpNo(employeesBean.getEmpNo());
				employeeResponse.setFirstName(employeesBean.getFirstName());
				employeeResponse.setLastName(employeesBean.getLastName());
				employeeResponse.setBirthDate(employeesBean.getBirthDate());
				employeeResponse.setDepartmentsBean(employeesBean.getDepartmentsBean());
				employeeResponse.setSalariesBean(employeesBean.getSalariesBean());
				employeeResponse.setCreatedBy(employeesBean.getCreatedBy());
				employeeResponse.setCreatedDate(employeesBean.getCreatedDate());
				employeeResponse.setUpdatedBy(employeesBean.getUpdatedBy());
				employeeResponse.setUpdatedDate(employeesBean.getUpdatedDate());
			}
		} catch (ResourceNotFoundException e) {
			log.error(e.getMessage());
			throw e;
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		// return employeesBean;
		return new ResponseEntity<EmployeeResponse>(employeeResponse, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_CREATE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> addEmployee(@RequestBody EmployeeRequest employeeRequest) throws EMSException {
		EmployeesBean employeesBean = new EmployeesBean();
		log.debug("--------> addEmployee() :" + employeesBean.toString());

		try {
			if (employeeRequest != null) {
				employeesBean.setEmpNo(employeeRequest.getEmpNo());
				employeesBean.setFirstName(employeeRequest.getFirstName());
				employeesBean.setLastName(employeeRequest.getLastName());
				employeesBean.setBirthDate(employeeRequest.getBirthDate());
				employeesBean.setDepartmentsBean(employeeRequest.getDepartmentsBean());
				employeesBean.setSalariesBean(employeeRequest.getSalariesBean());
				employeesBean.setCreatedBy(employeeRequest.getCreatedBy());
				employeesBean.setCreatedDate(employeeRequest.getCreatedDate());

				employeeService.addEmployee(employeesBean);
			}
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeesBean.getEmpNo()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(path = ApplicationConstants.ENDPOINT_UPDATE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> updateEmployee(@RequestBody EmployeeRequest employeeRequest) throws EMSException {

		log.debug("--------> updateEmployee()");
		EmployeesBean employeesBean = new EmployeesBean();
		try {
			if (employeeRequest != null) {
				employeesBean.setEmpNo(employeeRequest.getEmpNo());
				employeesBean.setFirstName(employeeRequest.getFirstName());
				employeesBean.setLastName(employeeRequest.getLastName());
				employeesBean.setBirthDate(employeeRequest.getBirthDate());
				employeesBean.setDepartmentsBean(employeeRequest.getDepartmentsBean());
				employeesBean.setSalariesBean(employeeRequest.getSalariesBean());
				employeesBean.setUpdatedDate(new Date(System.currentTimeMillis()));
				employeesBean.setUpdatedBy("System");
				
				employeeService.updateEmployee(employeesBean);
			}
		} catch (EMSException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeesBean.getEmpNo()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = ApplicationConstants.ENDPOINT_DELETE_EMPLOYEE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> deleteEmployee(@RequestBody EmployeeRequest employeeRequest) throws EMSException {

		log.debug("--------> deleteEmployee()");
		EmployeesBean employeesBean = new EmployeesBean();
		try {
			if (employeeRequest != null) {
				employeesBean.setEmpNo(employeeRequest.getEmpNo());
				employeesBean.setFirstName(employeeRequest.getFirstName());
				employeesBean.setLastName(employeeRequest.getLastName());
				employeesBean.setBirthDate(employeeRequest.getBirthDate());
				employeesBean.setDepartmentsBean(employeeRequest.getDepartmentsBean());
				employeesBean.setSalariesBean(employeeRequest.getSalariesBean());

				employeeService.deleteEmployee(employeesBean);
			}
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