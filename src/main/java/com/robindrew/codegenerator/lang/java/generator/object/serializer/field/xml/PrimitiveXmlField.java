package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedField;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class PrimitiveXmlField extends SerializedField {

	public PrimitiveXmlField(JavaModelBeanField field, IJavaModelSet models) {
		super(field, models);
	}

	public String get() {
		String name = getType().getSimpleName();
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return name;
	}

	@Override
	public String getReadMethod() {
		return "read" + get() + "(\"" + getName() + "\");";
	}

	@Override
	public String getWriteMethod(String getter, boolean comma) {
		return "write" + get() + "(\"" + getName() + "\", " + getter + ");";
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
	}

}
