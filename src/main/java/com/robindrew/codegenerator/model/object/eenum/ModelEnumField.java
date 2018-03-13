package com.robindrew.codegenerator.model.object.eenum;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelEnumField extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute
	private String type;
	@Attribute
	private String value;
	@Attribute(required = false)
	private boolean quotes = true;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getValue() {
		// Special case for strings
		// Can be disabled by setting quotes="false"
		if (type.equals("String") && quotes) {
			return "\"" + value + "\"";
		}
		return value;
	}

}
