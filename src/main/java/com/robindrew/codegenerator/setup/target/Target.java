package com.robindrew.codegenerator.setup.target;

import org.simpleframework.xml.Attribute;

public class Target implements ITarget {

	@Attribute
	private String name;
	@Attribute
	private String directory;
	private ITargetWriter writer;

	public String getName() {
		return name;
	}

	public String getDirectory() {
		return directory;
	}

	public void setWriter(ITargetWriter writer) {
		this.writer = writer;
	}

	@Override
	public ITargetWriter getWriter() {
		return writer;
		// return new DirectoryTargetWriter(directory);
	}
}
