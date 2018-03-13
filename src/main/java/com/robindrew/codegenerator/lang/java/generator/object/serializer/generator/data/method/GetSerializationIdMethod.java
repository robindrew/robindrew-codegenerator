package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.data.method;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetSerializationIdMethod extends JavaMethod {

	public static final String CONSTANT_NAME = "SERIALIZATION_ID";

	public GetSerializationIdMethod() {
		super("getSerializationId", int.class);
		getComment().line("Returns the serialization id.");
		getComment().line("@return the serialization id.");
		setContents(new JavaCodeLines("return " + CONSTANT_NAME + ";"));
	}

}
