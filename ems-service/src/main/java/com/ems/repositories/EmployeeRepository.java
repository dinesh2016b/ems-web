/**
 * 
 */
package com.ems.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ems.entity.Employees;

/**
 * @author dinesh
 *
 */
public interface EmployeeRepository extends PagingAndSortingRepository<Employees, Long> {
	
	Page<Employees> findAll(Pageable pageable);

	Page<Employees> findByFirstName(String firstName, Pageable pageable);

	Slice<Employees> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

	Employees findById(Long employeeId);

	void save(Employees employees);
}
