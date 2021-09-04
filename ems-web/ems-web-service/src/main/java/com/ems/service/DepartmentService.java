package com.ems.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ems.bean.DepartmentsBean;
import com.ems.exception.ResourceNotFoundException;

public interface DepartmentService {

	// @GetMapping(path = "/departments/pageNo/{pageNo}/size/{size}")
	@GetMapping(path = "/backend-department-service/departments/pageNo/{pageNo}/size/{size}")
    List<DepartmentsBean> getAllDepartments(@PathVariable(value = "pageNo") int pageNo,
                                            @PathVariable(value = "size") int size) throws Exception;

	// @GetMapping(path = "/departments/{id}")
	@GetMapping(path = "/backend-department-service/departments/{id}")
    DepartmentsBean getDepartmentsById(@PathVariable(value = "id") String departmentId) throws Exception;

	// @PostMapping(path = "/departments")
	@PostMapping(path = "/backend-department-service/departments")
    DepartmentsBean createDepartment(@RequestBody DepartmentsBean department) throws Exception;

	// @PutMapping(path = "/departments/{deptId}")
	@PutMapping(path = "/backend-department-service/departments/{deptId}")
    DepartmentsBean updateDepartment(@PathVariable(value = "deptId") String departmentId,
                                     @RequestBody DepartmentsBean departmentsBean) throws ResourceNotFoundException;

	// @DeleteMapping(path = "/departments/{deptId}")
	@DeleteMapping(path = "/backend-department-service/departments/{deptId}")
    Map<String, Boolean> deleteDepartment(@PathVariable(value = "deptId") String departmentId)
			throws ResourceNotFoundException;
}
