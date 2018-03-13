package com.robindrew.codegenerator.lang.java.type;

import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public class JavaTypeObject extends AbstractJavaType {

	private final IClassType type;
	private final int generics;
	private final IJavaType component;
	private final int dimensions;

	public JavaTypeObject(String packageName, String simpleName, IClassType type) {
		this(packageName, simpleName, type, UNKNOWN_GENERICS_COUNT, null, 0);
	}

	public JavaTypeObject(String packageName, String simpleName, IClassType type, int generics) {
		this(packageName, simpleName, type, generics, null, 0);
	}

	public JavaTypeObject(String packageName, String simpleName, IClassType type, int generics, IJavaType component, int dimensions) {
		super(packageName, simpleName);
		if (type == null) {
			throw new NullPointerException("type");
		}
		if ((component != null) != (dimensions > 0)) {
			throw new IllegalArgumentException();
		}
		this.type = type;
		this.generics = generics;
		this.component = component;
		this.dimensions = dimensions;
	}

	@Override
	public IClassType getClassType() {
		return type;
	}

	@Override
	public int getGenericsCount() {
		return generics;
	}

	@Override
	public IJavaType getComponentType() {
		return component;
	}

	@Override
	public int getDimensions() {
		return dimensions;
	}

}
