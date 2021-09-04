package com.ems.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ems.bean.EmployeesBean;
import com.ems.service.impl.EmployeeServiceImpl;

@RestController
@CrossOrigin(origins = "https://localhost:8080")
@RequestMapping("/employees")
public class EmployeesController {

	private final Logger logger = LoggerFactory.getLogger(EmployeesController.class);

	@Autowired
	private EmployeeServiceImpl employeeService;

	@GetMapping(path = "/pageNo/{pageNo}/size/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeesBean> getEmployees(@PathVariable(value = "pageNo") int pageNo,
			@PathVariable(value = "size") int size) throws Exception {

		logger.info("------------> getEmployees()");

		EmployeesBean employesResource = null;
		List<EmployeesBean> employeesResourceList = new ArrayList<EmployeesBean>();

		try {
			List<EmployeesBean> employeesBeans = employeeService.getEmployees(pageNo, size);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return employeesResourceList;
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeesBean getEmployeeById(@PathVariable(value = "id") Long employeeId) throws Exception {

		EmployeesBean employesResource = null;
		try {

			EmployeesBean employeesBean = employeeService.getEmployeesById(employeeId);

			return employesResource;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

		return null;
	}

	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeesBean> addEmployee(@RequestBody EmployeesBean employeesBean)
			throws Exception {

		logger.debug("--------> addEmployee() :" + employeesBean.toString());

		try {
			employeeService.addEmployee(employeesBean);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employeesBean.getEmpNo()).toUri();

		return ResponseEntity.created(location).build();
	}
}