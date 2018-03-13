package com.robindrew.codegenerator.lang.java.generator.object.field;

import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;

public class SerialVersionUidField extends JavaField {

	public SerialVersionUidField(long value) {
		super("serialVersionUID", long.class);

		setStatic(true);
		setFinal(true);
		setAssignment(value + "L");
		setComment("The serialization version number");
	}

}
