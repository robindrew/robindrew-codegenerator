package com.robindrew.codegenerator.lang.java.generator.object.eenum;

import static com.robindrew.codegenerator.lang.java.type.block.visibility.JavaVisibility.PRIVATE;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.ENUM;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnumConstant;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnumField;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.eenum.JavaEnumField;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetFieldsBlock;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.object.IJavaObject;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaEnumGenerator extends JavaObjectGenerator {

	private final JavaModelEnum eenum;

	public JavaEnumGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelEnum eenum) {
		super(setup, context, model, eenum.get());
		if (eenum.getConstantList().isEmpty()) {
			throw new IllegalArgumentException("enum has no constants: " + eenum.getName());
		}
		this.eenum = eenum;

	}

	public JavaModelEnum getEnum() {
		return eenum;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this enum as a type
		IJavaType type = registerJavaType(getEnum().getName(), ENUM);
		eenum.setType(type);
	}

	@Override
	public void verifyReferencedTypes() {
		// Check the constants reference known types
		for (JavaModelEnumConstant constant : getEnum().getConstantList()) {
			verifyReferencedType(constant);
		}
	}

	private void verifyReferencedType(JavaModelEnumConstant constant) {
		for (JavaModelEnumField field : constant.getFieldList()) {
			field.setType(resolve(field.get().getType()));
		}
	}

	@Override
	public void generate() {

		// For each enum we generate an interface and a class
		IJavaType type = resolve(getEnum().getName());
		JavaObject object = new JavaObject(type);

		// Extends
		addExtends(object);

		// Add the constants
		IJavaNamedTypeSet fields = addConstants(object);

		// Add the fields
		if (!fields.isEmpty()) {
			addFields(object, fields);
			addConstructor(object, fields);
			addGetters(object, fields);
		}

		// Done!
		write(object);
	}

	private void addExtends(JavaObject object) {
		for (JavaModelExtends eextends : getEnum().getExtendsList()) {
			IJavaType type = resolve(eextends.get().getType());
			eextends.setType(type);
			object.addExtends(type);
		}
	}

	private void addGetters(JavaObject object, IJavaNamedTypeSet fields) {
		for (IJavaNamedType field : fields) {
			GetterMethod getter = new GetterMethod(field);
			object.addBlock(getter);
		}
	}

	private void addConstructor(JavaObject object, IJavaNamedTypeSet fields) {
		IJavaMethod constructor = new JavaConstructor(object.getType());
		new SetFieldsBlock(fields, true).populate(constructor);
		constructor.setVisibility(PRIVATE);
		object.addBlock(constructor);
	}

	private void addFields(JavaObject object, IJavaNamedTypeSet fields) {
		for (IJavaNamedType field : fields) {
			JavaField javaField = new JavaField(field);
			javaField.setFinal(true);
			javaField.setStandardComment();
			object.addBlock(javaField);
		}
	}

	private IJavaNamedTypeSet addConstants(IJavaObject object) {
		IJavaTypeSet references = new JavaTypeSet();
		IJavaNamedTypeSet field = new JavaNamedTypeSet(references);

		for (JavaModelEnumConstant constant : getEnum().getConstantList()) {
			addConstant(object, constant, field);
		}
		return field;
	}

	private void addConstant(IJavaObject object, JavaModelEnumConstant constant, IJavaNamedTypeSet parameters) {
		JavaEnumField field = new JavaEnumField(constant.getName());

		boolean add = parameters.isEmpty();
		for (JavaModelEnumField enumField : constant.getFieldList()) {
			addField(field, enumField);
			addParameter(enumField, parameters, add);
		}

		field.setStandardComment();
		object.addBlock(field);
	}

	private void addParameter(JavaModelEnumField enumField, IJavaNamedTypeSet parameters, boolean add) {
		IJavaNamedType parameter = new JavaNamedType(enumField.getName(), enumField.getType());

		// Just add parameters from first field
		if (add) {
			parameters.add(parameter);
			return;
		}

		// Verify parameters from all other fields
		if (!parameters.contains(parameter)) {
			throw new IllegalStateException("Enum field declarations do not match");
		}
	}

	private void addField(JavaEnumField field, JavaModelEnumField enumField) {
		field.addParameter(enumField.get().getValue());
	}

}
