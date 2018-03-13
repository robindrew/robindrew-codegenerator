package com.robindrew.codegenerator.lang.java.generator.object.factory;

public enum FactoryType {

	/** Bean. */
	BEAN("IBean"),
	/** Executable Bean. */
	EXECUTABLE_BEAN("IExecutableBean<?>"),
	/** Data Serializer. */
	DATA_SERIALIZER("IDataSerializer<?>"),
	/** Data Serializer. (for executable bean return types) */
	DATA_SERIALIZER_RETURN_TYPE("IDataSerializer<?>"),
	/** XML Serializer. */
	XML_SERIALIZER("IXmlSerializer<?>"),
	/** XML Serializer. (for executable bean return types) */
	XML_SERIALIZER_RETURN_TYPE("IXmlSerializer<?>"),
	/** JSON Serializer. */
	JSON_SERIALIZER("IJsonSerializer<?>"),
	/** JSON Serializer (for executable bean return types). */
	JSON_SERIALIZER_RETURN_TYPE("IJsonSerializer<?>"),
	/** Servlet Request Parser. */
	SERVLET_REQUEST_PARSER("IServletRequestParser<?>");

	private final String type;

	private FactoryType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
