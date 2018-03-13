package com.robindrew.codegenerator.setup.source;

import org.simpleframework.xml.Attribute;

public class Source implements ISource {

	@Attribute
	private String name;
	@Attribute
	private String type;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

}
