package com.ems.bean;

public class DepartmentsBean {
	
	private String deptNo;
	private String deptName;

	public DepartmentsBean() {
		super();
	}
		
	/**
	 * @param deptNo
	 * @param deptName
	 */
	public DepartmentsBean(String deptNo, String deptName) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
