package com.robindrew.codegenerator.lang.java.type.block.method.delegate;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class DelegateConstructor extends JavaConstructor {

	public DelegateConstructor(String name, IJavaNamedType delegate) {
		super(name);
		getParameters().add(new JavaNamedType(delegate));

		IJavaCodeLines contents = new JavaCodeLines();
		new NullPointerConstraint(delegate).appendTo(contents);
		contents.line("this." + delegate.getName() + " = " + delegate.getName() + ";");
		setContents(contents);
	}

	public DelegateConstructor(String name, IJavaType delegateType) {
		this(name, new JavaNamedType(DelegateField.DEFAULT_NAME, delegateType));
	}

}
