package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectSerializedType extends XmlSerializedType {

	private final IJavaType serializedType;

	public ObjectSerializedType(IJavaType type, IJavaType serializedType) {
		super(type);
		this.serializedType = serializedType;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectSerializedType(IJavaType type, Class<?> serializedType) {
		this(type, new JavaTypeClass(serializedType));
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(serializedType);
	}

	@Override
	public String getNewSerializer(String name, boolean reference) {
		String quotes = reference ? "" : "\"";
		return "new " + serializedType.getSimpleName() + "(" + quotes + name + quotes + ")";
	}

}
