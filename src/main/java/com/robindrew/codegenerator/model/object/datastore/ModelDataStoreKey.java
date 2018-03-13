package com.robindrew.codegenerator.model.object.datastore;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelDataStoreKey extends SimpleModelElement {

	@Attribute
	private String type;
	@Attribute
	private boolean unique;

	public String getType() {
		return type;
	}

	public boolean isUnique() {
		return unique;
	}

}
