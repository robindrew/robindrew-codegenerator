package com.robindrew.codegenerator.lang.java.type.block.field;

import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.annotation.IJavaAnnotationSet;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.block.visibility.IJavaVisibility;

public interface IJavaField extends IJavaNamedType, IJavaClassBlock {

	IJavaVisibility getVisibility();

	boolean isStatic();

	boolean isTransient();

	@Override
	boolean isFinal();

	boolean isConstant();

	boolean isVolatile();

	boolean isInterface();

	IJavaField setVisibility(IJavaVisibility visibility);

	IJavaField setStatic(boolean value);

	IJavaField setFinal(boolean value);

	IJavaField setConstant(boolean value);

	IJavaField setTransient(boolean value);

	IJavaField setVolatile(boolean value);

	IJavaField setInterface(boolean value);

	IJavaCodeBlock getAssignment();

	IJavaAnnotationSet getAnnotations();

	boolean hasAssignment();

	IJavaField setAssignment(IJavaCodeBlock code);

	IJavaField setAssignment(Object code);

	IMultiComment getComment();

}
