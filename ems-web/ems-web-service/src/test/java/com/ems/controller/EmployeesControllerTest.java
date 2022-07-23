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

import com.ems.bean.EmployeesBean;
import com.ems.security.util.JwtUtil;
import com.ems.service.MyUserDetailsService;
import com.ems.service.impl.EmployeeServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeesController.class)
class EmployeesControllerTest {

	@MockBean
	private EmployeeServiceImpl employeeService;

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	private MyUserDetailsService myUserDetailsService;
	
	@MockBean
	private JwtUtil jwtUtil;

	@DisplayName("Test testFindEmployeeById()")
	@Test
	public void testFindEmployeeById() throws Exception {
		EmployeesBean employeesBean = new EmployeesBean(1000L, "Dinesh", "Dinesh1", "08/09/2002", null, null);

		Mockito.when(employeeService.getEmployeesById(1000L)).thenReturn(employeesBean);

		mockMvc.perform(get("/employees/10000")).andExpect(status().isForbidden());
	}

	@DisplayName("Test testFindEmployees()")
	@Test
	public void testFindEmployees() throws Exception {
		List<EmployeesBean> employees = new ArrayList<EmployeesBean>();
		employees.add(new EmployeesBean(1000L, "Dinesh1", "Dinesh1", "01/09/2002", null, null));
		employees.add(new EmployeesBean(1001L, "Dinesh2", "Dinesh2", "02/09/2003", null, null));
		employees.add(new EmployeesBean(1002L, "Dinesh3", "Dinesh3", "03/09/2004", null, null));
		employees.add(new EmployeesBean(1003L, "Dinesh4", "Dinesh4", "04/09/2005", null, null));
		employees.add(new EmployeesBean(1004L, "Dinesh5", "Dinesh5", "05/09/2006", null, null));

		Mockito.when(employeeService.getEmployees(0, 5)).thenReturn(employees);
		mockMvc.perform(get("/employees/pageNo/0/size/5")).andExpect(status().isForbidden());
	}
}