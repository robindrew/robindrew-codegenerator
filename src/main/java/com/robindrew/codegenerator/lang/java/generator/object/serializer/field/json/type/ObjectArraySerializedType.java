package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type;

import com.robindrew.codegenerator.api.serializer.json.serializer.array.ObjectArraySerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectArraySerializedType extends JsonSerializedType {

	private final IJavaType componentType;
	private final JsonSerializedType component;

	public ObjectArraySerializedType(IJavaType type, JsonSerializedType component, IJavaType componentType) {
		super(type);
		this.component = component;
		this.componentType = componentType;
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
		return "new ObjectArraySerializer(" + component.getNewSerializer() + ", " + componentType.getSimpleName() + ".class)";
	}

}
