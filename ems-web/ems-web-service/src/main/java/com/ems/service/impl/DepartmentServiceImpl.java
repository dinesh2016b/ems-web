/**
 * 
 */
package com.ems.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.bean.DepartmentsBean;
import com.ems.dao.DepartmentRepositoryDAO;
import com.ems.entity.Departments;
import com.ems.exception.ResourceNotFoundException;
import com.ems.service.DepartmentService;

/**
 * @author dinesh
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepositoryDAO departmentRepository;

	@Override
	public List<DepartmentsBean> getAllDepartments(int pageNo, int size) throws Exception {
		
		
		 List<Departments> departmentList=  departmentRepository.findAll();
		 List<DepartmentsBean> departmentsBeanList = new ArrayList<DepartmentsBean>();
		 
		 for(Departments departments : departmentList) {
			 DepartmentsBean departmentsBean = new DepartmentsBean();
			 departmentsBean.setDeptNo(departments.getDeptNo());
			 departmentsBean.setDeptName(departments.getDeptName());
			 departmentsBeanList.add(departmentsBean);
		 }
		 
		return departmentsBeanList;
	}

	@Override
	public DepartmentsBean getDepartmentsById(String departmentId) throws Exception {	
		
		 Departments departments=  departmentRepository.findById(departmentId).orElseThrow(
					() -> new ResourceNotFoundException("Departments not found for this departmentId :: " + departmentId));
		 
		 DepartmentsBean departmentsBean = new DepartmentsBean();
		 departmentsBean.setDeptNo(departments.getDeptNo());
		 departmentsBean.setDeptName(departments.getDeptName());
		 
		return departmentsBean;
	}

	@Override
	public DepartmentsBean createDepartment(DepartmentsBean department) throws Exception {
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
