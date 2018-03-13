package com.robindrew.codegenerator.lang.java.generator;

public class JavaModelGenerationException extends RuntimeException {

	private static final long serialVersionUID = 3191967633655619887L;

	public JavaModelGenerationException(String message, Throwable t) {
		super(message, t);
	}

	public JavaModelGenerationException(String message) {
		super(message);
	}

	public JavaModelGenerationException(Throwable t) {
		super(t);
	}
}
