package com.robindrew.codegenerator.lang.java.generator.object.validator;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.generator.object.JavaGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.IJavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.ObjectValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.StringValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.collection.CollectionValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.collection.ListValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.collection.MapValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.collection.SetValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.number.ByteValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.number.DoubleValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.number.FloatValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.number.IntegerValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.number.LongValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.number.ShortValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveBooleanValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveByteValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveDoubleValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveFloatValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveIntValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveLongValidator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive.PrimitiveShortValidator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaValidatorGenerator extends JavaGenerator {

	private final JavaModelValidator validator;

	public JavaValidatorGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelValidator validator) {
		super(setup, context, model);
		if (validator == null) {
			throw new NullPointerException("validator");
		}
		this.validator = validator;
	}

	@Override
	public void registerPrimaryTypes() {
		IJavaType type = resolve(validator.get().getType());
		validator.setType(type);

		// Register the validator as an alias to a type
		getContext().getResolver().registerAlias(type, validator.get().getName());
	}

	@Override
	public void verifyReferencedTypes() {
		IJavaValidatorLookup lookup = getContext().getValidatorLookup();

		// Types are now registered, this should be a reasonable place to create validators
		IJavaValidator validator = createValidator();
		lookup.setValidator(getName(), validator);
		this.validator.setJavaValidator(validator);
	}

	private String getName() {
		return validator.getName();
	}

	private IJavaValidator createValidator() {
		String type = validator.get().getType();

		// String
		if (type.equals("String")) {
			return new StringValidator(validator.get());
		}

		// Primitive Objects
		if (type.equals("Byte")) {
			return new ByteValidator(validator.get());
		}
		if (type.equals("Short")) {
			return new ShortValidator(validator.get());
		}
		if (type.equals("Integer")) {
			return new IntegerValidator(validator.get());
		}
		if (type.equals("Long")) {
			return new LongValidator(validator.get());
		}
		if (type.equals("Float")) {
			return new FloatValidator(validator.get());
		}
		if (type.equals("Double")) {
			return new DoubleValidator(validator.get());
		}

		// Primitives
		if (type.equals("byte")) {
			return new PrimitiveByteValidator(validator.get());
		}
		if (type.equals("short")) {
			return new PrimitiveShortValidator(validator.get());
		}
		if (type.equals("int")) {
			return new PrimitiveIntValidator(validator.get());
		}
		if (type.equals("long")) {
			return new PrimitiveLongValidator(validator.get());
		}
		if (type.equals("float")) {
			return new PrimitiveFloatValidator(validator.get());
		}
		if (type.equals("double")) {
			return new PrimitiveDoubleValidator(validator.get());
		}
		if (type.equals("boolean")) {
			return new PrimitiveBooleanValidator(validator.get());
		}

		// Collections
		if (type.equals("Collection")) {
			return new CollectionValidator(validator.get());
		}
		if (type.equals("List")) {
			return new ListValidator(validator.get());
		}
		if (type.equals("Set")) {
			return new SetValidator(validator.get());
		}
		if (type.equals("Map")) {
			return new MapValidator(validator.get());
		}

		// Unknown type
		IJavaType resolved = resolve(type);
		return new ObjectValidator(resolved, validator.get());
	}

	@Override
	public void generate() {
		// We do not generate validator objects yet, although we could in the future
	}

}
