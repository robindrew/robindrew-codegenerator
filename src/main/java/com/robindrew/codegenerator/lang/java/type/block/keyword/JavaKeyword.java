package com.robindrew.codegenerator.lang.java.type.block.keyword;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public enum JavaKeyword implements IJavaKeyword {

	/** The class keyword. */
	CLASS("class"),
	/** The interface keyword. */
	INTERFACE("interface"),
	/** The extends keyword. */
	EXTENDS("extends"),
	/** The implements keyword. */
	IMPLEMENTS("implements"),
	/** The public keyword. */
	PUBLIC("public"),
	/** The protected keyword. */
	PROTECTED("protected"),
	/** The private keyword. */
	PRIVATE("private"),
	/** The abstract keyword. */
	ABSTRACT("abstract"),
	/** The static keyword. */
	STATIC("static"),
	/** The final keyword. */
	FINAL("final"),
	/** The transient keyword. */
	TRANSIENT("transient"),
	/** The volatile keyword. */
	VOLATILE("volatile");

	private final String keyword;

	private JavaKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String get() {
		return keyword;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.write(keyword);
		writer.space();
	}

}
