package com.robindrew.codegenerator.lang.java.type.block.comment;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class MultiComment implements IMultiComment {

	private final List<String> lines = new ArrayList<String>(1);
	private boolean singleLine = false;

	@Override
	public int size() {
		return lines.size();
	}

	@Override
	public boolean isEmpty() {
		return lines.isEmpty();
	}

	@Override
	public void clear() {
		lines.clear();
	}

	@Override
	public MultiComment line() {
		return line("");
	}

	@Override
	public MultiComment line(Object line) {
		if (line == null) {
			throw new NullPointerException("line");
		}
		lines.add(line.toString());
		return this;
	}

	@Override
	public MultiComment lines(Object... lines) {
		for (Object line : lines) {
			line(line);
		}
		return this;
	}

	@Override
	public MultiComment setSingleLine(boolean singleLine) {
		this.singleLine = singleLine;
		return this;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (isEmpty()) {
			return;
		}

		// Single line
		if (singleLine && size() == 1) {
			writer.indent();
			writer.write("/** ").write(lines.get(0)).write(" */").line();
			return;
		}

		// Many lines
		writer.indent();
		writer.write("/**").line();
		for (String line : lines) {
			writer.indent();
			writer.write(" * ").write(line).line();
		}
		writer.indent();
		writer.write(" */").line();
	}

}
