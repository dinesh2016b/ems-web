package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Salaries;

@Repository
public interface SalariesRepository extends JpaRepository<Salaries, String> {

}