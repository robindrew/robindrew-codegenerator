package com.robindrew.codegenerator.lang.java.type.block.codeblock;

import java.util.Collection;

import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;

public interface IJavaCodeLines extends IJavaClassBlock, IJavaCodeBlock {

	IJavaCodeLines line(CharSequence line);

	IJavaCodeLines lines(Collection<? extends CharSequence> lines);

	IJavaCodeLines lines(CharSequence... lines);

	IJavaCodeLines line(int indent, CharSequence line);

	IJavaCodeLines lines(int indent, Collection<? extends CharSequence> lines);

	IJavaCodeLines lines(int indent, CharSequence... lines);

	void setIndent(int indent);

	IJavaCodeLines emptyLine();

}
