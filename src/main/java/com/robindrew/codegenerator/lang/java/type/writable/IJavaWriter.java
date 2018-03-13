package com.robindrew.codegenerator.lang.java.type.writable;

import com.robindrew.codegenerator.lang.java.type.IJavaType;

public interface IJavaWriter {

	IJavaWriter write(IJavaWritable writable);

	IJavaWriter write(IJavaWritable writable, boolean write);

	IJavaWriter write(IJavaType type);

	IJavaWriter write(CharSequence value);

	IJavaWriter write(String value);

	IJavaWriter write(boolean value);

	IJavaWriter write(byte value);

	IJavaWriter write(short value);

	IJavaWriter write(int value);

	IJavaWriter write(long value);

	IJavaWriter write(float value);

	IJavaWriter write(double value);

	IJavaWriter write(char value);

	IJavaWriter shiftIndent(int value);

	IJavaWriter setIndent(int value);

	IJavaWriter line();

	IJavaWriter indent();

	IJavaWriter space();

	IJavaWriter semi();

	IJavaWriter comma();

	int getIndent();

}
