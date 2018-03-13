package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type;

import com.robindrew.codegenerator.api.serializer.xml.serializer.collection.MapSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class MapSerializedType extends XmlSerializedType {

	private final XmlSerializedType key;
	private final XmlSerializedType value;

	public MapSerializedType(IJavaType type, XmlSerializedType key, XmlSerializedType value) {
		super(type);
		this.key = key;
		this.value = value;
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(MapSerializer.class);
		key.addReferences(set);
		value.addReferences(set);
	}

	@Override
	public String getNewSerializer(String name, boolean reference) {
		String keyName = key.getType().getSimpleName();
		String valueName = value.getType().getSimpleName();
		String quotes = reference ? "" : "\"";
		return "new MapSerializer<" + keyName + ", " + valueName + ">(" + quotes + name + quotes + ", " + key.getNewSerializer("key", false) + ", " + value.getNewSerializer("value", false) + ")";
	}
}
