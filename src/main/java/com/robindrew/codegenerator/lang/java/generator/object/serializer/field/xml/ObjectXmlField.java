package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type.XmlSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type.XmlSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectXmlField extends SerializedField {

	public ObjectXmlField(JavaModelBeanField field, IJavaModelSet models) {
		super(field, models);
	}

	private XmlSerializedType getXmlType() {
		XmlSerializedTypeFactory factory = new XmlSerializedTypeFactory(getModels());
		return factory.getType(getType());
	}

	@Override
	public String getReadMethod() {
		return "readObject(" + getXmlType().getNewSerializer(getName().get(), false) + ");";
	}

	@Override
	public String getWriteMethod(String getter, boolean comma) {
		return "writeObject(" + getter + ", " + getXmlType().getNewSerializer(getName().get(), false) + ");";
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		getXmlType().addReferences(set);
	}

}
