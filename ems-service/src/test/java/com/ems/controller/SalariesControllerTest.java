package com.ems.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ems.exception.EMSException;
import com.ems.repositories.SalariesRepository;
import com.ems.service.SalariesService;

@ExtendWith(MockitoExtension.class)
class SalariesControllerTest {

	@Mock
	private SalariesService salariesService;
	
	@Mock
	private SalariesRepository salariesRepositoryDAO;
	
	@InjectMocks
	private SalariesController salariesController;
		
	@Test
	void test() throws EMSException {
		salariesController.getAllSalaries();	
	}

}
