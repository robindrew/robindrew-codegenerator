package com.robindrew.codegenerator.model.object.builder;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelBuilder extends ModelObject {

	@Attribute
	private String bean;

	public String getBean() {
		return bean;
	}
}
