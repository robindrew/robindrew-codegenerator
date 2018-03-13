package com.robindrew.codegenerator.lang.java.type.block.eenum;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.block.comment.MultiComment;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaEnumField implements IJavaEnumField {

	private final IJavaName name;
	private final List<String> parameters = new ArrayList<String>();
	private IJavaCodeBlock contents = null;
	private final IJavaTypeSet references = new JavaTypeSet();
	private final IMultiComment comment = new MultiComment();

	public JavaEnumField(IJavaName name) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		this.name = name;
	}

	public JavaEnumField(String name) {
		this(new JavaName(name));
	}

	@Override
	public IMultiComment getComment() {
		return comment;
	}

	public void setComment(Object singleLine) {
		comment.clear();
		comment.line(singleLine);
		comment.setSingleLine(true);
	}

	public void setStandardComment() {
		setComment("The " + getName() + " constant.");
	}

	@Override
	public IJavaName getName() {
		return name;
	}

	@Override
	public IJavaCodeBlock getContents() {
		return contents;
	}

	@Override
	public boolean hasContents() {
		return contents != null;
	}

	@Override
	public void setContents(IJavaCodeBlock block) {
		this.contents = block;
	}

	@Override
	public IJavaTypeSet getReferences() {
		return references;
	}

	@Override
	public List<String> getParameters() {
		return unmodifiableList(parameters);
	}

	@Override
	public void addParameter(String value) {
		if (value == null) {
			throw new NullPointerException("value");
		}
		parameters.add(value);
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (!comment.isEmpty()) {
			comment.writeTo(writer);
		}
		writer.indent();

		// Method name
		writer.write(getName().toConst());

		// Parameters
		writeParameters(writer);

		// Contents
		writeContents(writer);

	}

	private void writeParameters(IJavaWriter writer) {
		if (getParameters().isEmpty()) {
			return;
		}

		writer.write('(');
		boolean comma = false;
		for (String parameter : getParameters()) {
			if (comma) {
				writer.write(", ");
			}
			comma = true;
			writer.write(parameter);
		}
		writer.write(')');
	}

	private void writeContents(IJavaWriter writer) {
		if (!hasContents()) {
			return;
		}

		writer.write(" {");
		writer.line();
		if (hasContents()) {
			writer.shiftIndent(1);
			getContents().writeTo(writer);
			writer.shiftIndent(-1);
		}
		writer.indent();
		writer.write("}");
		writer.line();
	}

}
