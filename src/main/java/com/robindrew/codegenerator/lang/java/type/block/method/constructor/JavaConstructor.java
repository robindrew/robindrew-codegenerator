package com.robindrew.codegenerator.lang.java.type.block.method.constructor;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class JavaConstructor extends JavaMethod {

	public JavaConstructor(IJavaName name) {
		super(name);
		setConstructor(true);
	}

	public JavaConstructor(String name) {
		this(new JavaName(name));
	}

	public JavaConstructor(IJavaType type) {
		this(new JavaName(type.getSimpleName()));
	}

}
