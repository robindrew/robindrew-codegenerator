package com.robindrew.codegenerator.model.reader;

public class ModelReadException extends RuntimeException {

	private static final long serialVersionUID = -3148929554806232114L;

	public ModelReadException(Throwable cause) {
		super(cause);
	}

	public ModelReadException(String message, Throwable cause) {
		super(message, cause);
	}

}
