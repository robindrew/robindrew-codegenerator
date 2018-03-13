package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type;

import com.robindrew.codegenerator.api.serializer.xml.serializer.collection.SetSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class SetSerializedType extends XmlSerializedType {

	private final XmlSerializedType component;

	public SetSerializedType(IJavaType type, XmlSerializedType component) {
		super(type);
		this.component = component;
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(SetSerializer.class);
		component.addReferences(set);
	}

	@Override
	public String getNewSerializer(String name, boolean reference) {
		String element = component.getType().getSimpleName();
		String quotes = reference ? "" : "\"";
		return "new SetSerializer<" + element + ">(" + quotes + name + quotes + ", " + component.getNewSerializer("element", false) + ")";
	}

}
