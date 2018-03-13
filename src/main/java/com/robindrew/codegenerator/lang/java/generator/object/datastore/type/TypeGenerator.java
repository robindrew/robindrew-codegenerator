package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.IJavaGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.IJavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaTypedWritable;
import com.robindrew.codegenerator.model.object.ModelObject;
import com.robindrew.codegenerator.model.object.name.ModelName;

public abstract class TypeGenerator implements IJavaGenerator, IJavaDataStoreGenerator {

	private final JavaDataStoreGenerator generator;

	protected TypeGenerator(JavaDataStoreGenerator generator) {
		if (generator == null) {
			throw new NullPointerException("generator");
		}
		this.generator = generator;
	}

	@Override
	public void registerPrimaryTypes() {
	}

	@Override
	public void verifyReferencedTypes() {
	}

	public IJavaType getInterfaceType() {
		return getDataStore().getInterfaceType();
	}

	public IJavaContext getContext() {
		return generator.getContext();
	}

	public JavaModelDataStore getDataStore() {
		return generator.getDataStore();
	}

	public JavaModelBean getElementBean() {
		return getDataStore().getElementBean();
	}

	public JavaModelBean getKeyBean() {
		return generator.getDataStore().getKeyBean();
	}

	public IJavaType resolve(Class<?> type) {
		return generator.resolve(type);
	}

	public IJavaType resolve(Class<?> type, IJavaType... genericsList) {
		return generator.resolve(type, genericsList);
	}

	public IJavaType resolve(String name) {
		return generator.resolve(name);
	}

	public IJavaType registerJavaType(String name, IClassType classType) {
		return generator.registerJavaType(name, classType);
	}

	public IJavaNamedTypeSet getKeyFields() {
		return generator.getKeyFields();
	}

	public void write(IJavaTypedWritable writable) {
		generator.write(writable);
	}

	protected IJavaMethod toDelegate(JavaMethod method) {
		return new DelegateMethod(method).setDelegateContents().setOverride();
	}

	public String getName(ModelName typeName, String name) {
		return generator.getName(typeName, name);
	}

	public String getName(ModelObject object, String name) {
		return generator.getName(object, name);
	}

}
