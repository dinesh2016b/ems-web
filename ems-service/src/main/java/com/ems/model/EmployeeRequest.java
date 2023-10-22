package com.ems.model;

import com.ems.bean.DepartmentsBean;
import com.ems.bean.SalariesBean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class EmployeeRequest {
	private long empNo;
	private String firstName;
	private String lastName;
	private int pageNo;
	private int size;
	private String birthDate;
	private DepartmentsBean departmentsBean;
	private SalariesBean salariesBean;
}
