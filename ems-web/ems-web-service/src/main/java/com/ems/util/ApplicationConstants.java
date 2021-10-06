package com.ems.util;

public class ApplicationConstants {

	public static final String[] FILTER_PATTERNS = new String[] {"/*"};
	
	public static final String URI_PARAM_ID = "id";
	public static final String URI_PARAM_PAGE_NUMBER = "pageNo";
	public static final String URI_PARAM_PAGE_SIZE = "size";
	
	// Employee API
	public static final String ENDPOINT_GET_EMPLOYEES = "/employees/pageNo/{"+URI_PARAM_PAGE_NUMBER+"}/size/{"+URI_PARAM_PAGE_SIZE+"}";
	public static final String ENDPOINT_GET_EMPLOYEE_BY_ID = "/employees/id/{"+URI_PARAM_ID+"}";
	public static final String ENDPOINT_CREATE_EMPLOYEE = "/employees/create";
	public static final String ENDPOINT_UPDATE_EMPLOYEE = "/employees/update/{"+URI_PARAM_ID+"}";
	public static final String ENDPOINT_DELETE_EMPLOYEE = "/employees/delete/{"+URI_PARAM_ID+"}";
	
	// Department API
	public static final String ENDPOINT_GET_DEPARTMENTS = "/departments/pageNo/{"+URI_PARAM_PAGE_NUMBER+"}/size/{"+URI_PARAM_PAGE_SIZE+"}";
	public static final String ENDPOINT_GET_DEPARTMENT_BY_ID = "/departments/id/{"+URI_PARAM_ID+"}";
	public static final String ENDPOINT_CREATE_DEPARTMENT = "/departments/create";
	public static final String ENDPOINT_UPDATE_DEPARTMENT = "/departments/update/{"+URI_PARAM_ID+"}";
	public static final String ENDPOINT_DELETE_DEPARTMENT = "/departments/delete/{"+URI_PARAM_ID+"}";

	// Salary API
	public static final String ENDPOINT_GET_SALARY_EMPLOYEE_BY_ID = "/salary/id/{"+URI_PARAM_ID+"}";

}
