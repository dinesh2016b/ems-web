package com.ems.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.ems.dao.DepartmentDAO;
import com.ems.entity.Departments;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repositories.DepartmentRepository;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Page<Departments> getAllDepartments(int pageNo, int size) throws Exception {
		Pageable pageable = PageRequest.of(pageNo, size);
		Page<Departments> departments = departmentRepository.findAll(pageable);

		return departments;
	}

	@Override
	public Departments getDepartmentsById(String departmentId) throws Exception {
		Departments departments = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Departments not found for this departmentId :: " + departmentId));

		return departments;
	}

	@Override
	public Departments createDepartment(Departments department) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departments updateDepartment(String departmentId, Departments departments) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Boolean> deleteDepartment(String departmentId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
