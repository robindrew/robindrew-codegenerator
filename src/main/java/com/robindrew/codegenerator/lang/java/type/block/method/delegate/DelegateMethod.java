package com.robindrew.codegenerator.lang.java.type.block.method.delegate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class DelegateMethod extends JavaMethod {

	private static final List<IJavaNamedType> getParameters(Method method) {
		List<IJavaNamedType> list = new ArrayList<IJavaNamedType>();
		int count = 0;
		for (Class<?> type : method.getParameterTypes()) {
			count++;
			String name = "param" + count;
			list.add(new JavaNamedType(name, type));
		}
		return list;
	}

	private String fieldName = DelegateField.DEFAULT_NAME;

	public DelegateMethod(String methodName, IJavaType returnType) {
		super(methodName, returnType);
		setOverride();
	}

	public DelegateMethod(String methodName) {
		super(methodName);
		setOverride();
	}

	public DelegateMethod(Method method) {
		super(method.getName(), method.getReturnType());
		getParameters().addAll(getParameters(method));
		setOverride();
	}

	public DelegateMethod(IJavaMethod method) {
		super(method.getName(), method.getReturnType());
		getParameters().addAll(method.getParameters());
		setOverride();
	}

	public DelegateMethod setFieldName(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		this.fieldName = name;
		return this;
	}

	public DelegateMethod setDelegateContents() {
		IJavaCodeLines contents = new JavaCodeLines();
		setDelegateContents(contents);
		setContents(contents);
		return this;
	}

	public IJavaCodeLines setDelegateContents(IJavaCodeLines lines) {
		if (getReturnType().isVoid()) {
			lines.line(getDelegateCall() + ";");
		} else {
			lines.line("return " + getDelegateCall() + ";");
		}
		return lines;
	}

	protected String getDelegateCall(boolean includeField) {
		if (includeField) {
			return fieldName + "." + getName() + "(" + getParameterNames() + ")";
		} else {
			return getName() + "(" + getParameterNames() + ")";
		}
	}

	protected String getDelegateCall() {
		return getDelegateCall(true);
	}

	private String getParameterNames() {
		StringBuilder text = new StringBuilder();
		boolean comma = false;
		for (IJavaNamedType parameter : getParameters()) {
			if (comma) {
				text.append(", ");
			}
			comma = true;
			text.append(parameter.getName());
		}
		return text.toString();
	}
}
