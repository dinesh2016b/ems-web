package com.ems.dao;

import com.ems.entity.Salaries;
import com.ems.exception.EMSException;

public interface SalariesDAO {
	Salaries getSalariesByEmployeeId(long emplooyeeId) throws EMSException;
}
