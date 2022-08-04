/**
 * 
 */
package com.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.bean.DepartmentsBean;
import com.ems.dao.DepartmentDAO;
import com.ems.entity.Departments;
import com.ems.exception.EMSException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dinesh
 *
 */
@Slf4j
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDAO departmentDAO;

	@Override
	public List<DepartmentsBean> getAllDepartments(int pageNo, int size) throws EMSException {

		Page<Departments> departmentList = departmentDAO.getAllDepartments(pageNo, size);
		List<DepartmentsBean> departmentsBeanList = new ArrayList<DepartmentsBean>();

		for (Departments departments : departmentList) {
			DepartmentsBean departmentsBean = new DepartmentsBean();
			departmentsBean.setDeptNo(departments.getDeptNo());
			departmentsBean.setDeptName(departments.getDeptName());
			departmentsBeanList.add(departmentsBean);
		}

		return departmentsBeanList;
	}

	@Override
	public DepartmentsBean getDepartmentsById(String departmentId) throws EMSException, ResourceNotFoundException {

		Departments departments = departmentDAO.getDepartmentsById(departmentId);
		DepartmentsBean departmentsBean = new DepartmentsBean();
		departmentsBean.setDeptNo(departments.getDeptNo());
		departmentsBean.setDeptName(departments.getDeptName());

		return departmentsBean;
	}

	@Override
	public DepartmentsBean createDepartment(DepartmentsBean department) throws EMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentsBean updateDepartment(String departmentId, DepartmentsBean departmentsBean)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Boolean> deleteDepartment(String departmentId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
