package com.robindrew.codegenerator.lang.java.type.block.method.setter;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class SetterMethod extends JavaMethod {

	private static IJavaName getName(IJavaName name) {
		return new JavaName("set" + name.toUpper().toString());
	}

	private final IJavaName fieldName;

	public SetterMethod(IJavaName fieldName, IJavaType parameterType, IJavaCodeLines block) {
		super(getName(fieldName));
		this.fieldName = fieldName;
		getParameters().add(fieldName, parameterType);

		// Comment
		setStandardComment();

		// Content
		block.line("this." + fieldName + " = " + fieldName + ";");
		setContents(block);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SetterMethod(IJavaName name, Class<?> returnType) {
		this(name, new JavaTypeClass(returnType), new JavaCodeLines());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SetterMethod(String name, Class<?> returnType) {
		this(new JavaName(name), new JavaTypeClass(returnType), new JavaCodeLines());
	}

	public SetterMethod(String name, IJavaType returnType) {
		this(new JavaName(name), returnType, new JavaCodeLines());
	}

	public SetterMethod(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType(), new JavaCodeLines());
	}

	public SetterMethod(String name, IJavaType returnType, IJavaCodeLines block) {
		this(new JavaName(name), returnType, block);
	}

	public IJavaName getFieldName() {
		return fieldName;
	}

	public SetterMethod setStandardComment() {
		getComment().line("Setter for the " + fieldName + " field.");
		getComment().line("@param " + fieldName + " the " + fieldName + " value to set.");
		return this;
	}

}
