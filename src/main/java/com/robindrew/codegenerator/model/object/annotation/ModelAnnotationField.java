package com.robindrew.codegenerator.model.object.annotation;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelAnnotationField extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute
	private String type;
	@Attribute(name = "default", required = false)
	private String defaultValue = null;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public boolean hasDefaultValue() {
		return defaultValue != null;
	}

}
