package com.robindrew.codegenerator.lang.java.generator.model.common;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.common.ModelMethod;

public class JavaModelMethod extends JavaModelTyped<ModelMethod> {

	private final List<JavaModelMethodParameter> parameterList;

	public JavaModelMethod(ModelMethod method, List<JavaModelMethodParameter> parameterList) {
		super(method);
		this.parameterList = parameterList;
	}

	public String getName() {
		return get().getName();
	}

	public IJavaType getReturnType() {
		return getType();
	}

	public List<JavaModelMethodParameter> getParameterList() {
		return parameterList;
	}

	public String getReturnValue() {
		return get().getReturnValue();
	}

}
