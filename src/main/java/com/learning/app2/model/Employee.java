package com.learning.app2.model;

public class Employee {

	private String employeeId;

	private String employeeName;

	private String employeeEmail;

	private String employeeAddresss;

	public Employee() {
		super();
	}

	public String getEmployeeAddresss() {
		return employeeAddresss;
	}

	public void setEmployeeAddresss(String employeeAddresss) {
		this.employeeAddresss = employeeAddresss;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", employeeAddresss=" + employeeAddresss + "]";
	}

}
