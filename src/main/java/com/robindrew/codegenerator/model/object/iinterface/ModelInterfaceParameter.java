package com.robindrew.codegenerator.model.object.iinterface;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelInterfaceParameter extends SimpleModelElement {

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
