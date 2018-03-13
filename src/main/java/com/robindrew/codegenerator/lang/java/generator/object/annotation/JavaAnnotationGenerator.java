package com.robindrew.codegenerator.lang.java.generator.object.annotation;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.ANNOTATION;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.annotation.field.IJavaAnnotationField;
import com.robindrew.codegenerator.lang.java.type.block.annotation.field.JavaAnnotationField;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.annotation.ModelAnnotation;
import com.robindrew.codegenerator.model.object.annotation.ModelAnnotationField;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaAnnotationGenerator extends JavaObjectGenerator {

	private final ModelAnnotation annotation;

	private IJavaType objectType = null;

	public JavaAnnotationGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelAnnotation annotation) {
		super(setup, context, model, annotation);
		if (annotation == null) {
			throw new NullPointerException("annotation");
		}
		this.annotation = annotation;
	}

	public ModelAnnotation getAnnotation() {
		return annotation;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this annotation as a type
		objectType = registerJavaType(getAnnotation().getName(), ANNOTATION);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {

		// For each comparator we generate a class
		JavaObject object = new JavaObject(objectType);

		// Annotation Method
		addBlocks(object);

		// Done!
		write(object);
	}

	private void addBlocks(JavaObject object) {
		for (ModelAnnotationField field : getAnnotation().getFieldList()) {
			addBlock(object, field);
		}
	}

	private void addBlock(JavaObject object, ModelAnnotationField field) {
		IJavaName name = new JavaName(field.getName());
		IJavaType type = resolve(field.getType());
		IJavaAnnotationField annotation = new JavaAnnotationField(name, type);
		if (field.hasDefaultValue()) {
			annotation.setDefaultValue(field.getDefaultValue());
		}
		object.addBlock(annotation);
	}
}
