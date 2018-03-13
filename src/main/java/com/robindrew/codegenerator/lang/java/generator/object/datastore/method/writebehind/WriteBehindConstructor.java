package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.writebehind;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class WriteBehindConstructor extends JavaConstructor {

	public WriteBehindConstructor(String name, IJavaNamedType delegate, IJavaNamedType executor) {
		super(name);
		getParameters().add(new JavaNamedType(delegate));
		getParameters().add(new JavaNamedType(executor));

		IJavaCodeLines contents = new JavaCodeLines();
		new NullPointerConstraint(delegate).appendTo(contents);
		new NullPointerConstraint(executor).appendTo(contents);
		contents.line("this." + delegate.getName() + " = " + delegate.getName() + ";");
		contents.line("this." + executor.getName() + " = " + executor.getName() + ";");
		setContents(contents);
	}

}
