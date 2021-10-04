package com.ems.dao;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.ems.entity.Departments;
import com.ems.exception.ResourceNotFoundException;

public interface DepartmentDAO {

	Page<Departments> getAllDepartments(int pageNo, int size) throws Exception;

	Departments getDepartmentsById(String departmentId) throws Exception;

	Departments createDepartment(Departments department) throws Exception;

	Departments updateDepartment(String departmentId, Departments departments)
			throws ResourceNotFoundException;

	Map<String, Boolean> deleteDepartment(String departmentId) throws ResourceNotFoundException;
}
