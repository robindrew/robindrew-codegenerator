package com.robindrew.codegenerator.lang.java.type.name;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaName implements IJavaName {

	public static String toLower(String name) {
		return new JavaName(name).toLower().get();
	}

	public static String toUpper(String name) {
		return new JavaName(name).toUpper().get();
	}

	public static String toGetter(String name, boolean isBoolean) {
		return (isBoolean ? "is" : "get") + new JavaName(name).toUpper().get() + "()";
	}

	private final String name;
	private final boolean immutable;

	public JavaName(String name) {
		this(name, false);
	}

	public JavaName(String name, boolean immutable) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		this.name = name;
		this.immutable = immutable;
	}

	@Override
	public String get() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof IJavaName) {
			IJavaName that = (IJavaName) object;
			return get().equals(that.get());
		}
		return false;
	}

	private IJavaName toCamel(boolean lower) {
		if (immutable) {
			return this;
		}

		String text = this.name;
		StringBuilder converted = new StringBuilder();

		// First letter
		char c = text.charAt(0);
		converted.append(lower ? toLowerCase(c) : toUpperCase(c));

		// Remaining letters
		boolean underscore = false;
		boolean upper = isUpperCase(c);
		for (int i = 1; i < text.length(); i++) {
			c = text.charAt(i);
			if (c == '_') {
				underscore = true;
				continue;
			}
			if (underscore) {
				underscore = false;
				upper = true;
				converted.append(toUpperCase(c));
				continue;
			}
			if (upper) {
				upper = isUpperCase(c);
				converted.append(toLowerCase(c));
				continue;
			}
			upper = isUpperCase(c);
			converted.append(c);
		}
		return new JavaName(converted.toString());
	}

	@Override
	public IJavaName toUpper() {
		return toCamel(false);
	}

	@Override
	public IJavaName toLower() {
		return toCamel(true);
	}

	@Override
	public IJavaName toConst() {
		if (immutable) {
			return this;
		}

		String text = this.name;
		StringBuilder converted = new StringBuilder();
		boolean lower = false;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (isUpperCase(c) || c == '_') {
				if (lower) {
					lower = false;
					converted.append('_');
				}
				converted.append(c);
			} else {
				lower = true;
				converted.append(toUpperCase(c));
			}
		}
		return new JavaName(converted.toString());
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.write(name);
	}

	@Override
	public List<IJavaName> split() {
		String[] names = name.split("\\.");
		List<IJavaName> list = new ArrayList<IJavaName>();
		for (String name : names) {
			list.add(new JavaName(name));
		}
		return list;
	}

}
