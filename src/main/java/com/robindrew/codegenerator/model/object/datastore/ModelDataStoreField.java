package com.robindrew.codegenerator.model.object.datastore;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelDataStoreField extends SimpleModelElement {

	@Attribute
	private String name;

	public String getName() {
		return name;
	}
}
