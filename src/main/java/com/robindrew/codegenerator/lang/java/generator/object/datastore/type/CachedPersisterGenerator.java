package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreMethods;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister.CachedPersisterAddAutoMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister.CachedPersisterCreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister.CachedPersisterDestroyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister.CachedPersisterExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister.CachedPersisterMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister.ICachedPersisterMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.IJavaField;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetFieldsBlock;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public class CachedPersisterGenerator extends TypeGenerator {

	private IJavaType type = null;
	private JavaField cache = null;
	private JavaField persister = null;

	public CachedPersisterGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		type = registerJavaType("CachedPersister" + getDataStore().getName(), CLASS);
	}

	@Override
	public void generate() {
		JavaObject object = new JavaObject(type);
		object.addExtends(getInterfaceType());

		// Locks
		cache = new JavaField("cache", getInterfaceType()).setFinal(true);
		persister = new JavaField("persister", getInterfaceType()).setFinal(true);
		object.addBlock(cache);
		object.addBlock(persister);

		// Constructor
		addConstructor(object, cache, persister);

		// Add methods
		addMethods(object);

		write(object);
	}

	private void addMethods(JavaObject object) {
		JavaDataStoreMethods methods = new JavaDataStoreMethods(this);
		methods.addDefaultMethods();
		methods.add(new CachedPersisterCreateMethod());
		methods.add(new CachedPersisterDestroyMethod());
		methods.add(new CachedPersisterExistsMethod());
		if (getDataStore().isAutoIncrement()) {
			methods.add(new CachedPersisterAddAutoMethod(getDataStore()));
		}

		for (JavaMethod method : methods) {
			if (method instanceof ICachedPersisterMethod) {
				object.addBlock(method);
			} else {
				if (isDelegate(method)) {
					object.addBlock(toDelegate(method));
				} else {
					object.addBlock(toCachedPersister(method));
				}
			}
		}
	}

	private boolean isDelegate(JavaMethod method) {
		return !method.getReturnType().isVoid();
	}

	private DelegateMethod toCachedPersister(IJavaMethod method) {
		return new CachedPersisterMethod(cache, persister, method).setDelegateContents();
	}

	@Override
	protected IJavaMethod toDelegate(JavaMethod method) {
		return new DelegateMethod(method).setFieldName("cache").setDelegateContents().setOverride();
	}

	private void addConstructor(JavaObject object, IJavaField cache, IJavaField persister) {
		IJavaNamedTypeSet fields = new JavaNamedTypeSet();
		fields.add(new JavaNamedType(cache));
		fields.add(new JavaNamedType(persister));

		JavaConstructor constructor = new JavaConstructor(type);
		new SetFieldsBlock(fields, true).populate(constructor);
		object.addBlock(constructor);
	}

}
