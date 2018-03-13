package com.robindrew.codegenerator.lang.java.generator.object.lookup;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterfaceMethod;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterfaceParameter;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class JavaMethodLookup implements IJavaMethodLookup {

	private final IJavaModelSet modelSet;

	public JavaMethodLookup(IJavaModelSet modelSet) {
		if (modelSet == null) {
			throw new NullPointerException("modelSet");
		}
		this.modelSet = modelSet;
	}

	@Override
	public JavaMethod getMethod(Method method) {
		JavaMethod value = new JavaMethod(method.getName(), method.getReturnType());
		addParameters(value.getParameters(), method.getParameterTypes());
		return value.setUnsupportedOperationContents();
	}

	private void addParameters(IJavaNamedTypeSet parameters, Class<?>[] types) {
		// In java, method parameter names are lost (sigh)
		int index = 1;
		for (Class<?> type : types) {
			parameters.add(new JavaNamedType("param" + index, type));
			index++;
		}
	}

	@Override
	public Set<JavaMethod> getMethodSet(Class<?> type) {
		Set<JavaMethod> set = new LinkedHashSet<JavaMethod>();
		for (Method method : type.getDeclaredMethods()) {
			set.add(getMethod(method));
		}
		for (Class<?> iinterface : type.getInterfaces()) {
			set.addAll(getMethodSet(iinterface));
		}
		return set;
	}

	@Override
	public Set<JavaMethod> getInterfaceMethodSet(IJavaType type) {
		JavaModelInterface iinterface = modelSet.getInterface(type, true);
		if (iinterface != null) {
			return getMethodSet(iinterface);
		}
		if (type.isInterface()) {
			return getMethodSet(type.getType());
		}
		return Collections.emptySet();
	}

	@Override
	public Set<JavaMethod> getClassMethodSet(IJavaType type) {
		if (!type.isInterface()) {
			return getMethodSet(type.getType());
		}
		return Collections.emptySet();
	}

	@Override
	public Set<JavaMethod> getInterfaceMethodSet(List<JavaModelExtends> extendsList) {

		// Find the unique set of ALL interface methods
		Set<JavaMethod> set = new LinkedHashSet<JavaMethod>();
		for (JavaModelExtends eextends : extendsList) {
			IJavaType type = eextends.getType();
			set.addAll(getInterfaceMethodSet(type));
		}

		// Remove any interface methods ALREADY IMPLEMENTED by a subclass
		set.removeAll(getClassMethodSet(extendsList));
		return set;
	}

	@Override
	public Set<JavaMethod> getClassMethodSet(List<JavaModelExtends> extendsList) {
		Set<JavaMethod> set = new LinkedHashSet<JavaMethod>();
		for (JavaModelExtends eextends : extendsList) {
			IJavaType type = eextends.getType();
			set.addAll(getClassMethodSet(type));
		}
		return set;
	}

	@Override
	public Set<JavaMethod> getMethodSet(JavaModelInterface iinterface) {
		Set<JavaMethod> set = new LinkedHashSet<JavaMethod>();
		for (JavaModelInterfaceMethod method : iinterface.getMethodList()) {
			set.add(getMethod(method));
		}
		set.addAll(getInterfaceMethodSet(iinterface.getExtendsList()));
		return set;
	}

	private JavaMethod getMethod(JavaModelInterfaceMethod method) {
		JavaMethod value = new JavaMethod(method.getName(), method.getReturnType());
		for (JavaModelInterfaceParameter parameter : method.getParameterList()) {
			value.getParameters().add(parameter.toNamedType());
		}
		return value.setUnsupportedOperationContents();
	}

}
