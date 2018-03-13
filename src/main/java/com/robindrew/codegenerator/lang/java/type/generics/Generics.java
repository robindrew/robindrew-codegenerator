package com.robindrew.codegenerator.lang.java.type.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics {

	private final String name;
	private final List<Generics> genericsList = new ArrayList<Generics>();

	public Generics(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void add(Generics generics) {
		if (generics == null) {
			throw new NullPointerException("generics");
		}
		genericsList.add(generics);
	}

	public List<Generics> getGenericsList() {
		return genericsList;
	}

	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append(name);
		if (!genericsList.isEmpty()) {
			text.append('<');
			boolean comma = false;
			for (Generics generics : genericsList) {
				if (comma) {
					text.append(',');
				}
				comma = true;
				text.append(generics);
			}
			text.append('>');
		}
		return text.toString();
	}

	public boolean isEmpty() {
		return genericsList.isEmpty();
	}

	public int size() {
		return genericsList.size();
	}

}
