package com.robindrew.codegenerator.lang.java.type.block.method.getter;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class GetterMethod extends JavaMethod {

	private static IJavaName getName(IJavaName name) {
		return new JavaName("get" + name.toUpper());
	}

	private final IJavaName fieldName;
	private boolean notNull = false;

	public GetterMethod(IJavaName fieldName, IJavaType returnType, IJavaCodeLines block) {
		super(getName(fieldName), returnType);
		this.fieldName = fieldName;

		// Comment
		setStandardComment();

		// Contents
		block.line("return " + fieldName + ";");
		setContents(block);
	}

	public GetterMethod(IJavaName fieldName, IJavaType returnType) {
		this(fieldName, returnType, new JavaCodeLines());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GetterMethod(IJavaName name, Class<?> returnType) {
		this(name, new JavaTypeClass(returnType));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GetterMethod(String name, Class<?> returnType) {
		this(new JavaName(name), new JavaTypeClass(returnType));
	}

	public GetterMethod(String name, IJavaType returnType) {
		this(new JavaName(name), returnType);
	}

	public GetterMethod(IJavaNamedType namedType, IJavaCodeLines block) {
		this(namedType.getName(), namedType.getType(), block);
	}

	public GetterMethod(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType());
	}

	public GetterMethod(String name, IJavaType returnType, IJavaCodeLines block) {
		this(new JavaName(name), returnType, block);
	}

	public IJavaName getFieldName() {
		return fieldName;
	}

	public GetterMethod setNonNull(boolean notNull) {
		this.notNull = notNull;
		return this;
	}

	public GetterMethod setStandardComment() {
		getComment().line("Getter for the " + fieldName + " field.");
		getComment().line("@return the value of the " + fieldName + " field.");
		return this;
	}

	public GetterMethod setDefaultContents() {
		IJavaCodeLines code = new JavaCodeLines();
		return setDefaultContents(code);
	}

	public GetterMethod setDefaultContents(IJavaCodeLines code) {
		if (notNull) {
			new NullPointerConstraint(fieldName.get()).appendTo(code);
		}
		code.line("return " + fieldName + ";");
		setContents(code);
		return this;
	}
}
