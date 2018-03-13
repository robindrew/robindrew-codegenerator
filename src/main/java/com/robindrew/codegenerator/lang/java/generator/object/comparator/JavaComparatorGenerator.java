package com.robindrew.codegenerator.lang.java.generator.object.comparator;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.compare.CompareMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.tostring.ToStringBuilderMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.tostring.ToStringMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.comparator.ModelComparator;
import com.robindrew.codegenerator.model.object.comparator.ModelComparatorField;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaComparatorGenerator extends JavaObjectGenerator {

	private final ModelComparator comparator;

	private IJavaType objectType = null;
	private IJavaType compareType = null;
	private IJavaNamedTypeSet fields = null;

	public JavaComparatorGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelComparator comparator) {
		super(setup, context, model, comparator);
		if (comparator == null) {
			throw new NullPointerException("comparator");
		}
		if (comparator.getFieldList().isEmpty()) {
			throw new IllegalArgumentException("comparator has no fields: " + comparator.getName());
		}
		this.comparator = comparator;
	}

	public ModelComparator getComparator() {
		return comparator;
	}

	private IJavaNamedTypeSet getFields() {
		if (fields == null) {
			fields = new JavaNamedTypeSet();
			if (getComparator().isReverse()) {
				fields.add("reverse", boolean.class);
			}
			if (getComparator().isSwap()) {
				fields.add("swap", boolean.class);
			}
		}
		return fields;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this comparator as a type
		objectType = registerJavaType(getComparator().getName(), CLASS);
	}

	@Override
	public void verifyReferencedTypes() {
		compareType = resolve(getComparator().getType());
	}

	@Override
	public void generate() {

		// For each comparator we generate a class
		JavaObject object = new JavaObject(objectType);
		object.addExtends(getComparatorInterface());

		// Method
		addBlocks(object);

		// Done!
		write(object);
	}

	private void addBlocks(JavaObject object) {

		// Fields
		addFields(object);

		// Getters
		addGetters(object);

		// Setters
		addSetters(object);

		// toString()
		addToString(object);

		// Method
		addCompareMethod(object);
	}

	private void addFields(JavaObject object) {
		for (IJavaNamedType named : getFields()) {
			JavaField field = new JavaField(named);
			field.setStandardComment();
			field.setAssignment(field.getType().getDefaultValue());
			object.addBlock(field);
		}
	}

	private void addGetters(JavaObject object) {
		for (IJavaNamedType field : getFields()) {
			GetterMethod method = new GetterMethod(field);
			object.addBlock(method);
		}
	}

	private void addSetters(JavaObject object) {
		for (IJavaNamedType field : getFields()) {
			SetterMethod method = new SetterMethod(field);
			object.addBlock(method);
		}
	}

	private void addToString(JavaObject object) {
		if (getFields().isEmpty()) {
			// Reverse and swap fields disabled
			return;
		}
		ToStringMethod method = new ToStringBuilderMethod(getFields());
		object.addBlock(method);
	}

	private void addCompareMethod(JavaObject object) {
		List<IJavaName> list = new ArrayList<IJavaName>();
		for (ModelComparatorField field : getComparator().getFieldList()) {
			IJavaName name = new JavaName(field.getName());
			list.add(name);
		}

		boolean reverse = getComparator().isReverse();
		boolean swap = getComparator().isSwap();
		CompareMethod method = new CompareMethod(compareType, list, reverse, swap);
		object.addBlock(method);
	}

	private IJavaType getComparatorInterface() {
		return new JavaTypeWithGenerics(resolve(Comparator.class), compareType);
	}

}
