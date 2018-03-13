package com.robindrew.codegenerator.lang.java.type.block.codeblock;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaCodeBlock implements IJavaCodeBlock {

	private final StringBuilder code = new StringBuilder();

	public JavaCodeBlock(Object code) {
		append(code);
	}

	public JavaCodeBlock append(Object code) {
		if (code == null) {
			throw new NullPointerException("code");
		}
		this.code.append(code);
		return this;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.write(code);
	}

}
