package com.ems.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ems.bean.DepartmentsBean;
import com.ems.service.impl.DepartmentServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

	@MockBean
	private DepartmentServiceImpl departmentService;

	@Autowired
	private MockMvc mockMvc;
	
	//@InjectMocks
	//private DepartmentRepositoryDAO departmentRepositoryDAO;

	@DisplayName("Test testFindDepartmentById()")
	@Test
	public void testFindDepartmentById() throws Exception {
		DepartmentsBean departmentsBean = new DepartmentsBean("1000", "Department1");

		Mockito.when(departmentService.getDepartmentsById("1000")).thenReturn(departmentsBean);

		mockMvc.perform(get("/departments/1000")).andExpect(status().isOk());
	}

	@DisplayName("Test testFindAllDepartments()")
	@Test
	public void testFindAllDepartments() throws Exception {
		List<DepartmentsBean> departments = new ArrayList<DepartmentsBean>();
		departments.add(new DepartmentsBean("1000", "Department1"));
		departments.add(new DepartmentsBean("1001", "Department2"));
		departments.add(new DepartmentsBean("1002", "Department3"));
		departments.add(new DepartmentsBean("1003", "Department4"));
		departments.add(new DepartmentsBean("1004", "Department5"));

		Mockito.when(departmentService.getAllDepartments(0, 5)).thenReturn(departments);
		mockMvc.perform(get("/departments/pageNo/0/size/5")).andExpect(status().isOk());
	}
}