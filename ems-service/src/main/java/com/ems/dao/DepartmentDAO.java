package com.ems.dao;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.ems.entity.Departments;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;

public interface DepartmentDAO {

	Page<Departments> getAllDepartments(int pageNo, int size) throws EMSException;

	Departments getDepartmentsById(String departmentId) throws EMSException, ResourceNotFoundException;

	Departments createDepartment(Departments department) throws EMSException;

	Departments updateDepartment(String departmentId, Departments departments)
			throws EMSException, ResourceNotFoundException;

	Map<String, Boolean> deleteDepartment(String departmentId) throws EMSException, ResourceNotFoundException;
}
