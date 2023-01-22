package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.Departments;

public interface DepartmentRepository extends JpaRepository<Departments, String> {

}