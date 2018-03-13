package com.robindrew.codegenerator.lang.java.type.block.eenum;

import java.util.List;

import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public interface IJavaEnumField extends IJavaClassBlock {

	IJavaName getName();

	IJavaCodeBlock getContents();

	List<String> getParameters();

	void addParameter(String value);

	void setContents(IJavaCodeBlock block);

	boolean hasContents();

	IMultiComment getComment();
}
