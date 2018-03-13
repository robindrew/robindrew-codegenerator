package com.robindrew.codegenerator.lang.java.type.block.comment;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWritable;

public interface IComment extends IJavaWritable {

	int size();

	boolean isEmpty();

	void clear();

}
