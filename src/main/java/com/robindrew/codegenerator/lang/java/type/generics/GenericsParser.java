package com.robindrew.codegenerator.lang.java.type.generics;

public class GenericsParser {

	public Generics parseGenerics(String text) {
		text = text.trim();
		if (text.isEmpty()) {
			throw new IllegalArgumentException("Badly formatted generics: '" + text + "'");
		}

		// Find the starting: '<'
		int start = text.indexOf('<');
		if (start == -1) {
			return new Generics(text);
		}

		// Find matching ending: '>'
		int end = getEndIndex(text, start);
		if (end != text.length() - 1) {
			throw new IllegalArgumentException("Badly formatted generics: '" + text + "'");
		}

		String name = text.substring(0, start).trim();
		Generics generics = new Generics(name);
		parseGenerics(generics, text.substring(start + 1, end));
		return generics;
	}

	private void parseGenerics(Generics generics, String text) {
		text = text.trim();
		if (text.isEmpty()) {
			throw new IllegalArgumentException("Badly formatted generics: '" + text + "'");
		}

		int start = 0;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			// Skip generic block ...
			if (c == '<') {
				i = getEndIndex(text, i);
				continue;
			}

			// Comma? lets parse then
			if (c == ',') {
				Generics child = parseGenerics(text.substring(start, i));
				generics.add(child);
				start = i + 1;
				continue;
			}
		}

		// Final one ...
		Generics child = parseGenerics(text.substring(start));
		generics.add(child);
	}

	private int getEndIndex(String text, int start) {
		// We have found the starting '<', now lets find the matching ending '>'
		int depth = 1;
		for (int i = start + 1; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == '<') {
				depth++;
			}
			if (c == '>') {
				depth--;
			}
			if (depth == 0) {
				return i;
			}
		}
		throw new IllegalArgumentException("Badly formatted generics: '" + text + "'");
	}

}
