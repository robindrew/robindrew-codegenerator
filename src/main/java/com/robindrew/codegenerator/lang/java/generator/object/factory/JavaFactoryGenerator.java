package com.robindrew.codegenerator.lang.java.generator.object.factory;

import java.util.HashSet;
import java.util.Set;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.factory.block.FactoryConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.factory.block.FactoryMapField;
import com.robindrew.codegenerator.lang.java.generator.object.factory.block.NewInstanceMethod;
import com.robindrew.codegenerator.lang.java.generator.object.factory.block.RegisterMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.classtype.ClassType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.factory.ModelFactory;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaFactoryGenerator extends JavaObjectGenerator {

	private final ModelFactory factory;

	private IJavaType objectType = null;
	private IJavaType factoryType = null;

	public JavaFactoryGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelFactory factory) {
		super(setup, context, model, factory);
		if (factory == null) {
			throw new NullPointerException("factory");
		}
		this.factory = factory;
	}

	public ModelFactory getFactory() {
		return factory;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this factory as a type
		objectType = registerJavaType(getFactory().getName(), ClassType.CLASS);
	}

	@Override
	public void verifyReferencedTypes() {
		factoryType = resolve(getFactory().getType());
	}

	@Override
	public void generate() {

		// For each factory we generate a class
		JavaObject object = new JavaObject(objectType);

		// Field
		object.addBlock(new FactoryMapField(getContext().getResolver()));

		// Constructor
		object.addBlock(getConstructor());

		// Method
		object.addBlock(new RegisterMethod(getClassType()));
		object.addBlock(new NewInstanceMethod(factoryType));

		// Done!
		write(object);
	}

	private IJavaType getClassType() {
		return getContext().getResolver().resolveJavaType("Class<?>");
	}

	private IJavaClassBlock getConstructor() {
		IJavaName name = new JavaName(objectType.getSimpleName());

		Set<IJavaType> factoryTypeSet = new HashSet<IJavaType>();
		Set<IJavaType> types = getContext().getResolver().getRegisteredTypes();
		for (IJavaType type : types) {
			Set<IJavaType> extendsList = getContext().getExtendsMap().get(type);
			if (extendsList.contains(factoryType)) {
				factoryTypeSet.add(type);
			}
		}

		return new FactoryConstructor(name, factoryTypeSet);
	}

}
