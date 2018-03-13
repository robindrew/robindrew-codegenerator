package com.robindrew.codegenerator.lang.java.generator.object;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;
import com.robindrew.codegenerator.model.object.ModelObject;
import com.robindrew.codegenerator.model.object.name.ModelName;
import com.robindrew.codegenerator.setup.ISetup;

public abstract class JavaGenerator implements IJavaGenerator {

	private final ISetup setup;
	private final IJavaContext context;
	private final JavaModel model;

	protected JavaGenerator(ISetup setup, IJavaContext context, JavaModel model) {
		if (setup == null) {
			throw new NullPointerException("setup");
		}
		if (context == null) {
			throw new NullPointerException("context");
		}
		if (model == null) {
			throw new NullPointerException("model");
		}
		this.setup = setup;
		this.context = context;
		this.model = model;
	}

	public ISetup getSetup() {
		return setup;
	}

	public IJavaContext getContext() {
		return context;
	}

	public JavaModel getModel() {
		return model;
	}

	public String getName(ModelName typeName, String name) {
		return typeName.getPrefix() + name + typeName.getPostfix();
	}

	public String getName(ModelObject object, String name) {
		ModelName typeName = object.getInterfaceName();
		return getName(typeName, name);
	}

	protected void setExtends(IJavaType type, IJavaType extendsType) {
		context.getExtendsMap().put(type, extendsType);
	}

	public IJavaType resolve(Class<?> type) {
		return context.getResolver().registerClass(type);
	}

	public IJavaType resolve(Class<?> type, int generics) {
		return context.getResolver().registerClass(type, generics);
	}

	public IJavaType resolve(Class<?> type, IJavaType... genericsList) {
		IJavaType resolved = context.getResolver().registerClass(type, genericsList.length);
		resolved = new JavaTypeWithGenerics(resolved, genericsList);
		return resolved;
	}

	public IJavaType resolve(String name) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		return context.getResolver().resolveJavaType(name);
	}

	public IJavaType registerJavaType(String name, IClassType classType) {
		// We register an alias to guarantee that every
		// generated object has a unique name!
		String packageName = getModel().get().getPackage();
		return context.getResolver().registerAndAliasJavaType(packageName, name, classType);
	}

	@Override
	public void registerSecondaryTypes() {
		// Not used for most generators
	}

}
