package com.robindrew.codegenerator.model.object.alias;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelAlias extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute
	private String type;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
