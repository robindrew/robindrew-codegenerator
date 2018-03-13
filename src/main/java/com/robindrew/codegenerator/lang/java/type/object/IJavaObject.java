package com.robindrew.codegenerator.lang.java.type.object;

import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.eenum.IJavaEnumField;
import com.robindrew.codegenerator.lang.java.type.block.visibility.IJavaVisibility;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaTypedWritable;

public interface IJavaObject extends IJavaTypedWritable {

	boolean isStatic();

	boolean isAbstract();

	IJavaVisibility getVisibility();

	IJavaType getType();

	Set<IJavaEnumField> getEnumFields();

	Set<IJavaClassBlock> getBlocks();

	IJavaTypeSet getImports();

	IJavaTypeSet getExtends();

	void addBlock(IJavaClassBlock block);

	void addBlocks(Iterable<? extends IJavaClassBlock> blocks);

	void setVisibility(IJavaVisibility visibility);

	void setStatic(boolean value);

	void setAbstract(boolean value);

	void addImport(IJavaType type);

	void addExtends(IJavaType type);

}
