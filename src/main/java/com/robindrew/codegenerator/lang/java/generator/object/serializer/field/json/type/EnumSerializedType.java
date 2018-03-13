package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type;

import com.robindrew.codegenerator.api.serializer.json.serializer.lang.EnumSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class EnumSerializedType extends JsonSerializedType {

	public EnumSerializedType(IJavaType type) {
		super(type);
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(EnumSerializer.class);
	}

	@Override
	public String getNewSerializer() {
		String name = getType().getSimpleName();
		return "new EnumSerializer<" + name + ">(" + name + ".class)";
	}

}
