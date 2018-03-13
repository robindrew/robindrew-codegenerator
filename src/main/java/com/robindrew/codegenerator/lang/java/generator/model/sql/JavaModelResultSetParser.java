package com.robindrew.codegenerator.lang.java.generator.model.sql;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.sql.ModelResultSetParser;

public class JavaModelResultSetParser {

	private final ModelResultSetParser serializer;
	private volatile IJavaType type;

	public JavaModelResultSetParser(ModelResultSetParser serializer) {
		this.serializer = serializer;
	}

	public ModelResultSetParser get() {
		return serializer;
	}

	public String getName() {
		return serializer.getName();
	}

	public IJavaType getType() {
		return type;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

}
