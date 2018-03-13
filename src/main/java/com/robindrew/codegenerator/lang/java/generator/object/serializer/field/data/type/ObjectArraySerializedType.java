package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type;

import com.robindrew.codegenerator.api.serializer.data.serializer.array.ObjectArraySerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectArraySerializedType extends DataSerializedType {

	private final IJavaType componentType;
	private final DataSerializedType component;
	private boolean nullableComponents = false;

	public ObjectArraySerializedType(IJavaType type, DataSerializedType component, IJavaType componentType) {
		super(type);
		this.component = component;
		this.componentType = componentType;
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
		set.add(ObjectArraySerializer.class);
		set.add(componentType);
		component.addReferences(set);
	}

	@Override
	public String getNewSerializer() {
		component.setNullable(isNullableComponents());
		return "new ObjectArraySerializer(" + isNullable() + ", " + component.getNewSerializer() + ", " + componentType.getSimpleName() + ".class)";
	}

}
