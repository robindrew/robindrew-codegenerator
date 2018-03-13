package com.robindrew.codegenerator.lang.java.type.block.codeblock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaCodeLines implements IJavaCodeLines {

	private final List<JavaCodeLine> lineList = new ArrayList<JavaCodeLine>();
	private final IJavaTypeSet references = new JavaTypeSet();
	private int baseIndent = 0;

	public JavaCodeLines() {
	}

	public JavaCodeLines(int indent, CharSequence line) {
		line(indent, line);
	}

	public JavaCodeLines(CharSequence line) {
		line(0, line);
	}

	public JavaCodeLines(CharSequence... lines) {
		lines(0, lines);
	}

	public JavaCodeLines(Collection<? extends CharSequence> lines) {
		lines(0, lines);
	}

	public JavaCodeLines(int indent, Collection<? extends CharSequence> lines) {
		lines(indent, lines);
	}

	public JavaCodeLines(int indent, CharSequence... lines) {
		lines(indent, lines);
	}

	@Override
	public IJavaTypeSet getReferences() {
		return references;
	}

	@Override
	public JavaCodeLines line(int indent, CharSequence line) {
		lineList.add(new JavaCodeLine(baseIndent + indent, line));
		return this;
	}

	@Override
	public JavaCodeLines lines(int indent, Collection<? extends CharSequence> lines) {
		if (lines.isEmpty()) {
			throw new IllegalArgumentException("lines is empty");
		}
		for (CharSequence line : lines) {
			line(indent, line);
		}
		return this;
	}

	@Override
	public JavaCodeLines line(CharSequence line) {
		return line(0, line);
	}

	@Override
	public JavaCodeLines lines(Collection<? extends CharSequence> lines) {
		return lines(0, lines);
	}

	@Override
	public JavaCodeLines lines(int indent, CharSequence... lines) {
		return lines(indent, Arrays.asList(lines));
	}

	@Override
	public JavaCodeLines lines(CharSequence... lines) {
		return lines(0, lines);
	}

	@Override
	public JavaCodeLines emptyLine() {
		return line(Integer.MIN_VALUE, "");
	}

	@Override
	public String toString() {
		return lineList.toString();
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		for (JavaCodeLine line : lineList) {
			int indent = line.getIndent();
			if (indent == Integer.MIN_VALUE) {
				int oldIndent = writer.getIndent();
				writer.setIndent(0);
				line.writeTo(writer);
				writer.line();
				writer.setIndent(oldIndent);
			} else {
				writer.shiftIndent(indent);
				writer.indent();
				line.writeTo(writer);
				writer.line();
				writer.shiftIndent(-indent);
			}
		}
	}

	@Override
	public void setIndent(int indent) {
		baseIndent += indent;
	}

}
