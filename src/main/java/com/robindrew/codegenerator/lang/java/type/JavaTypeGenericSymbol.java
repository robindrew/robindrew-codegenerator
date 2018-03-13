package com.robindrew.codegenerator.lang.java.type;

import com.robindrew.codegenerator.lang.java.type.classtype.ClassType;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

/**
 * A Generic type (for example Map has the generic types "K" and "V")
 */
public class JavaTypeGenericSymbol extends AbstractJavaType {

	public JavaTypeGenericSymbol(String simpleName) {
		super("", simpleName);
		if (simpleName.length() > 1) {
			throw new IllegalArgumentException("Generic types can only be a single character");
		}
	}

	@Override
	public IClassType getClassType() {
		return ClassType.GENERIC_SYMBOL;
	}

	@Override
	public int getGenericsCount() {
		// Generic types never have generic children
		return 0;
	}

}
