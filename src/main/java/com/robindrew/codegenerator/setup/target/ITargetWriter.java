package com.robindrew.codegenerator.setup.target;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaTypedWritable;

public interface ITargetWriter {

	void clean();

	void write(IJavaTypedWritable writable);

}
