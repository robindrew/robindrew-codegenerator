package com.robindrew.codegenerator.model.object;

import java.io.StringWriter;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.robindrew.common.util.Java;

public class SimpleModelElement {

	@Override
	public String toString() {
		try {
			Serializer persister = new Persister();
			StringWriter writer = new StringWriter();
			persister.write(this, writer);
			return writer.toString();
		} catch (Exception e) {
			throw Java.propagate(e);
		}
	}

}
