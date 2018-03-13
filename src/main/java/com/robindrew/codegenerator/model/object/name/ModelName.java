package com.robindrew.codegenerator.model.object.name;

import org.simpleframework.xml.Attribute;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelName extends SimpleModelElement {

	public static ModelName prefix(String prefix) {
		return new ModelName(prefix, "");
	}

	public static ModelName postfix(String postfix) {
		return new ModelName("", postfix);
	}

	@Attribute(required = false)
	private String prefix = "";
	@Attribute(required = false)
	private String postfix = "";

	public ModelName() {
	}

	public ModelName(String prefix, String postfix) {
		if (prefix == null) {
			throw new NullPointerException("prefix");
		}
		if (postfix == null) {
			throw new NullPointerException("postfix");
		}
		this.prefix = prefix;
		this.postfix = postfix;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getPostfix() {
		return postfix;
	}
}
