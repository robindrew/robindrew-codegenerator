package com.robindrew.codegenerator.lang.java.type.block.method;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.annotation.IJavaAnnotationSet;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.visibility.IJavaVisibility;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public interface IJavaMethod extends IJavaClassBlock {

	IJavaName getName();

	IJavaType getReturnType();

	IJavaNamedTypeSet getParameters();

	IJavaVisibility getVisibility();

	boolean isStatic();

	boolean isConstructor();

	boolean isAbstract();

	boolean isInterface();

	IJavaMethod setVisibility(IJavaVisibility visibility);

	IJavaMethod setStatic(boolean value);

	IJavaMethod setAbstract(boolean value);

	IJavaMethod setConstructor(boolean value);

	IJavaMethod setInterface(boolean value);

	IJavaCodeBlock getContents();

	IJavaAnnotationSet getAnnotations();

	void setContents(IJavaCodeBlock block);

	boolean hasContents();

	IMultiComment getComment();

	IJavaTypeSet getThrowsSet();

	IJavaMethod setOverride();
}
