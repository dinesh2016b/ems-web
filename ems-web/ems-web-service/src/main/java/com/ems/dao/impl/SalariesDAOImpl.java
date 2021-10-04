package com.ems.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ems.dao.SalariesDAO;
import com.ems.entity.Salaries;
import com.ems.entity.SalariesId;
import com.ems.repositories.SalariesRepository;

@Repository
public class SalariesDAOImpl implements SalariesDAO {

	@Autowired
	private SalariesRepository salariesRepository;

	@Override
	public Salaries getSalariesByEmployeeId(long emplooyeeId) throws Exception {
		SalariesId salariesId = new SalariesId();
		salariesId.setEmpNo(emplooyeeId);
		//return salariesRepository.getById("0");
		return null;
	}

}
