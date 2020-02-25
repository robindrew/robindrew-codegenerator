package com.robindrew.codegenerator.lang.java.generator.model.iinterface;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterface;

public class JavaModelInterface {

	private final ModelInterface iinterface;
	private final List<JavaModelExtends> extendsList;
	private final List<JavaModelInterfaceMethod> methodList;
	private volatile IJavaType type;

	public JavaModelInterface(ModelInterface iinterface, List<JavaModelExtends> extendsList, List<JavaModelInterfaceMethod> methodList) {
		this.iinterface = iinterface;
		this.extendsList = extendsList;
		this.methodList = methodList;
	}

	public ModelInterface get() {
		return iinterface;
	}

	public String getName() {
		return get().getName();
	}

	public List<JavaModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<JavaModelInterfaceMethod> getMethodList() {
		return methodList;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	public IJavaType getType() {
		if (type == null) {
			throw new NullPointerException("type");
		}
		return type;
	}

}
