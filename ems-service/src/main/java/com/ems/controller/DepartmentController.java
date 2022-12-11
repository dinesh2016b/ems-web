package com.ems.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ems.bean.DepartmentsBean;
import com.ems.entity.Departments;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repositories.DepartmentRepository;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials="true")
@Slf4j
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@PostMapping(path = ApplicationConstants.ENDPOINT_GET_DEPARTMENTS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartmentsBean>> getAllDepartments(@PathVariable(value = "pageNo") String pageNo,
			@PathVariable(value = "size") String size) throws Exception {
		try {
			log.info("----> department list ");

			List<Departments> departmentList = departmentRepository.findAll();
			List<DepartmentsBean> departmentsBeans = new ArrayList<DepartmentsBean>();
			DepartmentsBean departmentsBean = null;
			for (Departments departments : departmentList) {
				departmentsBean = new DepartmentsBean();
				departmentsBean.setDeptNo(departments.getDeptNo());
				departmentsBean.setDeptName(departments.getDeptName());
				departmentsBeans.add(departmentsBean);
			}

			return ResponseEntity.ok().body(departmentsBeans);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_GET_DEPARTMENT_BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DepartmentsBean> getDepartmentsById(@PathVariable(value = "id") String departmentId)
			throws Exception {

		try {

			log.info("----> departmentId - " + departmentId);
			Departments departments = departmentRepository.findById(departmentId)
					.orElseThrow(() -> new Exception("Departments not found for this deptId :: " + departmentId));

			DepartmentsBean departmentsBean = new DepartmentsBean();
			departmentsBean.setDeptNo(departments.getDeptNo());
			departmentsBean.setDeptName(departments.getDeptName());

			return ResponseEntity.ok().body(departmentsBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_CREATE_DEPARTMENT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Departments> createDepartment(@RequestBody Departments department) throws Exception {

		try {
			departmentRepository.save(department);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(department.getDeptNo()).toUri();

			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_UPDATE_DEPARTMENT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Departments> updateEmployee(@PathVariable(value = "deptId") String departmentId,
			@RequestBody Departments employeeDetails) throws ResourceNotFoundException {

		try {

			Departments department = departmentRepository.findById(departmentId).orElseThrow(
					() -> new ResourceNotFoundException("Departments not found for this deptId :: " + departmentId));

			final Departments updatedDepartment = departmentRepository.save(department);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(updatedDepartment.getDeptNo()).toUri();

			return ResponseEntity.created(location).build();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}

	}

	@DeleteMapping(path = ApplicationConstants.ENDPOINT_DELETE_DEPARTMENT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "deptId") String departmentId)
			throws ResourceNotFoundException {

		try {
			Departments department = departmentRepository.findById(departmentId).orElseThrow(
					() -> new ResourceNotFoundException("Departments not found for this deptId :: " + departmentId));

			departmentRepository.delete(department);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}