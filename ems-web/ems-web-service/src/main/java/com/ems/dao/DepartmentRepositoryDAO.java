package com.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.Departments;

@Repository
public interface DepartmentRepositoryDAO extends JpaRepository<Departments, String> {

}