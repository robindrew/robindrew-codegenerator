package com.robindrew.codegenerator.lang.java.generator.model.bean;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.bean.ModelBeanMethod;

public class JavaModelBeanMethod extends JavaModelTyped<ModelBeanMethod> {

	private final List<JavaModelBeanParameter> parameterList;

	public JavaModelBeanMethod(ModelBeanMethod method, List<JavaModelBeanParameter> parameterList) {
		super(method);
		this.parameterList = parameterList;
	}

	public String getName() {
		return get().getName();
	}

	public IJavaType getReturnType() {
		return getType();
	}

	public List<JavaModelBeanParameter> getParameterList() {
		return parameterList;
	}

	public String getReturnValue() {
		return get().getReturnValue();
	}

}
