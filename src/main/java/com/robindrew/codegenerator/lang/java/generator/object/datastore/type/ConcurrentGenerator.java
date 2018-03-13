package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreMethods;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.concurrent.ConcurrentMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetReadLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetWriteLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public class ConcurrentGenerator extends TypeGenerator {

	private IJavaType concurrentType = null;

	public ConcurrentGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		concurrentType = registerJavaType("Concurrent" + getDataStore().getName(), CLASS);
	}

	@Override
	public void generate() {
		JavaObject object = new JavaObject(concurrentType);
		object.addExtends(getInterfaceType());

		// Delegate field
		JavaField delegateField = new JavaField("delegate", getInterfaceType()).setFinal(true);
		object.addBlock(delegateField);

		// Delegate constructor
		object.addBlock(new DelegateConstructor(concurrentType.getSimpleName(), delegateField));

		// Delegate getter
		object.addBlock(new GetterMethod(delegateField));

		// Concurrent methods
		JavaDataStoreMethods methods = new JavaDataStoreMethods(this);
		methods.addDefaultMethods();
		for (JavaMethod method : methods) {
			if (isDelegate(method)) {
				object.addBlock(toDelegate(method));
			} else {
				object.addBlock(toConcurrent(method));
			}
		}

		// Done
		write(object);
	}

	private boolean isDelegate(JavaMethod method) {
		return method instanceof GetReadLockMethod || method instanceof GetWriteLockMethod;
	}

	private IJavaClassBlock toConcurrent(IJavaMethod method) {
		boolean readLock = !method.getReturnType().isVoid();
		return new ConcurrentMethod(method, readLock, false).setDelegateContents().setOverride();
	}

}
