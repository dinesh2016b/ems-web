package com.ems.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.bean.SalariesBean;
import com.ems.entity.Salaries;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repositories.SalariesRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class SalariesController {

	private Logger logger = LoggerFactory.getLogger(SalariesController.class);

	@Autowired
	private SalariesRepository salariesRepositoryDAO;

	@GetMapping("/salaries")
	public ResponseEntity<List<Salaries>> getAllSalaries() throws EMSException {
		logger.info("----> department list ");

		List<Salaries> salariesList = new ArrayList<Salaries>();
		salariesList = salariesRepositoryDAO.findAll();

		return ResponseEntity.ok().body(salariesList);
	}
	
	@GetMapping("/salaries/{id}")
	public SalariesBean getSalariesByEmployeeId(@PathVariable(value = "id") long id) throws EMSException {
		try {

			logger.info("----> employeeId - " + id);
			/*
			Salaries salaries = salariesRepositoryDAO.findById(String.valueOf(id))
					.orElseThrow(() -> new EMSException("Salaries not found for this employeeId :: " + String.valueOf(id)));
			*/
			SalariesBean salariesBean = new SalariesBean();
			salariesBean.setSalary(100);
			salariesBean.setToDate(new Date());
			
			return salariesBean;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@PostMapping("/salaries")
	public Salaries createEmployee(@RequestBody Salaries salaries) {
		return salariesRepositoryDAO.save(salaries);
	}

	@PutMapping("/salaries/{deptId}")
	public ResponseEntity<Salaries> updateEmployee(@PathVariable(value = "deptId") String departmentId,
			@RequestBody Salaries employeeDetails) throws ResourceNotFoundException {
		Salaries salaries = salariesRepositoryDAO.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Salaries not found for this deptId :: " + departmentId));

		final Salaries updatedEmployee = salariesRepositoryDAO.save(salaries);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/salaries/{deptId}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "deptId") String departmentId)
			throws ResourceNotFoundException {
		
		Salaries salaries = salariesRepositoryDAO.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Salaries not found for this deptId :: " + departmentId));

		salariesRepositoryDAO.delete(salaries);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}