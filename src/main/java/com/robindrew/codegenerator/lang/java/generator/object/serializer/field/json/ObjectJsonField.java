package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type.JsonSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type.JsonSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class ObjectJsonField extends SerializedField {

	public ObjectJsonField(JavaModelBeanField field, IJavaModelSet models) {
		super(field, models);
	}

	private JsonSerializedType getDataType() {
		JsonSerializedTypeFactory factory = new JsonSerializedTypeFactory(getModels());
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
