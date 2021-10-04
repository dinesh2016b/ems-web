package com.ems.dao;

import com.ems.entity.Salaries;

public interface SalariesDAO {
	Salaries getSalariesByEmployeeId(long emplooyeeId) throws Exception;
}
