package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.common.io.data.serializer.collection.SetSerializer;

public class SetSerializedType extends DataSerializedType {

	private final DataSerializedType component;
	private boolean nullableComponents = false;

	public SetSerializedType(IJavaType type, DataSerializedType component) {
		super(type);
		this.component = component;
	}

	public void setNullableComponents(boolean nullable) {
		this.nullableComponents = nullable;
	}

	public boolean isNullableComponents() {
		return nullableComponents;
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(SetSerializer.class);
		component.addReferences(set);
	}

	@Override
	public String getNewSerializer() {
		String name = component.getType().getSimpleName();
		component.setNullable(isNullableComponents());
		return "new SetSerializer<" + name + ">(" + component.getNewSerializer() + ", " + isNullable() + ")";
	}

}
