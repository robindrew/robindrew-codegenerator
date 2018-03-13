package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class SqlCodeLines extends JavaCodeLines {

	public static String questions(int size) {
		StringBuilder text = new StringBuilder();
		boolean comma = false;
		for (int i = 0; i < size; i++) {
			if (comma) {
				text.append(',');
			}
			comma = true;
			text.append('?');
		}
		return text.toString();
	}
}
