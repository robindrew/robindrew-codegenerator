package com.robindrew.codegenerator.lang.java.generator.object.lookup;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public interface IJavaMethodLookup {

	JavaMethod getMethod(Method method);

	Set<JavaMethod> getMethodSet(Class<?> type);

	Set<JavaMethod> getInterfaceMethodSet(IJavaType type);

	Set<JavaMethod> getClassMethodSet(IJavaType type);

	Set<JavaMethod> getInterfaceMethodSet(List<JavaModelExtends> extendsList);

	Set<JavaMethod> getClassMethodSet(List<JavaModelExtends> extendsList);

	Set<JavaMethod> getMethodSet(JavaModelInterface iinterface);

}
