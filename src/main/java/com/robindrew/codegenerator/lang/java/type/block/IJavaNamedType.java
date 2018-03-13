package com.robindrew.codegenerator.lang.java.type.block;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWritable;

/**
 * Any object that represents a name and a type, such as a field, parameter, etc.
 */
public interface IJavaNamedType extends IJavaWritable {

	IJavaType getType();

	IJavaName getName();

	boolean isFinal();

}
