package com.test.enums;

public enum HttpStatusCode implements IHttpStatusCode {

	/** The OK constant. */
	OK(200),
	/** The NOT_FOUND constant. */
	NOT_FOUND(404),
	/** The INTERNAL_SERVER_ERROR constant. */
	INTERNAL_SERVER_ERROR(500);

	/** The code field. */
	private final int code;

	private HttpStatusCode(int code) {
		this.code = code;
	}

	/**
	 * Getter for the code field.
	 * @return the value of the code field.
	 */
	public int getCode() {
		return code;
	}
}
