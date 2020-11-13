package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class Equals {

	private boolean not = false;
	private IJavaType type = null;

	private String fieldName1 = null;
	private String methodName1 = null;

	private String fieldName2 = null;
	private String methodName2 = null;

	public Equals not() {
		not = true;
		return this;
	}

	public Equals field1(JavaModelBeanField field) {
		fieldName1 = field.getName();
		type = field.getType();
		return this;
	}

	public Equals field2(JavaModelBeanField field) {
		fieldName2 = field.getName();
		type = field.getType();
		return this;
	}

	public Equals field1(String fieldName) {
		fieldName1 = fieldName;
		return this;
	}

	public Equals field2(String fieldName) {
		fieldName2 = fieldName;
		return this;
	}

	public Equals method1(JavaModelBeanField field) {
		methodName1 = JavaName.toUpper(field.getName());
		type = field.getType();
		return this;
	}

	public Equals method2(JavaModelBeanField field) {
		methodName2 = JavaName.toUpper(field.getName());
		type = field.getType();
		return this;
	}

	public Equals method1(JavaModelBeanField field, String fieldName) {
		fieldName1 = fieldName;
		methodName1 = JavaName.toUpper(field.getName());
		type = field.getType();
		return this;
	}

	public Equals method2(JavaModelBeanField field, String fieldName) {
		fieldName2 = fieldName;
		methodName2 = JavaName.toUpper(field.getName());
		type = field.getType();
		return this;
	}

	private void appendVariable1(StringBuilder code) {
		appendVariable(code, fieldName1, methodName1);
	}

	private void appendVariable2(StringBuilder code) {
		appendVariable(code, fieldName2, methodName2);
	}

	private void appendVariable(StringBuilder code, String fieldName, String methodName) {

		// Just a field?
		if (methodName == null && fieldName != null) {
			code.append(fieldName);
			return;
		}

		// Field?
		if (fieldName != null) {
			code.append(fieldName).append(".");
		}

		// Method
		code.append("get").append(methodName).append("()");
	}

	public String toString() {
		return appendTo(new StringBuilder()).toString();
	}

	public StringBuilder appendTo(StringBuilder code) {

		// Special case - array equality
		if (type.isArray()) {
			if (not) {
				code.append("!");
			}
			code.append("Arrays.equals(");
			appendVariable1(code);
			code.append(", ");
			appendVariable2(code);
			code.append(")");
			return code;
		}

		// Primitive
		if (type.isPrimitive()) {
			appendVariable1(code);
			if (not) {
				code.append(" != ");
			} else {
				code.append(" == ");
			}
			appendVariable2(code);
			return code;
		}

		// Object
		if (not) {
			code.append("!");
		}
		appendVariable1(code);
		code.append(".equals(");
		appendVariable2(code);
		code.append(")");
		return code;
	}
}
