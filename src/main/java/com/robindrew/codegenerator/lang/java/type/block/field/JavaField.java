package com.robindrew.codegenerator.lang.java.type.block.field;

import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.FINAL;
import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.STATIC;
import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.TRANSIENT;
import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.VOLATILE;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.annotation.IJavaAnnotationSet;
import com.robindrew.codegenerator.lang.java.type.block.annotation.JavaAnnotationSet;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.block.comment.MultiComment;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.visibility.IJavaVisibility;
import com.robindrew.codegenerator.lang.java.type.block.visibility.JavaVisibility;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaField extends JavaNamedType implements IJavaField {

	private IJavaVisibility visibility = JavaVisibility.PRIVATE;
	private boolean isStatic = false;
	private boolean isConstant = false;
	private boolean isTransient = false;
	private boolean isVolatile = false;
	private boolean isFinal = false;
	private boolean isInterface = false;
	private IJavaCodeBlock assignment = null;
	private final IJavaTypeSet references = new JavaTypeSet();
	private final IMultiComment comment = new MultiComment();
	private final IJavaAnnotationSet annotations = new JavaAnnotationSet();

	public JavaField(IJavaName name, IJavaType type) {
		super(name, type);
		getReferences().add(type);
	}

	public JavaField(String name, IJavaType type) {
		this(new JavaName(name), type);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaField(String name, Class<?> type) {
		this(new JavaName(name), new JavaTypeClass(type));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaField(IJavaName name, Class<?> type) {
		this(name, new JavaTypeClass(type));
	}

	public JavaField(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType());
	}

	@Override
	public IMultiComment getComment() {
		return comment;
	}

	@Override
	public IJavaVisibility getVisibility() {
		return visibility;
	}

	@Override
	public JavaField setVisibility(IJavaVisibility visibility) {
		if (visibility == null) {
			throw new NullPointerException("visibility");
		}
		this.visibility = visibility;
		return this;
	}

	@Override
	public boolean isStatic() {
		return isStatic;
	}

	@Override
	public boolean isTransient() {
		return isTransient;
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

	@Override
	public boolean isConstant() {
		return isConstant;
	}

	@Override
	public boolean isVolatile() {
		return isVolatile;
	}

	@Override
	public boolean isInterface() {
		return isInterface;
	}

	@Override
	public JavaField setStatic(boolean value) {
		this.isStatic = value;
		return this;
	}

	@Override
	public JavaField setConstant(boolean value) {
		this.isConstant = value;
		return this;
	}

	@Override
	public JavaField setTransient(boolean value) {
		this.isTransient = value;
		return this;
	}

	@Override
	public JavaField setVolatile(boolean value) {
		this.isVolatile = value;
		return this;
	}

	@Override
	public JavaField setFinal(boolean value) {
		this.isFinal = value;
		return this;
	}

	@Override
	public JavaField setInterface(boolean value) {
		this.isInterface = value;
		return this;
	}

	@Override
	public IJavaCodeBlock getAssignment() {
		return assignment;
	}

	@Override
	public boolean hasAssignment() {
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
	public JavaField setAssignment(IJavaCodeBlock code) {
		if (code == null) {
			throw new NullPointerException("code");
		}
		this.assignment = code;
		return this;
	}

	@Override
	public JavaField setAssignment(Object code) {
		if (code == null) {
			throw new NullPointerException("code");
		}
		IJavaCodeBlock block = new JavaCodeBlock(code);
		return setAssignment(block);
	}

	@Override
	public IJavaAnnotationSet getAnnotations() {
		return annotations;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (!comment.isEmpty()) {
			comment.writeTo(writer);
		}
		writer.indent();

		// Modifiers
		if (!isInterface()) {
			writer.write(getVisibility());
			writer.write(STATIC, isStatic());
			writer.write(FINAL, isFinal());
			writer.write(TRANSIENT, isTransient());
			writer.write(VOLATILE, isVolatile());
		}

		// Return type
		writer.write(getType().getDeclaration(false));
		writer.space();

		// Field name
		if (isConstant()) {
			writer.write(getName().toConst());
		} else {
			writer.write(getName().toLower());
		}

		// Assignment?
		if (hasAssignment()) {
			writer.write(" = ");
			writer.write(getAssignment());
		}
		writer.write(';');
		writer.line();
	}

}
