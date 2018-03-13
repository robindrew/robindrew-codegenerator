package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type.DataSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type.DataSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectDataField extends SerializedField {

	public ObjectDataField(JavaModelBeanField field, IJavaModelSet models) {
		super(field, models);
	}

	private DataSerializedType getDataType() {
		DataSerializedTypeFactory factory = new DataSerializedTypeFactory(getModels());
		return factory.getType(getType());
	}

	@Override
	public String getReadMethod() {
		return "readObject(" + getDataType().getNewSerializer() + ");";
	}

	@Override
	public String getWriteMethod(String getter, boolean comma) {
		return "writeObject(" + getter + ", " + getDataType().getNewSerializer() + ");";
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		getDataType().addReferences(set);
	}

}
