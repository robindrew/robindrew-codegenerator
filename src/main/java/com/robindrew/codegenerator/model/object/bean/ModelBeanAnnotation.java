package com.robindrew.codegenerator.model.object.bean;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelBeanAnnotation extends SimpleModelElement {

	@Attribute
	private String type;
	@Attribute
	private boolean field = true;
	@Attribute
	private boolean getter = true;
	@Attribute
	private boolean setter = true;

	public String getType() {
		return type;
	}

	public boolean isField() {
		return field;
	}

	public boolean isGetter() {
		return getter;
	}

	public boolean isSetter() {
		return setter;
	}

}
