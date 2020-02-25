package com.robindrew.codegenerator.lang.java.generator.model.executor;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.executor.ModelExecutorSet;

public class JavaModelExecutorSet {

	private final ModelExecutorSet set;
	private final List<JavaModelExtends> extendsList;
	private volatile IJavaType type;

	public JavaModelExecutorSet(ModelExecutorSet set, List<JavaModelExtends> extendsList) {
		if (set == null) {
			throw new NullPointerException("eenum");
		}
		if (extendsList == null) {
			throw new NullPointerException("extendsList");
		}
		this.set = set;
		this.extendsList = extendsList;
	}

	public ModelExecutorSet get() {
		return set;
	}

	public List<JavaModelExtends> getExtendsList() {
		return extendsList;
	}

	public IJavaType getType() {
		return type;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	public String getName() {
		return get().getName();
	}

}
