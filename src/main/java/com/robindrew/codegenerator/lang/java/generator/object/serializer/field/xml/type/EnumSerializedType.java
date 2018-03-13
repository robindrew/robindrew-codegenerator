package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type;

import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.EnumSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class EnumSerializedType extends XmlSerializedType {

	public EnumSerializedType(IJavaType type) {
		super(type);
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(EnumSerializer.class);
	}

	@Override
	public String getNewSerializer(String name, boolean reference) {
		String type = getType().getSimpleName();
		String quotes = reference ? "" : "\"";
		return "new EnumSerializer<" + type + ">(" + type + ".class, " + quotes + name + quotes + ")";
	}
}
