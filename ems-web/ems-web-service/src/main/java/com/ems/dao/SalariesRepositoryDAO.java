package com.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Salaries;

@Repository
public interface SalariesRepositoryDAO extends JpaRepository<Salaries, String> {

}