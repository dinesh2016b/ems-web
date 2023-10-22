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
public class DepartmentsBean {
	private String deptNo;
	private String deptName;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
}
