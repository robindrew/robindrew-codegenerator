package com.robindrew.codegenerator.util;

import java.io.InputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.robindrew.codegenerator.model.reader.ModelReadException;

public class SimpleResourceReader {

	public <T> T read(String resourceName, Class<T> type) {
		try (InputStream input = ClassLoader.getSystemResourceAsStream(resourceName)) {
			if (input == null) {
				throw new IllegalArgumentException("resource not found: '" + resourceName + "'");
			}
			Serializer serializer = new Persister();
			T instance = serializer.read(type, input);
			return instance;
		} catch (Exception e) {
			throw new ModelReadException(e);
		}
	}

}
