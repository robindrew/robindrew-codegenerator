package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method.ReadObjectMethod;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method.ReadObjectReturnTypeMethod;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method.WriteObjectMethod;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method.WriteObjectReturnTypeMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.field.IJavaField;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaJsonSerializerGenerator extends JavaObjectGenerator {

	private final JavaModelJsonSerializer serializer;
	private IJavaType classType = null;
	private IJavaType returnTypeClassType = null;
	private JavaModelBean bean = null;

	public JavaJsonSerializerGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelJsonSerializer serializer) {
		super(setup, context, model, serializer.get());
		this.serializer = serializer;
	}

	public JavaModelJsonSerializer getSerializer() {
		return serializer;
	}

	public boolean isReturnType() {
		return serializer.isReturnType();
	}

	@Override
	public void registerPrimaryTypes() {
		classType = registerJavaType(serializer.getName(), CLASS);
		getSerializer().setType(classType);

		// Return Type?
		if (isReturnType()) {
			returnTypeClassType = registerJavaType(serializer.get().getReturnTypeName(), CLASS);
			getSerializer().setReturnType(returnTypeClassType);
		}
	}

	@Override
	public void registerSecondaryTypes() {

		// Resolve bean type and link
		IJavaType beanType = resolve(getSerializer().get().getType());
		bean = getContext().getModelSet().getBean(beanType, false);
		bean.setJsonSerializer(serializer);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {

		// For each bean we generate an interface
		JavaObject object = new JavaObject(classType);
		object.addExtends(resolve(IJsonSerializer.class, bean.getInterfaceType()));

		IJavaField nameField = new JavaField("name", String.class);
		object.addBlock(nameField);

		// Empty Constructor
		IJavaMethod emptyConstructor = new JavaConstructor(object.getType());
		emptyConstructor.setContents(new JavaCodeLines("this(\"" + bean.get().getName() + "\");"));

		// Constructor with single argument "name"
		IJavaMethod nameConstructor = new JavaConstructor(object.getType());
		nameConstructor.getParameters().add(new JavaNamedType(nameField));
		nameConstructor.setContents(new JavaCodeLines("this.name = name;"));

		object.addBlock(emptyConstructor);
		object.addBlock(nameConstructor);
		object.addBlock(new GetterMethod(nameField));

		// Add the fields, getters and setter methods
		IJavaModelSet modelSet = getContext().getModelSet();
		IJavaTypeResolver resolver = getContext().getResolver();
		object.addBlock(new ReadObjectMethod(bean, modelSet, resolver));
		object.addBlock(new WriteObjectMethod(bean, modelSet, resolver));

		// Done!
		write(object);

		if (isReturnType()) {
			generateReturnType();
		}

	}

	private void generateReturnType() {

		// For each bean we generate an interface
		JavaObject object = new JavaObject(returnTypeClassType);
		object.addExtends(resolve(IJsonSerializer.class, bean.getReturnType().toObjectType()));

		IJavaField nameField = new JavaField("name", String.class);
		object.addBlock(nameField);

		// Empty Constructor
		IJavaMethod emptyConstructor = new JavaConstructor(object.getType());
		emptyConstructor.setContents(new JavaCodeLines("this(\"" + bean.get().getName() + "ReturnType\");"));

		// Constructor with single argument "name"
		IJavaMethod nameConstructor = new JavaConstructor(object.getType());
		nameConstructor.getParameters().add(new JavaNamedType(nameField));
		nameConstructor.setContents(new JavaCodeLines("this.name = name;"));

		object.addBlock(emptyConstructor);
		object.addBlock(nameConstructor);
		object.addBlock(new GetterMethod(nameField));

		// Add the fields, getters and setter methods
		IJavaModelSet modelSet = getContext().getModelSet();
		IJavaTypeResolver resolver = getContext().getResolver();
		object.addBlock(new ReadObjectReturnTypeMethod(bean, modelSet, resolver));
		object.addBlock(new WriteObjectReturnTypeMethod(bean, modelSet, resolver));

		// Done!
		write(object);
	}

}
