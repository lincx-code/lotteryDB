/**
 * This is the application error log class that prints out
 * timestamp, class, and method when exception occurs.
 *
 * @author		Chenxin Lin
 * @version		1.0
 * @since		2018-07-01
 */

package com.code.common.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLog {
	private Exception ex;
	private String message;

	public ErrorLog() {
		// Default constructor - do nothing
	}

	public ErrorLog(Exception ex, String message) {
		this.ex = ex;
		this.message = message;
	}

	// Getters and setters *********************************************
	public Exception getExceptoin() {
		return ex;
	}

	public void setException(Exception ex) {
		this.ex = ex;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// Class sub methods *********************************************
	public void printErrorLog() {
		String errorLog = "|ERROR| " + getTimestamp() + " " + getCurrentMethod();
		System.out.println("====================================================================");
		System.out.println(errorLog);

		if (message != null && !message.equals("")) {
			System.out.println("|MESSAGE| " + message);
		}

		System.out.println("|STACK TRACE|");
		ex.printStackTrace(System.out);
		System.out.println("====================================================================");
	}

	public String getTimestamp() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String getCurrentMethod() {

		// Developer's notes
		/*
		 * for (int i = 0; i < Thread.currentThread().getStackTrace().length; i++) { System.out.println(i + " : " +
		 * Thread.currentThread().getStackTrace()[i]); }
		 */

		return "[" + Thread.currentThread().getStackTrace()[3].getClassName() + "] --> ["
				+ Thread.currentThread().getStackTrace()[3].getMethodName() + "]";
	}
}
