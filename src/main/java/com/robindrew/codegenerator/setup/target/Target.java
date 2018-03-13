package com.robindrew.codegenerator.setup.target;

import org.simpleframework.xml.Attribute;

public class Target implements ITarget {

	@Attribute
	private String name;
	@Attribute
	private String directory;

	public String getName() {
		return name;
	}

	public String getDirectory() {
		return directory;
	}

	@Override
	public ITargetWriter getWriter() {
		return new DirectoryTargetWriter(directory);
	}
}
