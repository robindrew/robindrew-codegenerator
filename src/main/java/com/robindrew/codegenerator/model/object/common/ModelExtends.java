package com.robindrew.codegenerator.model.object.common;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelExtends extends SimpleModelElement {

	@Attribute
	private String type;

	public String getType() {
		return type;
	}

}
