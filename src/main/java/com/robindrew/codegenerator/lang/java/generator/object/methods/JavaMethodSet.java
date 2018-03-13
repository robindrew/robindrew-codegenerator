package com.robindrew.codegenerator.lang.java.generator.object.methods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.lookup.IJavaMethodLookup;
import com.robindrew.codegenerator.lang.java.generator.object.lookup.JavaMethodLookup;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class JavaMethodSet implements Iterable<JavaMethod> {

	private final Map<JavaMethod, JavaMethod> methodMap = new LinkedHashMap<JavaMethod, JavaMethod>();

	private final IJavaModelSet modelSet;
	private final List<JavaModelExtends> extendsList;

	public JavaMethodSet(IJavaModelSet modelSet, List<JavaModelExtends> extendsList) {
		if (modelSet == null) {
			throw new NullPointerException("modelSet");
		}
		if (extendsList == null) {
			throw new NullPointerException("extendsList");
		}
		this.modelSet = modelSet;
		this.extendsList = extendsList;
	}

	@Override
	public Iterator<JavaMethod> iterator() {
		return methodMap.values().iterator();
	}

	public void addInterfaceMethods() {
		for (JavaMethod method : getInterfaceMethodList()) {
			add(method, false);
		}
	}

	public List<JavaMethod> getInterfaceMethodList() {
		List<JavaMethod> list = new ArrayList<JavaMethod>();
		IJavaMethodLookup lookup = new JavaMethodLookup(modelSet);
		for (JavaMethod method : lookup.getInterfaceMethodSet(extendsList)) {
			if (!methodMap.containsKey(method)) {
				list.add(method);
			}
		}
		return list;
	}

	public void add(JavaMethod method) {
		// Replace existing method ...
		add(method, true);
	}

	public void add(JavaMethod method, boolean replaceExisting) {
		if (!replaceExisting && methodMap.containsKey(method)) {
			return;
		}
		methodMap.put(method, method);
	}

}
