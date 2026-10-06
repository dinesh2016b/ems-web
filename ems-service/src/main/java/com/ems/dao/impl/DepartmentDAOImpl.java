package com.ems.dao.impl;

import com.ems.dao.DepartmentDAO;
import com.ems.entity.Departments;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@RequiredArgsConstructor
@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	private final DepartmentRepository departmentRepository;

	@Override
	public Page<Departments> getAllDepartments(int pageNo, int size) throws EMSException {
		Pageable pageable = PageRequest.of(pageNo, size);
		Page<Departments> departments = departmentRepository.findAll(pageable);

		return departments;
	}

	@Override
	public Departments getDepartmentsById(String departmentId) throws EMSException, ResourceNotFoundException {
		Departments departments = departmentRepository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Departments not found for this departmentId :: " + departmentId));

		return departments;
	}

	@Override
	public Departments createDepartment(Departments department) throws EMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Departments updateDepartment(String departmentId, Departments departments)
			throws EMSException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Boolean> deleteDepartment(String departmentId) throws EMSException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
