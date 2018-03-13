package com.robindrew.codegenerator.lang.java.type.name;

import java.util.List;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWritable;

public interface IJavaName extends IJavaWritable {

	String get();

	IJavaName toUpper();

	IJavaName toLower();

	IJavaName toConst();

	List<IJavaName> split();

}
