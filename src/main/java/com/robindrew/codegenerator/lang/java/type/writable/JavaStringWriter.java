package com.robindrew.codegenerator.lang.java.type.writable;

import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class JavaStringWriter implements IJavaWriter {

	private final StringBuilder writer = new StringBuilder();

	private int indent = 0;
	private String tab = "\t";

	public JavaStringWriter setTab(String tab) {
		if (tab.isEmpty()) {
			throw new IllegalArgumentException("tab is empty");
		}
		this.tab = tab;
		return this;
	}

	@Override
	public JavaStringWriter write(IJavaWritable writable, boolean write) {
		if (write) {
			writable.writeTo(this);
		}
		return this;
	}

	@Override
	public JavaStringWriter write(IJavaWritable writable) {
		return write(writable, true);
	}

	@Override
	public JavaStringWriter write(String value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(boolean value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(byte value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(short value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(int value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(long value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(float value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(double value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter write(char value) {
		writer.append(value);
		return this;
	}

	@Override
	public JavaStringWriter line() {
		return write('\n');
	}

	@Override
	public JavaStringWriter space() {
		return write(' ');
	}

	@Override
	public JavaStringWriter shiftIndent(int value) {
		if (indent + value < 0) {
			throw new IllegalArgumentException("current indent=" + indent + ", value=" + value);
		}
		indent += value;
		return this;
	}

	@Override
	public JavaStringWriter indent() {
		if (indent > 0) {
			for (int i = 0; i < indent; i++) {
				writer.append(tab);
			}
		}
		return this;
	}

	@Override
	public String toString() {
		return writer.toString();
	}

	@Override
	public JavaStringWriter write(IJavaType type) {
		write(type.getDeclaration());
		return this;
	}

	@Override
	public JavaStringWriter semi() {
		write(';');
		return this;
	}

	@Override
	public JavaStringWriter write(CharSequence value) {
		return write(value.toString());
	}

	@Override
	public JavaStringWriter comma() {
		write(',');
		return this;
	}

	@Override
	public int getIndent() {
		return indent;
	}

	@Override
	public IJavaWriter setIndent(int indent) {
		this.indent = indent;
		return this;
	}

}
