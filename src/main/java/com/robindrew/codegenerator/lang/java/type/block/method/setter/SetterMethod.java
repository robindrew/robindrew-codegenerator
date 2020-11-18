package com.robindrew.codegenerator.lang.java.type.block.method.setter;

import static com.robindrew.codegenerator.lang.java.type.JavaTypeClass.VOID;

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
		this(fieldName, parameterType, block, VOID);
	}

	public SetterMethod(IJavaName fieldName, IJavaType parameterType, IJavaCodeLines block, IJavaType returnType) {
		super(getName(fieldName), returnType);
		this.fieldName = fieldName;
		getParameters().add(fieldName, parameterType);

		// Comment
		setStandardComment();

		// Content
		block.line("this." + fieldName + " = " + fieldName + ";");
		if (!returnType.equals(VOID)) {
			block.line("return this;");
		}
		setContents(block);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SetterMethod(IJavaName name, Class<?> parameterType) {
		this(name, new JavaTypeClass(parameterType), new JavaCodeLines());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SetterMethod(String name, Class<?> parameterType) {
		this(new JavaName(name), new JavaTypeClass(parameterType), new JavaCodeLines());
	}

	public SetterMethod(String name, IJavaType parameterType) {
		this(new JavaName(name), parameterType, new JavaCodeLines());
	}

	public SetterMethod(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType(), new JavaCodeLines());
	}

	public SetterMethod(String name, IJavaType parameterType, IJavaCodeLines block) {
		this(new JavaName(name), parameterType, block);
	}

	public SetterMethod(String name, IJavaType parameterType, IJavaCodeLines block, IJavaType returnType) {
		this(new JavaName(name), parameterType, block, returnType);
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
