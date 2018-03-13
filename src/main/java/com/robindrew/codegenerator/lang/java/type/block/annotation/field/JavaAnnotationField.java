package com.robindrew.codegenerator.lang.java.type.block.annotation.field;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.block.comment.MultiComment;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaAnnotationField extends JavaNamedType implements IJavaAnnotationField {

	private IJavaCodeBlock assignment = null;
	private final IJavaTypeSet references = new JavaTypeSet();
	private final IMultiComment comment = new MultiComment();

	public JavaAnnotationField(IJavaName name, IJavaType type) {
		super(name, type);
		getReferences().add(type);
	}

	public JavaAnnotationField(String name, IJavaType type) {
		this(new JavaName(name), type);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaAnnotationField(String name, Class<?> type) {
		this(new JavaName(name), new JavaTypeClass(type));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaAnnotationField(IJavaName name, Class<?> type) {
		this(name, new JavaTypeClass(type));
	}

	public JavaAnnotationField(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType());
	}

	@Override
	public IMultiComment getComment() {
		return comment;
	}

	@Override
	public IJavaCodeBlock getDefaultValue() {
		return assignment;
	}

	@Override
	public boolean hasDefaultValue() {
		return assignment != null;
	}

	@Override
	public IJavaTypeSet getReferences() {
		return references;
	}

	public void setComment(Object singleLine) {
		comment.clear();
		comment.line(singleLine);
		comment.setSingleLine(true);
	}

	public void setStandardComment() {
		setComment("The " + getName() + " field.");
	}

	@Override
	public void setDefaultValue(IJavaCodeBlock code) {
		if (code == null) {
			throw new NullPointerException("code");
		}
		this.assignment = code;
	}

	@Override
	public void setDefaultValue(Object code) {
		if (code == null) {
			throw new NullPointerException("code");
		}
		IJavaCodeBlock block = new JavaCodeBlock(code);
		setDefaultValue(block);
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (!comment.isEmpty()) {
			comment.writeTo(writer);
		}
		writer.indent();

		// Field type
		writer.write(getType().getDeclaration(false));
		writer.space();

		// Field name
		writer.write(getName().toLower());
		writer.write("()");

		// Default value?
		if (hasDefaultValue()) {
			writer.write(" default ");
			boolean quote = getType().isType(String.class);
			if (quote) {
				writer.write('\"');
			}
			writer.write(getDefaultValue());
			if (quote) {
				writer.write('\"');
			}
		}
		writer.write(';');
		writer.line();
	}

}
