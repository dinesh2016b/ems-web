package com.ems.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeesApplicationTests {

	@Autowired
	private EmployeesController employeesController;

	@Test
	public void contextLoads() {
		assertThat(employeesController).isNotNull();
	}
}