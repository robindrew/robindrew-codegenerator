package com.robindrew.codegenerator.lang.java.generator.object.delegate;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.lang.reflect.Method;

import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerationException;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateConstructor;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateField;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.delegate.ModelDelegate;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaDelegateGenerator extends JavaObjectGenerator {

	private final ModelDelegate delegate;

	private IJavaType objectType = null;
	private Class<?> type = null;

	public JavaDelegateGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelDelegate delegate) {
		super(setup, context, model, delegate);
		if (delegate == null) {
			throw new NullPointerException("delegate");
		}
		this.delegate = delegate;
	}

	public ModelDelegate getDelegate() {
		return delegate;
	}

	@Override
	public void registerPrimaryTypes() {
		objectType = registerJavaType(getDelegate().getName(), CLASS);

		// Delegates can only be generated for actual class definitions
		IJavaType type = resolve(delegate.getType());
		if (!(type instanceof JavaTypeClass)) {
			throw new JavaModelGenerationException("Type is not an existing class: " + type);
		}
		JavaTypeClass<?> classType = (JavaTypeClass<?>) type;
		this.type = classType.getType();
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {

		// For each comparator we generate a class
		JavaObject object = new JavaObject(objectType);
		object.addExtends(resolve(type));

		// Delegate Field
		addFields(object);

		// Delegate Constructor
		addConstructor(object);

		// Delegate Method
		addBlocks(object);

		// Done!
		write(object);

	}

	private void addConstructor(JavaObject object) {
		String name = object.getType().getSimpleName();
		DelegateField field = new DelegateField(resolve(type));
		object.addBlock(new DelegateConstructor(name, field));
	}

	private void addFields(JavaObject object) {
		DelegateField field = new DelegateField(resolve(type));
		object.addBlock(field);
	}

	private void addBlocks(JavaObject object) {
		Method[] methods = type.getDeclaredMethods();
		for (Method method : methods) {
			DelegateMethod block = new DelegateMethod(method);
			block.setDelegateContents();
			object.addBlock(block);
		}
	}
}
