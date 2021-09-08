/**
 * 
 */
package com.ems.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.bean.SalariesBean;
import com.ems.service.SalariesService;

/**
 * @author dinesh
 *
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
public class SalariesServiceImpl implements SalariesService {

	@Override
	public SalariesBean getSalariesByEmployeeId(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
