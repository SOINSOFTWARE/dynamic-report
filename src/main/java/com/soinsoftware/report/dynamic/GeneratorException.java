package com.soinsoftware.report.dynamic;

/**
 * @author Carlos Rodriguez
 *
 */
public class GeneratorException extends RuntimeException {

	private static final long serialVersionUID = -8263813744760235903L;
	
	public GeneratorException(String message, Throwable throwable) {
		super(message, throwable);
	}
}