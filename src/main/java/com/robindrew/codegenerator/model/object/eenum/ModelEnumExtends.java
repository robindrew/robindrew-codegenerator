package com.robindrew.codegenerator.model.object.eenum;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelEnumExtends extends SimpleModelElement {

	@Attribute
	private String type;

	public String getType() {
		return type;
	}

}
