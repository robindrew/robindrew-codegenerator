package com.robindrew.codegenerator.model.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ModelReaderLookup implements IModelReaderLookup {

	private final Set<String> resources = new TreeSet<String>();

	public IModelReaderLookup add(String resource) {
		this.resources.add(resource);
		return this;
	}

	@Override
	public List<IModelReader> getReaderList() {
		List<IModelReader> list = new ArrayList<IModelReader>();
		for (String resource : resources) {
			list.add(new ModelReader(resource));
		}
		return list;
	}

}
