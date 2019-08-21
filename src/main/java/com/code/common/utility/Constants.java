package com.code.common.utility;

public class Constants {
	// common request mappings **********************************
	public static final String ERROR_GENERAL = "/error";
	public static final String ERROR_DATABASE = "/error_database";
	public static final String ERROR_NOTFOUND = "/error_not_found";
	public static final String ERROR_DENIED = "/error_access_denied";
	public static final String ERROR_SESSION = "/login?invalid";
	public static final String ERROR_NOTSUPPORTED = "/error_not_supported";

	public static final String REQUEST_REDIRECT = "redirect:";
	public static final String REQUEST_RESOURCES = "/resources/**";
	public static final String REQUEST_INDEX = "/index";
	public static final String REQUEST_HOME = "/home";

	// default format *****************************************
	public static final String TOSTRING_PREFIX = "\n\t";
	public static final String DEFAULT_TIMEZONE = "America/New_York";
	public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss z";
	public static final int DEFAULT_DATA_PER_PAGE = 50;
	public static final int POWERBALL_MULTI = 69;
	public static final int POWERBALL_SINGLE = 26;
	public static final int MEGA_MULTI = 70;
	public static final int MEGA_SINGLE = 25;
	public static final int LOTTO_MULTI = 59;
	public static final int DEFAULT_REPORT_ROWS = 10;
}
