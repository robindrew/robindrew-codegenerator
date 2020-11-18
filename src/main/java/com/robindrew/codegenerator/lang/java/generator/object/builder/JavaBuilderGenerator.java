package com.robindrew.codegenerator.lang.java.generator.object.builder;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.validator.IJavaValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.IJavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.build.BuildMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetFieldsBlock;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.classtype.ClassType;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.model.object.builder.ModelBuilder;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaBuilderGenerator extends JavaObjectGenerator {

	private final ModelBuilder builder;

	private IJavaType objectType = null;
	private IJavaType beanType = null;
	private JavaModelBean bean = null;
	private IJavaNamedTypeSet fieldSet = null;

	public JavaBuilderGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelBuilder builder) {
		super(setup, context, model, builder);
		if (builder == null) {
			throw new NullPointerException("builder");
		}
		this.builder = builder;
	}

	public ModelBuilder getBuilder() {
		return builder;
	}

	public JavaModelBean getBean() {
		return bean;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this annotation as a type
		objectType = registerJavaType(getBuilder().getName(), ClassType.CLASS);
	}

	@Override
	public void verifyReferencedTypes() {
		// Resolve bean type and link
		beanType = resolve(getBuilder().getBean());
		bean = getContext().getModelSet().getBean(beanType, false);
	}

	@Override
	public void generate() {

		// For each comparator we generate a class
		JavaObject object = new JavaObject(objectType);

		// Fields
		addFields(object);

		// Empty constructor
		addEmptyConstructor(object);

		// Types constructor
		addTypesConstructor(object);

		// Clone constructor
		addCloneConstructor(object);

		// Setters
		addSetters(object);

		// Build!
		addBuild(object);

		// Done!
		write(object);
	}

	private void addCloneConstructor(JavaObject object) {
		boolean direct = false;
		IJavaNamedTypeSet fields = getFields();
		IJavaMethod constructor = new JavaConstructor(object.getType());
		new SetFieldsBlock(fields, direct).populate(constructor, new JavaNamedType("clone", getBean().getInterfaceType()));
		constructor.getComment().line("The clone constructor.");
		object.addBlock(constructor);
	}

	private void addTypesConstructor(JavaObject object) {
		IJavaNamedTypeSet fields = getFields();
		IJavaMethod constructor = new JavaConstructor(object.getType());
		constructor.getParameters().addAll(fields);
		IJavaCodeLines code = new JavaCodeLines();

		new SetFieldsBlock(fields, false).populate(code);
		constructor.setContents(code);
		constructor.getComment().line("The fields constructor.");
		object.addBlock(constructor);
	}

	private void addEmptyConstructor(JavaObject object) {
		JavaConstructor constructor = new JavaConstructor(object.getType());
		constructor.getComment().line("The empty constructor.");
		object.addBlock(constructor);
	}

	private void addBuild(JavaObject object) {
		BuildMethod method = new BuildMethod(bean);
		object.addBlock(method);
	}

	private void addFields(JavaObject object) {
		for (JavaModelBeanField field : bean.getFieldList()) {
			addField(object, field);
		}
	}

	private void addField(JavaObject object, JavaModelBeanField beanField) {
		IJavaType type = beanField.getType();
		JavaField field = new JavaField(beanField.getName(), type);
		field.setStandardComment();

		setValue(beanField, type, field);
		object.addBlock(field);
	}

	private void setValue(JavaModelBeanField beanField, IJavaType type, JavaField field) {
		if (beanField.get().hasValue()) {
			field.setAssignment(beanField.get().getValue());
		} else {
			// For complete clarity lets assign what would be the default value anyhow
			field.setAssignment(type.getDefaultValue());
		}
	}

	private void addSetters(JavaObject object) {
		for (JavaModelBeanField field : bean.getFieldList()) {
			addSetter(object, field);
		}
	}

	private void addSetter(JavaObject object, JavaModelBeanField field) {

		// Setter + argument checks
		IJavaCodeLines arguments = getArguments(field, object.getImports());
		SetterMethod method = new SetterMethod(field.getName(), field.getType(), arguments, objectType);
		object.addBlock(method);
	}

	private IJavaCodeLines getArguments(JavaModelBeanField field, IJavaTypeSet typeSet) {
		IJavaCodeLines code = new JavaCodeLines();
		getArguments(field, typeSet, code);
		return code;
	}

	private void getArguments(JavaModelBeanField field, IJavaTypeSet typeSet, IJavaCodeLines code) {
		IJavaValidator validator = getValidator(field);
		if (validator != null) {
			validator.appendMethodTo(code, field.getName(), typeSet);
		}
	}

	private IJavaValidator getValidator(JavaModelBeanField field) {
		String type = field.get().getType();
		if (type == null) {
			return null;
		}
		JavaModelValidator validator = getContext().getModelSet().getValidator(type, true);
		if (validator == null) {
			return null;
		}
		IJavaValidatorLookup lookup = getContext().getValidatorLookup();
		return lookup.getValidator(type);
	}

	private IJavaNamedTypeSet getFields() {
		if (fieldSet == null) {
			fieldSet = new JavaNamedTypeSet();
			for (JavaModelBeanField field : getBean().getFieldList()) {
				fieldSet.add(field.getName(), field.getType());
			}
		}
		return fieldSet;
	}

}
