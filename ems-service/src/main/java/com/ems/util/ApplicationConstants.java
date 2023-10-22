package com.ems.util;

public class ApplicationConstants {

	public static final String[] FILTER_PATTERNS = new String[] { "/*" };

	public static final String URI_PARAM_ID = "id";
	public static final String URI_PARAM_PAGE_NUMBER = "pageNo";
	public static final String URI_PARAM_PAGE_SIZE = "size";
	//Login 
	public static final String ENDPOINT_LOGIN = "/apiservice/auth/login";	
	//Logout 
	public static final String ENDPOINT_LOGOUT = "/apiservice/auth/logout";
	//Authenticate
	public static final String ENDPOINT_AUTHENTICATE = "/apiservice/authenticate";
	// Employee API
	public static final String ENDPOINT_GET_EMPLOYEES = "/apiservice/employees/all";
	public static final String ENDPOINT_GET_EMPLOYEE_BY_ID = "/apiservice/employees/id";
	public static final String ENDPOINT_CREATE_EMPLOYEE = "/apiservice/employees/create";
	public static final String ENDPOINT_UPDATE_EMPLOYEE = "/apiservice/employees/update";
	public static final String ENDPOINT_DELETE_EMPLOYEE = "/apiservice/employees/delete";
	// Department API
	public static final String ENDPOINT_GET_DEPARTMENTS = "/apiservice/departments/pageNo/{" + URI_PARAM_PAGE_NUMBER + "}/size/{"+ URI_PARAM_PAGE_SIZE + "}";
	public static final String ENDPOINT_GET_DEPARTMENT_BY_ID = "/apiservice/departments/{" + URI_PARAM_ID + "}";
	public static final String ENDPOINT_CREATE_DEPARTMENT = "/apiservice/departments/create";
	public static final String ENDPOINT_UPDATE_DEPARTMENT = "/apiservice/departments/update/{" + URI_PARAM_ID + "}";
	public static final String ENDPOINT_DELETE_DEPARTMENT = "/apiservice/departments/delete/{" + URI_PARAM_ID + "}";
	// Salary API
	public static final String ENDPOINT_GET_SALARY_EMPLOYEE_BY_ID = "/apiservice/salaries/id/{" + URI_PARAM_ID + "}";
}