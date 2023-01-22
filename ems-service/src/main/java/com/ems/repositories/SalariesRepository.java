package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Salaries;

public interface SalariesRepository extends JpaRepository<Salaries, String> {

}