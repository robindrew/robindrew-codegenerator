package com.robindrew.codegenerator.lang.java.type.block;

import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWritable;

public interface IJavaClassBlock extends IJavaWritable {

	IJavaTypeSet getReferences();

}
