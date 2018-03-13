package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.concurrent;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;

public class ConcurrentMethod extends DelegateMethod {

	private final boolean readLock;
	private final boolean checkParams;

	public ConcurrentMethod(IJavaMethod method, boolean readLock, boolean checkParams) {
		super(method);
		this.readLock = readLock;
		this.checkParams = checkParams;
		getReferences().add(Lock.class);
	}

	public IJavaCodeLines aquireLock(IJavaCodeLines code) {

		// Lock
		if (readLock) {
			code.line(0, "Lock lock = getReadLock();");
		} else {
			code.line(0, "Lock lock = getWriteLock();");
		}
		code.line(0, "lock.lock();");
		code.line(0, "try {");
		code.setIndent(1);
		return code;
	}

	public IJavaCodeLines releaseLock(IJavaCodeLines code) {

		// Unlock
		code.setIndent(-1);
		code.line(0, "} finally {");
		code.line(1, "lock.unlock();");
		code.line(0, "}");
		return code;
	}

	@Override
	public ConcurrentMethod setDelegateContents() {
		IJavaCodeLines contents = new JavaCodeLines();
		if (checkParams) {
			checkParameters(contents);
		}
		aquireLock(contents);
		setDelegateContents(contents);
		releaseLock(contents);
		setContents(contents);
		return this;
	}

	private void checkParameters(IJavaCodeLines contents) {

		// Performs checks to avoid locking unnecessarily
		for (IJavaNamedType parameter : getParameters()) {
			String name = parameter.getName().get();
			IJavaType type = parameter.getType();
			if (type.isPrimitive()) {
				continue;
			}

			// No Nulls Please!
			new NullPointerConstraint(parameter).appendTo(contents);

			// Collections and Maps should not be empty
			if (type.isInstanceOf(Collection.class) || type.isInstanceOf(Map.class)) {
				contents.line("if (" + name + ".isEmpty()) {");
				if (getReturnType().isVoid()) {
					contents.line(1, "return;");
				} else {
					contents.line(1, "return null;");
				}
				contents.line(0, "}");
			}
		}
	}
}
