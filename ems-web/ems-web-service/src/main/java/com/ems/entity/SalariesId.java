package com.ems.entity;
// Generated Aug 11, 2019 12:49:44 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SalariesId generated by hbm2java
 */
@Embeddable
public class SalariesId implements java.io.Serializable {

	private long empNo;
	private Date fromDate;

	public SalariesId() {
	}

	public SalariesId(int empNo, Date fromDate) {
		this.empNo = empNo;
		this.fromDate = fromDate;
	}

	@Column(name = "emp_no", nullable = false)
	public long getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(long empNo) {
		this.empNo = empNo;
	}

	@Column(name = "from_date", nullable = false, length = 10)
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SalariesId))
			return false;
		SalariesId castOther = (SalariesId) other;

		return (this.getEmpNo() == castOther.getEmpNo())
				&& ((this.getFromDate() == castOther.getFromDate()) || (this.getFromDate() != null
						&& castOther.getFromDate() != null && this.getFromDate().equals(castOther.getFromDate())));
	}

	public int hashCode() {
		int result = 17;

		result = (int) (37 * result + this.getEmpNo());
		result = 37 * result + (getFromDate() == null ? 0 : this.getFromDate().hashCode());
		return result;
	}

}
