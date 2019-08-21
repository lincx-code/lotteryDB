package com.code.lottery;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.common.utility.Constants;

@Controller
public class MainController {

	// Request mapping for Home ******************************
	@RequestMapping(value = { "/", "/home", "/index" }, method = RequestMethod.GET)
	public String doPageHome(final HttpServletRequest request) {
		return Constants.REQUEST_INDEX;
	}

	// Error page URL patterns *********************************************

	// General error URL
	@RequestMapping(value = { "/error", "/error.jsp" }, method = RequestMethod.GET)
	public String errorGeneral(final HttpServletRequest request, final ModelMap model) {
		return Constants.ERROR_GENERAL;
	}

	// Access denied URL
	@RequestMapping(value = { "/error_access_denied", "/error_access_denied.jsp" }, method = RequestMethod.GET)
	public String errorAccessDenied(final HttpServletRequest request, final ModelMap model) {
		return Constants.ERROR_DENIED;
	}

	// Page not found URL
	@RequestMapping(value = { "/error_not_found", "/error_not_found.jsp" }, method = RequestMethod.GET)
	public String errorPageNotFound(final HttpServletRequest request, final ModelMap model) {
		return Constants.ERROR_NOTFOUND;
	}

	// Page method not supported URL
	@RequestMapping(value = { "/error_not_supported", "/error_not_supported.jsp" }, method = RequestMethod.GET)
	public String errorMethodNotSupported(final HttpServletRequest request, final ModelMap model) {
		return Constants.ERROR_NOTSUPPORTED;
	}

	// Database error URL
	@RequestMapping(value = { "/error_database", "/error_database.jsp" }, method = RequestMethod.GET)
	public String errorDatabase(final HttpServletRequest request, final ModelMap model) {
		return Constants.ERROR_DATABASE;
	}
}
