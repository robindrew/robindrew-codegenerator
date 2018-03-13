package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type;

import com.robindrew.codegenerator.api.serializer.xml.serializer.collection.ListSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ListSerializedType extends XmlSerializedType {

	private final XmlSerializedType component;

	public ListSerializedType(IJavaType type, XmlSerializedType component) {
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
	public String getNewSerializer(String name, boolean reference) {
		String element = component.getType().getSimpleName();
		String quotes = reference ? "" : "\"";
		return "new ListSerializer<" + element + ">(" + quotes + name + quotes + ", " + component.getNewSerializer("element", false) + ")";
	}

}
