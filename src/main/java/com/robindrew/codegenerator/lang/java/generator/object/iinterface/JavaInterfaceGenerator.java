package com.robindrew.codegenerator.lang.java.generator.object.iinterface;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;

import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerationException;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterfaceMethod;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterfaceParameter;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaInterfaceGenerator extends JavaObjectGenerator {

	private final JavaModelInterface iinterface;

	public JavaInterfaceGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelInterface iinterface) {
		super(setup, context, model, iinterface.get());
		this.iinterface = iinterface;

	}

	public JavaModelInterface getInterface() {
		return iinterface;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this enum as a type
		IJavaType type = registerJavaType(getInterface().getName(), INTERFACE);
		iinterface.setType(type);
	}

	@Override
	public void verifyReferencedTypes() {
		try {

			// Extends
			for (JavaModelExtends eextends : getInterface().getExtendsList()) {
				eextends.setType(resolve(eextends.get().getType()));
			}

			// Method
			for (JavaModelInterfaceMethod method : getInterface().getMethodList()) {
				method.setType(resolve(method.get().getReturnType()));

				// Parameters
				for (JavaModelInterfaceParameter parameter : method.getParameterList()) {
					parameter.setType(resolve(parameter.get().getType()));
				}
			}

		} catch (Exception e) {
			throw new JavaModelGenerationException("Exception generating interface: " + iinterface.getName(), e);
		}
	}

	@Override
	public void generate() {

		// For each enum we generate an interface and a class
		IJavaType type = resolve(getInterface().getName());
		JavaObject object = new JavaObject(type);

		// Extends
		addExtends(object);

		// Methods
		addMethods(object);

		// Done!
		write(object);
	}

	private void addExtends(JavaObject object) {
		for (JavaModelExtends eextends : getInterface().getExtendsList()) {
			IJavaType type = eextends.getType();
			object.addExtends(type);
		}
	}

	private void addMethods(JavaObject object) {
		for (JavaModelInterfaceMethod method : getInterface().getMethodList()) {
			addMethod(object, method);
		}
	}

	private void addMethod(JavaObject object, JavaModelInterfaceMethod iMethod) {
		JavaMethod method = new JavaMethod(iMethod.getName(), iMethod.getReturnType());
		method.setInterface(true);
		for (JavaModelInterfaceParameter parameter : iMethod.getParameterList()) {
			addParameter(method, parameter);
		}
		object.addBlock(method);
	}

	private void addParameter(JavaMethod method, JavaModelInterfaceParameter parameter) {
		method.getParameters().add(parameter.getName(), parameter.getType());
	}

}
