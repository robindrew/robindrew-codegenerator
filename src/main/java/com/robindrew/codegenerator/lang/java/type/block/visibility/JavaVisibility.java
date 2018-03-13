package com.robindrew.codegenerator.lang.java.type.block.visibility;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public enum JavaVisibility implements IJavaVisibility {

	/** The public visibility. */
	PUBLIC("public"),
	/** The protected visibility. */
	PROTECTED("protected"),
	/** The private visibility. */
	PRIVATE("private"),
	/** The package-private visibility. */
	PACKAGE_PRIVATE("");

	private final String name;

	private JavaVisibility(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		if (name.isEmpty()) {
			throw new IllegalStateException("visibility has no name: " + name());
		}
		return name;
	}

	@Override
	public boolean hasName() {
		return !name.isEmpty();
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (hasName()) {
			writer.write(name);
			writer.space();
		}
	}

}
