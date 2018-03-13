package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public abstract class DataSerializedType {

	private final IJavaType type;
	private boolean nullable = false;

	public DataSerializedType(IJavaType type) {
		this.type = type;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public IJavaType getType() {
		return type;
	}

	public abstract void addReferences(IJavaTypeSet set);

	public abstract String getNewSerializer();

}
