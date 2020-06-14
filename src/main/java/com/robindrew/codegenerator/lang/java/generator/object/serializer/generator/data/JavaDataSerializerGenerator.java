package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.data;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.data.method.ReadObjectMethod;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.data.method.WriteObjectMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;
import com.robindrew.codegenerator.setup.ISetup;
import com.robindrew.common.io.data.serializer.ObjectSerializer;

public class JavaDataSerializerGenerator extends JavaObjectGenerator {

	private final JavaModelDataSerializer serializer;
	private IJavaType classType = null;
	private JavaModelBean bean = null;

	public JavaDataSerializerGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelDataSerializer serializer) {
		super(setup, context, model, serializer.get());
		this.serializer = serializer;
	}

	public JavaModelDataSerializer getSerializer() {
		return serializer;
	}

	@Override
	public void registerPrimaryTypes() {
		classType = registerJavaType(serializer.getName(), CLASS);
		getSerializer().setType(classType);
	}

	@Override
	public void registerSecondaryTypes() {

		// Resolve bean type and link
		IJavaType beanType = resolve(getSerializer().get().getType());
		bean = getContext().getModelSet().getBean(beanType, false);
		bean.setDataSerializer(serializer);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {

		// For each bean we generate an interface
		JavaObject object = new JavaObject(classType);
		object.addExtends(resolve(ObjectSerializer.class, bean.getInterfaceType()));

		addDefaultConstructor(object);
		addSetterConstructor(object);

		// Add the fields, getters and setter methods
		addBlocks(object);

		// Done!
		write(object);
	}

	private void addSetterConstructor(JavaObject object) {
		IJavaMethod constructor = new JavaConstructor(object.getType());
		constructor.setContents(new JavaCodeLines("super(nullable);"));
		constructor.getParameters().add(new JavaNamedType("nullable", boolean.class));
		object.addBlock(constructor);
	}

	private void addDefaultConstructor(JavaObject object) {
		IJavaMethod constructor = new JavaConstructor(object.getType());
		constructor.setContents(new JavaCodeLines("super(false);"));
		object.addBlock(constructor);
	}

	private void addBlocks(JavaObject object) {
		IJavaModelSet modelSet = getContext().getModelSet();
		IJavaTypeResolver resolver = getContext().getResolver();
		object.addBlock(new ReadObjectMethod(bean, modelSet, resolver));
		object.addBlock(new WriteObjectMethod(bean, modelSet, resolver));
	}

}
