package com.robindrew.codegenerator.lang.java.generator.object.datastore;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.model.object.bean.ModelBean;
import com.robindrew.codegenerator.model.object.bean.ModelBeanField;

public class JavaDataStoreElement {

	private final IJavaType elementType;
	private final IJavaType keyType;
	private final IJavaContext context;
	private final ModelBean elementBean;

	public JavaDataStoreElement(IJavaType elementType, ModelBean elementBean, IJavaType keyType, IJavaContext context) {
		if (elementType == null) {
			throw new NullPointerException("elementType");
		}
		if (keyType == null) {
			throw new NullPointerException("keyType");
		}
		if (context == null) {
			throw new NullPointerException("context");
		}
		if (elementBean == null) {
			throw new NullPointerException("elementBean");
		}
		this.elementType = elementType;
		this.keyType = keyType;
		this.context = context;
		this.elementBean = elementBean;
	}

	public IJavaType getElementType() {
		return elementType;
	}

	public IJavaType getKeyType() {
		return keyType;
	}

	public ModelBean getElementBean() {
		return elementBean;
	}

	public ModelBeanField getElementField(String name) {
		for (ModelBeanField field : elementBean.getFieldList()) {
			if (field.getName().equals(name)) {
				return field;
			}
		}
		throw new IllegalArgumentException("field not found: '" + name + "'");
	}

	public String getName() {
		return elementType.getSimpleName();
	}

	public IJavaType asList() {
		IJavaType listType = context.getResolver().getType(List.class);
		return new JavaTypeWithGenerics(listType, elementType);
	}

}
