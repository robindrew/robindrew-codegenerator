package com.robindrew.codegenerator.model.object.factory;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelFactory extends ModelObject {

	@Attribute
	private String type;

	public String getType() {
		return type;
	}
}
