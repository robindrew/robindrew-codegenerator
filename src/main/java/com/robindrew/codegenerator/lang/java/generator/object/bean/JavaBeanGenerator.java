package com.robindrew.codegenerator.lang.java.generator.object.bean;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;

import java.util.Set;

import com.robindrew.codegenerator.api.bean.IBean;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerationException;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanAnnotation;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanConstructor;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanMethod;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanParameter;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.field.ConstantField;
import com.robindrew.codegenerator.lang.java.generator.object.field.SerialVersionUidField;
import com.robindrew.codegenerator.lang.java.generator.object.lookup.IJavaMethodLookup;
import com.robindrew.codegenerator.lang.java.generator.object.lookup.JavaMethodLookup;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.data.method.GetSerializationIdMethod;
import com.robindrew.codegenerator.lang.java.generator.object.validator.IJavaValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.IJavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.annotation.JavaAnnotation;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.compare.CompareToMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.equals.EqualsMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.hashcode.HashCodeMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.CopySetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetFieldsBlock;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.tostring.ToStringBuilderMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.tostring.ToStringMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaBeanGenerator extends JavaObjectGenerator {

	private final JavaModelBean bean;

	private IJavaType classType = null;
	private IJavaType interfaceType = null;
	private IJavaNamedTypeSet fieldSet = null;
	private IJavaNamedTypeSet identityFieldSet = null;

	public JavaBeanGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelBean bean) {
		super(setup, context, model, bean.get());
		this.bean = bean;
	}

	public JavaModelBean getBean() {
		return bean;
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

	private IJavaNamedTypeSet getIdentityFields() {
		if (identityFieldSet == null) {
			identityFieldSet = new JavaNamedTypeSet();
			for (JavaModelBeanField field : getBean().getFieldList()) {
				if (field.get().isIdentity()) {
					identityFieldSet.add(field.getName(), field.getType());
				}
			}
			// If no fields are marked as identity fields, all of them are ...
			if (identityFieldSet.isEmpty()) {
				identityFieldSet = getFields();
			}
		}
		return identityFieldSet;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this bean as a type
		classType = registerJavaType(getBean().getName(), CLASS);
		interfaceType = registerJavaType("I" + getBean().getName(), INTERFACE);

		// Provide lookup
		bean.setTypes(classType, interfaceType);

		setExtends(classType, interfaceType);
	}

	@Override
	public void verifyReferencedTypes() {

		// Set field validators
		for (JavaModelBeanField field : getBean().getFieldList()) {
			String type = field.get().getType();
			if (type != null) {
				JavaModelValidator validator = getContext().getModelSet().getValidator(type, true);
				if (validator != null) {
					field.setValidator(validator);
					field.setType(validator.getType());
				}
			}
		}
	}

	@Override
	public void registerSecondaryTypes() {
		try {

			// Set field validators
			for (JavaModelBeanField field : getBean().getFieldList()) {
				String type = field.get().getType();
				if (type != null) {
					JavaModelValidator validator = getContext().getModelSet().getValidator(type, true);
					if (validator != null) {
						field.setValidator(validator);
						field.setType(validator.getType());
					}
				}
			}

			// Resolve field types
			for (JavaModelBeanField field : getBean().getFieldList()) {
				if (!field.hasType()) {
					IJavaType type = resolve(field.get().getType());
					field.setType(type);
				}

				// Resolve annotation types
				for (JavaModelBeanAnnotation annotation : field.getAnnotationList()) {
					IJavaType type = resolve(annotation.get().getType());
					annotation.setType(type);
				}
			}

			// Resolve extends types
			for (JavaModelExtends eextends : getBean().getExtendsList()) {
				IJavaType type = resolve(eextends.get().getType());
				eextends.setType(type);
				if (type.isInterface()) {
					setExtends(classType, type);
					bean.getExtendsSet().add(type);
				}
			}

			// Resolve constructor types
			for (JavaModelBeanConstructor constructor : getBean().getConstructorList()) {
				IJavaType type = resolve(constructor.get().getType());
				constructor.setType(type);
			}

			// Resolve command return type
			String returnType = bean.get().getReturnType();
			if (returnType != null) {
				bean.setReturnType(resolve(returnType));
			}

			// Method
			for (JavaModelBeanMethod method : bean.getMethodList()) {
				method.setType(resolve(method.get().getReturnType()));

				// Parameters
				for (JavaModelBeanParameter parameter : method.getParameterList()) {
					parameter.setType(resolve(parameter.get().getType()));
				}
			}
		} catch (Exception e) {
			throw new JavaModelGenerationException("Failed to register secondary types for bean: " + bean.getName(), e);
		}
	}

	private boolean isImmutable() {
		return getBean().isImmutable();
	}

	@Override
	public void generate() {

		// For each bean we generate an interface
		JavaObject objectInterface = new JavaObject(interfaceType);
		if (hasFields() && getBean().get().isCompare()) {
			objectInterface.addExtends(getComparableInterface());
		}
		if (bean.isExecutable()) {
			IJavaType type = resolve(IExecutableBean.class, bean.getReturnType());
			objectInterface.addExtends(type);
		} else {
			IJavaType type = resolve(IBean.class);
			objectInterface.addExtends(type);
		}
		addExtends(objectInterface);

		// For each bean we generate a class
		JavaObject object = new JavaObject(classType);
		object.addExtends(interfaceType);
		addExtends(object);

		// Add the fields, getters and setter methods
		addBlocks(object, objectInterface);

		// Done!
		write(object);
		write(objectInterface);
	}

	private void addExtends(JavaObject object) {
		boolean isInterface = object.getType().isInterface();
		for (JavaModelExtends eextends : getBean().getExtendsList()) {
			IJavaType type = eextends.getType();
			if (type.isInterface() == isInterface) {
				object.addExtends(type);
			}
		}
	}

	private IJavaType getComparableInterface() {
		return new JavaTypeWithGenerics(resolve(Comparable.class), interfaceType);
	}

	private void addBlocks(JavaObject object, JavaObject objectInterface) {

		// Fields
		addFields(object, objectInterface);

		// Constructors
		addConstructors(object);

		// Serialization
		addSerializationMethod(object, objectInterface);

		// Getters
		addGetters(object, objectInterface);

		// Setters
		addSetters(object, objectInterface);

		// hashCode()
		addHashCode(object);

		// equals()
		addEquals(object, objectInterface);

		// toString()
		addToString(object);

		// compareTo()
		addCompareTo(object, objectInterface);

		// Bean methods
		addMethods(object);

		// Interface methods
		addInterfaceMethods(object);
	}

	private void addMethods(JavaObject object) {
		for (JavaModelBeanMethod method : getBean().getMethodList()) {
			object.addBlock(getMethod(object, method));
		}
	}

	private JavaMethod getMethod(JavaObject object, JavaModelBeanMethod method) {
		JavaMethod value = new JavaMethod(method.getName(), method.getReturnType());
		for (JavaModelBeanParameter parameter : method.getParameterList()) {
			value.getParameters().add(parameter.toNamedType());
		}
		if (method.getReturnValue() != null) {
			String returnValue = getMethodReturnValue(object, value.getReturnType(), method.getReturnValue());
			value.setContents(new JavaCodeLines("return " + returnValue + ";"));
		} else {
			value.setUnsupportedOperationContents();
		}
		return value;
	}

	private String getMethodReturnValue(JavaObject object, IJavaType returnType, String returnValue) {

		// String return value
		if (returnType.isType(String.class)) {
			return "\"" + returnValue + "\"";
		}

		// Enum return value
		if (returnType.isEnum()) {
			return returnType.getSimpleName() + "." + returnValue;
		}

		// Guess! - works but not necessary (maybe)
		// int dotIndex = returnValue.indexOf(".");
		// if (dotIndex != -1) {
		// String prefix = returnValue.substring(0, dotIndex);
		// try {
		// IJavaType type = getContext().getResolver().resolveJavaType(prefix);
		// object.addImport(type);
		// } catch (Exception e) {
		// log.warn("Enable to guess type from return value: " + returnValue);
		// }
		// }
		return returnValue;
	}

	private void addSerializationMethod(JavaObject object, JavaObject objectInterface) {
		object.addBlock(new GetSerializationIdMethod().setOverride());
		objectInterface.addBlock(new GetSerializationIdMethod().setInterface(true));
	}

	private void addInterfaceMethods(JavaObject object) {
		IJavaMethodLookup lookup = new JavaMethodLookup(getContext().getModelSet());
		Set<JavaMethod> methods = lookup.getInterfaceMethodSet(bean.getExtendsList());
		for (JavaMethod method : methods) {
			Set<IJavaClassBlock> blocks = object.getBlocks();
			if (!contains(blocks, method)) {
				object.addBlock(method.setOverride());
			}
		}
	}

	private boolean contains(Set<IJavaClassBlock> blocks, JavaMethod method) {
		for (IJavaClassBlock block : blocks) {
			if (block instanceof IJavaMethod) {
				if (method.matches((IJavaMethod) block)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasFields() {
		return !getBean().getFieldList().isEmpty();
	}

	private void addCompareTo(JavaObject object, JavaObject objectInterface) {
		if (hasFields() && getBean().get().isCompare()) {
			CompareToMethod method = new CompareToMethod(objectInterface.getType(), getIdentityFields());
			object.addBlock(method);
		}
	}

	private void addToString(JavaObject object) {
		// To string all fields rather than just identity
		ToStringMethod method = new ToStringBuilderMethod(getFields());
		object.addBlock(method);
	}

	private void addHashCode(JavaObject object) {
		HashCodeMethod method = new HashCodeMethod(getIdentityFields());
		object.addBlock(method);
	}

	private void addEquals(JavaObject object, JavaObject objectInterface) {
		EqualsMethod method = new EqualsMethod(objectInterface.getType(), getIdentityFields());
		object.addBlock(method);
	}

	private void addConstructors(JavaObject object) {

		// Empty constructor
		addEmptyConstructor(object);

		// Types constructor
		addTypesConstructor(object);

		// Clone constructor
		addCloneConstructor(object, interfaceType);

		// Clone constructors
		for (JavaModelBeanConstructor constructor : getBean().getConstructorList()) {
			IJavaType type = constructor.getType();
			addCloneConstructor(object, type);
		}
	}

	private void addCloneConstructor(JavaObject object, IJavaType type) {
		if (getFields().isEmpty()) {
			return;
		}

		boolean direct = isImmutable();
		IJavaNamedTypeSet fields = getFields();
		IJavaMethod constructor = new JavaConstructor(object.getType());
		new SetFieldsBlock(fields, direct).populate(constructor, new JavaNamedType("clone", type));
		constructor.getComment().line("The clone constructor.");
		object.addBlock(constructor);
	}

	private void addTypesConstructor(JavaObject object) {
		if (getFields().isEmpty()) {
			return;
		}

		boolean direct = isImmutable();
		IJavaNamedTypeSet fields = getFields();
		IJavaMethod constructor = new JavaConstructor(object.getType());
		constructor.getParameters().addAll(fields);
		IJavaCodeLines code = new JavaCodeLines();

		// No setters used, we need to perform checks in constructor directly
		if (direct) {
			for (JavaModelBeanField field : getBean().getFieldList()) {
				getArguments(field, object.getImports(), code);
			}
		}

		new SetFieldsBlock(fields, direct).populate(code);
		constructor.setContents(code);
		constructor.getComment().line("The fields constructor.");
		object.addBlock(constructor);
	}

	private void addEmptyConstructor(JavaObject object) {
		if (isImmutable()) {
			return;
		}

		JavaConstructor constructor = new JavaConstructor(object.getType());
		constructor.getComment().line("The empty constructor.");
		object.addBlock(constructor);
	}

	private void addSetters(JavaObject object, JavaObject objectInterface) {
		for (JavaModelBeanField field : getBean().getFieldList()) {
			addSetter(object, objectInterface, field);
		}
	}

	private void addSetter(JavaObject object, JavaObject objectInterface, JavaModelBeanField field) {

		if (isImmutable()) {

			// Setter + argument checks
			IJavaCodeLines arguments = getArguments(field, object.getImports());
			CopySetterMethod method = new CopySetterMethod(field.toNamedType(), interfaceType, classType, getBean().getFields(), arguments);
			method.setOverride();
			object.addBlock(method);

			// Interface setter
			method = new CopySetterMethod(field.toNamedType(), interfaceType, classType, getBean().getFields());
			method.setInterface(true);
			objectInterface.addBlock(method);

		} else {

			// Setter + argument checks
			IJavaCodeLines arguments = getArguments(field, object.getImports());
			SetterMethod method = new SetterMethod(field.getName(), field.getType(), arguments);
			method.setOverride();
			object.addBlock(method);

			// Interface setter
			method = new SetterMethod(field.getName(), field.getType());
			method.setInterface(true);
			objectInterface.addBlock(method);
		}

	}

	private void addGetters(JavaObject object, JavaObject objectInterface) {
		for (JavaModelBeanField field : getBean().getFieldList()) {

			// Getter + arguments?
			GetterMethod method = new GetterMethod(field.getName(), field.getType());
			method.setOverride();
			object.addBlock(method);

			// Interface getter
			method = new GetterMethod(field.getName(), field.getType());
			method.setInterface(true);
			objectInterface.addBlock(method);
		}
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

	private void addSerialVersionUidField(JavaObject object) {
		Long version = getBean().get().getSerialVersionUid();
		if (version != null) {
			SerialVersionUidField field = new SerialVersionUidField(version);
			object.addBlock(field);
		}
	}

	private void addFields(JavaObject object, JavaObject objectInterface) {
		addStaticIdField(objectInterface);
		addSerialVersionUidField(object);
		for (JavaModelBeanField field : getBean().getFieldList()) {
			addField(object, field);
		}
	}

	private void addStaticIdField(JavaObject object) {
		ConstantField field = new ConstantField(GetSerializationIdMethod.CONSTANT_NAME, int.class);
		field.setInterface(true);
		field.setAssignment(getBean().get().getId());
		object.addBlock(field);
	}

	private void addField(JavaObject object, JavaModelBeanField beanField) {
		IJavaType type = beanField.getType();
		JavaField field = new JavaField(beanField.getName(), type);
		field.setFinal(isImmutable());
		field.setStandardComment();

		addAnnotations(field, beanField);

		if (!isImmutable()) {
			setValue(beanField, type, field);
		}
		object.addBlock(field);
	}

	private void addAnnotations(JavaField field, JavaModelBeanField beanField) {
		for (JavaModelBeanAnnotation annotation : beanField.getAnnotationList()) {
			if (annotation.get().isField()) {
				IJavaType type = annotation.getType();
				field.getAnnotations().add(new JavaAnnotation(type));
			}
		}
	}

	private void setValue(JavaModelBeanField beanField, IJavaType type, JavaField field) {
		if (beanField.get().hasValue()) {
			field.setAssignment(beanField.get().getValue());
		} else {
			// For complete clarity lets assign what would be the default value anyhow
			field.setAssignment(type.getDefaultValue());
		}
	}

}
