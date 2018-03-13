package com.robindrew.codegenerator.model.object.adapter;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelAdapterField extends SimpleModelElement {

	@Attribute
	private String from;
	@Attribute(required = false)
	private String to;

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to != null ? to : from;
	}
}
