package com.ems.dao.impl;

import com.ems.dao.SalariesDAO;
import com.ems.entity.Salaries;
import com.ems.entity.SalariesId;
import com.ems.exception.EMSException;
import com.ems.repositories.SalariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SalariesDAOImpl implements SalariesDAO {

    private final SalariesRepository salariesRepository;

    @Override
    public Salaries getSalariesByEmployeeId(long emplooyeeId) throws EMSException {
        SalariesId salariesId = new SalariesId();
        salariesId.setEmpNo(emplooyeeId);
        //return salariesRepository.getById("0");
        return null;
    }

}
