package com.robindrew.codegenerator.lang.java.generator.object.serializer.field;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public abstract class SerializedField {

	private final JavaModelBeanField field;
	private final IJavaModelSet models;

	protected SerializedField(JavaModelBeanField field, IJavaModelSet models) {
		this.field = field;
		this.models = models;
	}

	public JavaModelBeanField getField() {
		return field;
	}

	public IJavaModelSet getModels() {
		return models;
	}

	public IJavaName getName() {
		return new JavaName(field.getName());
	}

	public IJavaType getType() {
		return field.getType();
	}

	public void addReferences(IJavaTypeSet set, SerializedFieldType type) {
	}

	protected String getPrimitiveName() {
		String name = getType().getSimpleName();
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public boolean isType(Class<?> compare) {
		return getType().isType(compare);
	}

	public abstract String getReadMethod();

	public abstract String getWriteMethod(String getter, boolean comma);

	public abstract void addReferences(IJavaTypeSet set);

}
