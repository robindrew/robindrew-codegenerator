package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class SqlConstructor extends JavaConstructor {

	public SqlConstructor(String name, JavaField lockField, boolean lockParameter) {
		super(name);
		if (lockParameter) {
			getParameters().add(new JavaNamedType(lockField));
		}
		getReferences().add(ReentrantReadWriteLock.class);

		IJavaCodeLines code = new JavaCodeLines();
		if (lockParameter) {
			new NullPointerConstraint(lockField.getName().get()).appendTo(code);
			code.line("this." + lockField.getName() + " = " + lockField.getName() + ";");
		} else {
			code.line("this(new ReentrantReadWriteLock(true));");
		}
		setContents(code);
	}
}
