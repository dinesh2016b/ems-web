package com.ems.bean;

public class EmployeesBean {

	private long empNo;
	private String firstName;
	private String lastName;
	private String birthDate;
	private DepartmentsBean departmentsBean;
	private SalariesBean salariesBean;
	
	public EmployeesBean() {
		super();
	}

	public EmployeesBean(long empNo, String firstName, String lastName, String birthDate,
			DepartmentsBean departmentsBean, SalariesBean salariesBean) {
		super();
		this.empNo = empNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.departmentsBean = departmentsBean;
		this.salariesBean = salariesBean;
	}

	public long getEmpNo() {
		return empNo;
	}

	public void setEmpNo(long empNo) {
		this.empNo = empNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public DepartmentsBean getDepartmentsBean() {
		return departmentsBean;
	}

	public void setDepartmentsBean(DepartmentsBean departmentsBean) {
		this.departmentsBean = departmentsBean;
	}

	public SalariesBean getSalariesBean() {
		return salariesBean;
	}

	public void setSalariesBean(SalariesBean salariesBean) {
		this.salariesBean = salariesBean;
	}

	@Override
	public String toString() {
		return "EmployeesBean [empNo=" + empNo + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", departmentName=" + departmentsBean.getDeptName() + "]";
	}
}