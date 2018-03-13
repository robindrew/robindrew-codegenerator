package com.robindrew.codegenerator.lang.java.generator.object.datastore.key;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class KeyConstructor extends JavaConstructor {

	public KeyConstructor(String name, IJavaNamedType elementType) {
		super(name);
		getParameters().add(new JavaNamedType(elementType));

		IJavaCodeLines contents = new JavaCodeLines();
		new NullPointerConstraint(elementType).appendTo(contents);
		contents.line("this." + elementType.getName() + " = " + elementType.getName() + ";");
		setContents(contents);
	}

}
