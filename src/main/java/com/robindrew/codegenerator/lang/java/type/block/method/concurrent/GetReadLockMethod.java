package com.robindrew.codegenerator.lang.java.type.block.method.concurrent;

import static com.robindrew.codegenerator.lang.java.type.block.method.concurrent.ReadWriteLockField.DEFAULT_NAME;

import java.util.concurrent.locks.Lock;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetReadLockMethod extends JavaMethod {

	public GetReadLockMethod() {
		super("getReadLock", Lock.class);
	}

	public GetReadLockMethod setDefaultContents() {
		IJavaCodeLines lines = new JavaCodeLines();
		lines.line("return " + DEFAULT_NAME + ".readLock();");
		setContents(lines);
		return this;
	}
}
