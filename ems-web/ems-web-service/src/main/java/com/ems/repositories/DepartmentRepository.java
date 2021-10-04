package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Departments;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, String> {

}