package com.ems.bean;

import java.sql.Date;

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
public class EmployeesBean {
	private long empNo;
	private String firstName;
	private String lastName;
	private String birthDate;
	private DepartmentsBean departmentsBean;
	private SalariesBean salariesBean;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
}