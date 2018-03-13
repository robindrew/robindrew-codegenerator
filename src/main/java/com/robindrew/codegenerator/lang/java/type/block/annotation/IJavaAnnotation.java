package com.robindrew.codegenerator.lang.java.type.block.annotation;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;

public interface IJavaAnnotation extends IJavaClassBlock {

	IJavaType getType();
}
