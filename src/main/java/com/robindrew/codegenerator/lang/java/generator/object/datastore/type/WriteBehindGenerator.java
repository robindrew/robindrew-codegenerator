package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.concurrent.ExecutorService;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreMethods;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.CreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.DestroyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.writebehind.WriteBehindConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.writebehind.WriteBehindMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.IJavaField;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetReadLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetWriteLockMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public class WriteBehindGenerator extends TypeGenerator {

	private IJavaType writeBehindType = null;

	public WriteBehindGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		writeBehindType = registerJavaType("WriteBehind" + getDataStore().getName(), CLASS);
	}

	@Override
	public void generate() {
		JavaObject object = new JavaObject(writeBehindType);
		object.addExtends(getInterfaceType());

		// Fields
		JavaField delegate = new JavaField("delegate", getInterfaceType()).setFinal(true);
		IJavaField executor = new JavaField("executor", ExecutorService.class).setFinal(true);
		object.addBlock(delegate);
		object.addBlock(executor);

		// Constructor
		object.addBlock(new WriteBehindConstructor(writeBehindType.getSimpleName(), delegate, executor));

		// Write behdin methods
		JavaDataStoreMethods methods = new JavaDataStoreMethods(this);
		methods.addDefaultMethods();
		for (JavaMethod method : methods) {
			if (isDelegate(method)) {
				object.addBlock(toDelegate(method));
			} else {
				object.addBlock(toWriteBehind(method, isForceSync(method)));
			}
		}

		write(object);
	}

	private boolean isForceSync(JavaMethod method) {
		// Methods to make synchronous even though they return no value
		return method instanceof CreateMethod || method instanceof DestroyMethod || method instanceof ExistsMethod;
	}

	private boolean isDelegate(JavaMethod method) {
		return method instanceof GetReadLockMethod || method instanceof GetWriteLockMethod;
	}

	private IJavaMethod toWriteBehind(IJavaMethod method, boolean forceSync) {
		return new WriteBehindMethod(method, forceSync).setParametersFinal().setDelegateContents().setOverride();
	}

}
