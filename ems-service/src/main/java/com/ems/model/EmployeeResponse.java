package com.ems.model;

import java.sql.Date;

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
public class EmployeeResponse {
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
