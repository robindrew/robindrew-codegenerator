package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class CopyConstructor extends JavaConstructor {

	public CopyConstructor(String name, IJavaNamedType delegate, IJavaNamedType copyOnRead, IJavaNamedType copyOnWrite) {
		super(name);
		getParameters().add(new JavaNamedType(delegate));
		getParameters().add(new JavaNamedType(copyOnRead));
		getParameters().add(new JavaNamedType(copyOnWrite));

		IJavaCodeLines contents = new JavaCodeLines();
		new NullPointerConstraint(delegate).appendTo(contents);
		contents.line("this." + delegate.getName() + " = " + delegate.getName() + ";");
		contents.line("this." + copyOnRead.getName() + " = " + copyOnRead.getName() + ";");
		contents.line("this." + copyOnWrite.getName() + " = " + copyOnWrite.getName() + ";");
		setContents(contents);
	}

}
