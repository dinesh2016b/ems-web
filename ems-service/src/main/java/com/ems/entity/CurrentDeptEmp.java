package com.ems.entity;
// Generated Aug 11, 2019 12:49:44 AM by Hibernate Tools 4.3.5.Final

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * CurrentDeptEmp generated by hbm2java
 */
@Entity
@Table(name = "current_dept_emp", catalog = "employees")
public class CurrentDeptEmp implements java.io.Serializable {

	private CurrentDeptEmpId id;

	public CurrentDeptEmp() {
	}

	public CurrentDeptEmp(CurrentDeptEmpId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "empNo", column = @Column(name = "emp_no", nullable = false)),
			@AttributeOverride(name = "deptNo", column = @Column(name = "dept_no", nullable = false, length = 4)),
			@AttributeOverride(name = "fromDate", column = @Column(name = "from_date", length = 10)),
			@AttributeOverride(name = "toDate", column = @Column(name = "to_date", length = 10)) })
	public CurrentDeptEmpId getId() {
		return this.id;
	}

	public void setId(CurrentDeptEmpId id) {
		this.id = id;
	}

}
