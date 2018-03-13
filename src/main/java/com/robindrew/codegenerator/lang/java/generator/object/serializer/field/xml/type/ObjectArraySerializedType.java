package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type;

import com.robindrew.codegenerator.api.serializer.xml.serializer.array.ObjectArraySerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectArraySerializedType extends XmlSerializedType {

	private final IJavaType componentType;
	private final XmlSerializedType component;

	public ObjectArraySerializedType(IJavaType type, XmlSerializedType component, IJavaType componentType) {
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
	public String getNewSerializer(String name, boolean reference) {
		return "new ObjectArraySerializer(" + component.getNewSerializer("element", false) + ", " + componentType.getSimpleName() + ".class)";
	}

}
