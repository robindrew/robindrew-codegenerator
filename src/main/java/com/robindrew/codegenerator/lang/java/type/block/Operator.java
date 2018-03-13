package com.robindrew.codegenerator.lang.java.type.block;

public enum Operator {

	/** The greater than operator. */
	GREATER_THAN(">"),
	/** The greater than or equal to operator. */
	GREATER_THAN_EQUALS(">="),
	/** The less than operator. */
	LESS_THAN("<"),
	/** The less than or equal to operator. */
	LESS_THAN_EQUALS("<="),
	/** The equals operator. */
	EQUALS("=="),
	/** The not equals operator. */
	NOT_EQUALS("!=");

	private final String value;

	private Operator(String value) {
		this.value = value;
	}

	public String get() {
		return value;
	}

}
