package com.robindrew.codegenerator.model.object.factory;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelObjectFactory extends ModelObject {

	@Attribute
	private String factory;
	@Attribute(required = false)
	private String scope = "LOCAL";

	public String getFactory() {
		return factory;
	}

	public String getScope() {
		return scope;
	}
}
