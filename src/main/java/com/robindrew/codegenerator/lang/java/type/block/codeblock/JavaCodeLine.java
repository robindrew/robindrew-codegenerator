package com.robindrew.codegenerator.lang.java.type.block.codeblock;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWritable;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaCodeLine implements IJavaWritable {

	private final int indent;
	private final CharSequence line;

	public JavaCodeLine(int indent, CharSequence line) {
		if (line == null) {
			throw new NullPointerException("line");
		}
		this.line = line;
		this.indent = indent;
	}

	public int getIndent() {
		return indent;
	}

	public CharSequence getLine() {
		return line;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.write(line);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, SHORT_PREFIX_STYLE);
	}
}
