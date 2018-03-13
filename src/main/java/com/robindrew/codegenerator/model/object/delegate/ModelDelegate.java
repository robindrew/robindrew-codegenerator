package com.robindrew.codegenerator.model.object.delegate;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelDelegate extends ModelObject {

	@Attribute
	private String type;

	public String getType() {
		return type;
	}

}
