package com.robindrew.codegenerator.model.reader;

import java.io.InputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.robindrew.codegenerator.model.IModel;
import com.robindrew.codegenerator.model.Model;

public class ModelReader implements IModelReader {

	private final String resourceName;

	public ModelReader(String resourceName) {
		if (resourceName.isEmpty()) {
			throw new IllegalArgumentException("resourceName is empty");
		}
		this.resourceName = resourceName;
	}

	@Override
	public String toString() {
		return resourceName;
	}

	@Override
	public IModel readModel() {
		try (InputStream input = ClassLoader.getSystemResourceAsStream(resourceName)) {
			if (input == null) {
				throw new IllegalArgumentException("resource not found: '" + resourceName + "'");
			}
			Serializer serializer = new Persister();
			Model model = serializer.read(Model.class, input);
			return model;
		} catch (Exception e) {
			throw new ModelReadException("Failed to read model from resource: '" + resourceName + "'", e);
		}
	}

}
