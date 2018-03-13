package com.robindrew.codegenerator.lang.java.type.block.annotation.field;

import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;

public interface IJavaAnnotationField extends IJavaNamedType, IJavaClassBlock {

	IJavaCodeBlock getDefaultValue();

	boolean hasDefaultValue();

	void setDefaultValue(IJavaCodeBlock code);

	void setDefaultValue(Object code);

	IMultiComment getComment();
}
