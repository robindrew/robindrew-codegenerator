package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class MapConstructor extends JavaConstructor {

	public MapConstructor(String name, IJavaNamedType mapField, IJavaNamedType lockField, JavaField autoIncrementField, boolean lockParameter) {
		super(name);
		getParameters().add(new JavaNamedType(mapField));
		if (lockParameter) {
			getParameters().add(new JavaNamedType(lockField));
		}
		getReferences().add(Map.class);
		getReferences().add(ReentrantReadWriteLock.class);
		if (autoIncrementField != null) {
			getReferences().add(AtomicInteger.class);
		}

		IJavaCodeLines code = new JavaCodeLines();
		new NullPointerConstraint(mapField).appendTo(code);
		code.line("this." + mapField.getName() + " = " + mapField.getName() + ";");
		if (lockParameter) {
			code.line("this." + lockField.getName() + " = " + lockField.getName() + ";");
		} else {
			code.line("this." + lockField.getName() + " = new ReentrantReadWriteLock(true);");
		}
		if (autoIncrementField != null) {
			code.line("this." + autoIncrementField.getName() + " = new AtomicInteger(0);");
		}
		setContents(code);
	}
}
