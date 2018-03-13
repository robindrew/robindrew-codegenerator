package com.robindrew.codegenerator.lang.java.type.block.comment;

public interface IMultiComment extends IComment {

	IMultiComment line();

	IMultiComment line(Object line);

	IMultiComment lines(Object... lines);

	IMultiComment setSingleLine(boolean singleLine);

}
