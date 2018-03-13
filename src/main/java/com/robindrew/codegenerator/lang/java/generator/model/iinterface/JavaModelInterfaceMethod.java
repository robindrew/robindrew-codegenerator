package com.robindrew.codegenerator.lang.java.generator.model.iinterface;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterfaceMethod;

public class JavaModelInterfaceMethod extends JavaModelTyped<ModelInterfaceMethod> {

	private final List<JavaModelInterfaceParameter> parameterList;

	public JavaModelInterfaceMethod(ModelInterfaceMethod method, List<JavaModelInterfaceParameter> parameterList) {
		super(method);
		this.parameterList = parameterList;
	}

	public String getName() {
		return get().getName();
	}

	public IJavaType getReturnType() {
		return getType();
	}

	public List<JavaModelInterfaceParameter> getParameterList() {
		return parameterList;
	}

}
