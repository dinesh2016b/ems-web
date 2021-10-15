/**
 * 
 */
package com.ems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.bean.SalariesBean;
import com.ems.dao.SalariesDAO;
import com.ems.entity.Salaries;
import com.ems.exception.EMSException;
import com.ems.service.SalariesService;

/**
 * @author dinesh
 *
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
public class SalariesServiceImpl implements SalariesService {

	@Autowired 
	private SalariesDAO salariesDAO;
	
	@Override
	public SalariesBean getSalariesByEmployeeId(long employeesId) throws EMSException {
		
		Salaries salaries = salariesDAO.getSalariesByEmployeeId(employeesId);
		SalariesBean salariesBean = new SalariesBean(); 
		//salariesBean.setSalary(salaries.getSalary());
		
		return salariesBean;
	}

}
