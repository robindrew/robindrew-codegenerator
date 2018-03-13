package com.robindrew.codegenerator.model.object.comparator;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelComparatorField extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute(required = false)
	private boolean reverse = false;

	public String getName() {
		return name;
	}

	public boolean isReverse() {
		return reverse;
	}
}
