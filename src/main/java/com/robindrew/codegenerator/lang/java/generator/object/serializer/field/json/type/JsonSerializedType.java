package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public abstract class JsonSerializedType {

	private final IJavaType type;

	public JsonSerializedType(IJavaType type) {
		this.type = type;
	}

	public IJavaType getType() {
		return type;
	}

	public abstract void addReferences(IJavaTypeSet set);

	public abstract String getNewSerializer();

}
