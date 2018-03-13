package com.robindrew.codegenerator.model.object.validator;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelValidator extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute
	private String type;
	@Attribute(required = false)
	private boolean notNull = true;
	@Attribute(required = false)
	private boolean notEmpty = false;
	@Attribute(required = false)
	private String min = null;
	@Attribute(required = false)
	private String max = null;
	@Attribute(required = false)
	private String pattern = null;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public String getMin() {
		return min;
	}

	public String getMax() {
		return max;
	}

	public String getPattern() {
		return pattern;
	}

	public boolean isNotEmpty() {
		return notEmpty;
	}

}
