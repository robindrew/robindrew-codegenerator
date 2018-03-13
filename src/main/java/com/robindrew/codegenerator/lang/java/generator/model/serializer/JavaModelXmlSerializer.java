package com.robindrew.codegenerator.lang.java.generator.model.serializer;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.serializer.ModelXmlSerializer;

public class JavaModelXmlSerializer {

	private final ModelXmlSerializer serializer;
	private volatile IJavaType type;
	private volatile IJavaType returnType;

	public JavaModelXmlSerializer(ModelXmlSerializer serializer) {
		this.serializer = serializer;
	}

	public boolean isReturnType() {
		return serializer.isReturnType();
	}

	public ModelXmlSerializer get() {
		return serializer;
	}

	public String getName() {
		return serializer.getName();
	}

	public IJavaType getType() {
		return type;
	}

	public IJavaType getReturnType() {
		return returnType;
	}

	public boolean hasReturnType() {
		return returnType != null;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	public void setReturnType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.returnType = type;
	}

}
