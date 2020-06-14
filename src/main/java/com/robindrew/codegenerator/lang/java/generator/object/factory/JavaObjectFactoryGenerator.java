package com.robindrew.codegenerator.lang.java.generator.object.factory;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.Arrays;
import java.util.List;

import com.robindrew.codegenerator.api.bean.IBean;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.factory.IObjectFactory;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.servlet.IServletRequestAdapter;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.factory.field.FactoryMapField;
import com.robindrew.codegenerator.lang.java.generator.object.factory.method.ContainsTypeMethod;
import com.robindrew.codegenerator.lang.java.generator.object.factory.method.FactoryConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.factory.method.GetTypeMethod;
import com.robindrew.codegenerator.lang.java.generator.object.factory.method.GetTypesMethod;
import com.robindrew.codegenerator.lang.java.generator.object.factory.method.NewInstanceMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;
import com.robindrew.codegenerator.model.object.factory.ModelObjectFactory;
import com.robindrew.codegenerator.setup.ISetup;
import com.robindrew.common.io.data.IDataSerializer;

public class JavaObjectFactoryGenerator extends JavaObjectGenerator {

	private final ModelObjectFactory factory;

	private IJavaType objectType = null;

	public JavaObjectFactoryGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelObjectFactory factory) {
		super(setup, context, model, factory);
		if (factory == null) {
			throw new NullPointerException("factory");
		}
		this.factory = factory;
	}

	public ModelObjectFactory getFactory() {
		return factory;
	}

	public FactoryScope getScope() {
		return FactoryScope.valueOf(factory.getScope());
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this factory as a type
		objectType = registerJavaType(getFactory().getName(), CLASS);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {
		IJavaTypeResolver resolver = getContext().getResolver();
		resolver.registerAlias(IBean.class, 0);
		resolver.registerAlias(IExecutableBean.class, 1);
		resolver.registerAlias(IDataSerializer.class, 1);
		resolver.registerAlias(IXmlSerializer.class, 1);
		resolver.registerAlias(IJsonSerializer.class, 1);
		resolver.registerAlias(IServletRequestAdapter.class, 1);

		FactoryType type = FactoryType.valueOf(getFactory().getFactory());
		generate(resolver, type);
	}

	private void generate(IJavaTypeResolver resolver, FactoryType factoryType) {
		IJavaType type = resolver.resolveJavaType(factoryType.getType());
		IJavaType factory = resolver.registerClass(IObjectFactory.class, 1);
		factory = new JavaTypeWithGenerics(factory, type);

		// For each factory we generate a class
		JavaObject object = new JavaObject(objectType);
		object.addExtends(factory);

		// Field
		object.addBlock(new FactoryMapField(resolver, type));

		List<JavaModel> models = getModelList();
		// Constructor
		FactoryConstructor constructor = new FactoryConstructor(objectType, models, factoryType);
		object.addBlock(constructor);

		// Method
		object.addBlock(new GetTypesMethod(resolver, type));
		object.addBlock(new ContainsTypeMethod(resolver, type));
		object.addBlock(new GetTypeMethod(resolver, type));
		object.addBlock(new NewInstanceMethod(resolver, type));

		// Done!
		write(object);
	}

	private List<JavaModel> getModelList() {
		FactoryScope scope = getScope();
		switch (scope) {
			case GLOBAL:
				return getContext().getModelSet().getModelList();
			case LOCAL:
				return Arrays.asList(getModel());
			default:
				throw new IllegalArgumentException("Scope not supported: " + scope);
		}
	}
}
