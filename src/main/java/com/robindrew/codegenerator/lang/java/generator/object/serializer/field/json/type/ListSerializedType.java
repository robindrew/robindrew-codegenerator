package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type;

import com.robindrew.codegenerator.api.serializer.json.serializer.collection.ListSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ListSerializedType extends JsonSerializedType {

	private final JsonSerializedType component;

	public ListSerializedType(IJavaType type, JsonSerializedType component) {
		super(type);
		this.component = component;
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(ListSerializer.class);
		component.addReferences(set);
	}

	@Override
	public String getNewSerializer() {
		String name = component.getType().getSimpleName();
		return "new ListSerializer<" + name + ">(" + component.getNewSerializer() + ")";
	}

}
