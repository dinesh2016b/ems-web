package com.ems.util;

public class ApplicationConstants {

	public static final String[] FILTER_PATTERNS = new String[] { "/*" };

	public static final String URI_PARAM_ID = "id";
	public static final String URI_PARAM_PAGE_NUMBER = "pageNo";
	public static final String URI_PARAM_PAGE_SIZE = "size";
	//Login 
	public static final String ENDPOINT_SIGNIN = "/services/auth/signin";	
	public static final String ENDPOINT_SIGNOUT = "/services/auth/signout";
	public static final String ENDPOINT_SIGNUP = "/services/auth/signup";
	
	//User Profile 
	public static final String ENDPOINT_GET_USER_PROFILE = "/services/v1/user-management/getUserProfile";
	public static final String ENDPOINT_SAVE_USER_PROFILE = "/services/v1/user-management/saveUserProfile";	
	
	//Authenticate
	public static final String ENDPOINT_AUTHENTICATE = "/services/v1/authenticate";
	// Employee API
	public static final String ENDPOINT_GET_EMPLOYEES = "/services/v1/employees/all";
	public static final String ENDPOINT_GET_EMPLOYEE_BY_ID = "/services/v1/employees/id";
	public static final String ENDPOINT_CREATE_EMPLOYEE = "/services/v1/employees";
	public static final String ENDPOINT_UPDATE_EMPLOYEE = "/services/v1/employees";
	public static final String ENDPOINT_DELETE_EMPLOYEE = "/services/v1/employees";
	// Department API
	public static final String ENDPOINT_GET_DEPARTMENTS = "/services/v1/departments/pageNo/{" + URI_PARAM_PAGE_NUMBER + "}/size/{"+ URI_PARAM_PAGE_SIZE + "}";
	public static final String ENDPOINT_GET_DEPARTMENT_BY_ID = "/services/v1/departments/{" + URI_PARAM_ID + "}";
	public static final String ENDPOINT_CREATE_DEPARTMENT = "/services/v1/departments/create";
	public static final String ENDPOINT_UPDATE_DEPARTMENT = "/services/v1/departments/update/{" + URI_PARAM_ID + "}";
	public static final String ENDPOINT_DELETE_DEPARTMENT = "/services/v1/departments/delete/{" + URI_PARAM_ID + "}";
	// Salary API
	public static final String ENDPOINT_GET_SALARY_EMPLOYEE_BY_ID = "/services/v1/salaries/id/{" + URI_PARAM_ID + "}";
}