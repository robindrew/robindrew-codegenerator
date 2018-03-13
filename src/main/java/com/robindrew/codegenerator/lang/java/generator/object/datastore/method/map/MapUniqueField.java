package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.HashSet;
import java.util.Set;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;

public class MapUniqueField extends JavaField {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static IJavaType getSetType(IJavaType type) {
		type = type.toObjectType();
		IJavaType setType = new JavaTypeClass(Set.class, 1);
		return new JavaTypeWithGenerics(setType, type);
	}

	public MapUniqueField(JavaModelBeanField field) {
		super(field.getName() + "Set", getSetType(field.getType()));
		getReferences().add(HashSet.class);
		setAssignment("new HashSet<" + field.getType().toObjectType() + ">()");
		setFinal(true);
	}

}
