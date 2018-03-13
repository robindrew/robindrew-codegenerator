package com.robindrew.codegenerator.lang.java.generator.object.serializer.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.ObjectDataField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.PrimitiveDataField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.ObjectJsonField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.PrimitiveJsonField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.ObjectXmlField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.PrimitiveXmlField;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class SerializedFieldList implements Iterable<SerializedField> {

	private final List<SerializedField> list = new ArrayList<SerializedField>();
	private final SerializedFieldType type;
	private final IJavaModelSet models;

	public SerializedFieldList(JavaModelBean bean, IJavaModelSet models, IJavaTypeResolver resolver, SerializedFieldType type) {
		this.type = type;
		this.models = models;
		for (JavaModelBeanField field : bean.getFieldList()) {
			list.add(getField(field));
		}
	}

	private SerializedField getField(JavaModelBeanField field) {
		switch (type) {
			case DATA:
				return getDataField(field);
			case XML:
				return getXmlField(field);
			case JSON:
				return getJsonField(field);
			default:
				throw new IllegalArgumentException("type not supported: " + type);
		}
	}

	private SerializedField getJsonField(JavaModelBeanField field) {
		if (field.getType().isPrimitive()) {
			return new PrimitiveJsonField(field, models);
		}
		return new ObjectJsonField(field, models);
	}

	private SerializedField getXmlField(JavaModelBeanField field) {
		if (field.getType().isPrimitive()) {
			return new PrimitiveXmlField(field, models);
		}
		return new ObjectXmlField(field, models);
	}

	private SerializedField getDataField(JavaModelBeanField field) {
		if (field.getType().isPrimitive()) {
			return new PrimitiveDataField(field, models);
		}
		return new ObjectDataField(field, models);
	}

	@Override
	public Iterator<SerializedField> iterator() {
		return Collections.unmodifiableList(list).iterator();
	}

	public void addReferences(IJavaTypeSet references) {
		for (SerializedField field : this) {
			field.addReferences(references);
		}
	}

	public int size() {
		return list.size();
	}
}
