package com.robindrew.codegenerator.lang.java.type.block.method.concurrent;

import java.util.concurrent.locks.ReadWriteLock;

import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;

public class ReadWriteLockField extends JavaField {

	public static final String DEFAULT_NAME = "reentrantLock";

	public ReadWriteLockField(String name) {
		super(name, ReadWriteLock.class);
	}

	public ReadWriteLockField() {
		this(DEFAULT_NAME);
	}

}
