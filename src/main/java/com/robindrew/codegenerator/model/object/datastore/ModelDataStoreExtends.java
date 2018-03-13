package com.robindrew.codegenerator.model.object.datastore;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelDataStoreExtends extends SimpleModelElement {

	@Attribute
	private String type;

	public String getType() {
		return type;
	}

}
