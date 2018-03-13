package com.robindrew.codegenerator.model.object.bean;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelBeanConstructor extends SimpleModelElement {

	@Attribute
	private String type;

	public String getType() {
		return type;
	}

}
