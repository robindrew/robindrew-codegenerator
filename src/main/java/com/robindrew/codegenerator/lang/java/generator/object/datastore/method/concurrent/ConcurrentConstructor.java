package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class ConcurrentConstructor extends JavaConstructor {

	public ConcurrentConstructor(String name, IJavaNamedType delegate, IJavaNamedType lockField) {
		super(name);
		getParameters().add(new JavaNamedType(delegate));
		getParameters().add(new JavaNamedType(lockField));
		getReferences().add(ReentrantReadWriteLock.class);
		getReferences().add(ReadWriteLock.class);

		IJavaCodeLines contents = new JavaCodeLines();
		new NullPointerConstraint(delegate).appendTo(contents);
		new NullPointerConstraint(lockField).appendTo(contents);
		contents.line("this." + delegate.getName() + " = " + delegate.getName() + ";");
		contents.line("this." + lockField.getName() + " = " + lockField.getName() + ";");
		setContents(contents);
	}

	public ConcurrentConstructor(String name, IJavaNamedType delegate) {
		super(name);
		getParameters().add(new JavaNamedType(delegate));
		getReferences().add(ReentrantReadWriteLock.class);

		IJavaCodeLines contents = new JavaCodeLines();
		contents.line("this(" + delegate.getName() + ", new ReentrantReadWriteLock(true));");
		setContents(contents);
	}
}
