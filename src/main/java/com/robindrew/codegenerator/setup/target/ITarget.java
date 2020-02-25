package com.robindrew.codegenerator.setup.target;

public interface ITarget {

	String getName();

	String getDirectory();

	ITargetWriter getWriter();

	void setWriter(ITargetWriter writer);

}
