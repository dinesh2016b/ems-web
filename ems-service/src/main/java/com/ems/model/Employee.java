package com.ems.model;

import com.ems.bean.DepartmentsBean;
import com.ems.bean.SalariesBean;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Employee {
	private long empNo;
	private String firstName;
	private String lastName;
	private int pageNo;
	private int size;
	private String birthDate;
	private DepartmentsBean departmentsBean;
	private SalariesBean salariesBean;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
}
