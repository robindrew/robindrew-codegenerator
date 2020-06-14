package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.common.io.data.serializer.lang.EnumSerializer;

public class EnumSerializedType extends DataSerializedType {

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
		return "new EnumSerializer<" + name + ">(" + name + ".class, " + isNullable() + ")";
	}

}
