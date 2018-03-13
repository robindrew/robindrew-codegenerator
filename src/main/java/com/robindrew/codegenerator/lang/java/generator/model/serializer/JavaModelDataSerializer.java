package com.robindrew.codegenerator.lang.java.generator.model.serializer;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.serializer.ModelDataSerializer;

public class JavaModelDataSerializer {

	private final ModelDataSerializer serializer;
	private volatile IJavaType type;

	public JavaModelDataSerializer(ModelDataSerializer serializer) {
		this.serializer = serializer;
	}

	public ModelDataSerializer get() {
		return serializer;
	}

	public String getName() {
		return serializer.getName();
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

}
