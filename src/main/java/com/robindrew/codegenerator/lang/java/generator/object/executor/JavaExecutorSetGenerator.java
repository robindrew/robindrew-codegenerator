package com.robindrew.codegenerator.lang.java.generator.object.executor;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;

import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorLocator;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorSet;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.executor.JavaModelExecutorSet;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.executor.method.ExecutorSetConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.executor.method.ExecutorSetMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaExecutorSetGenerator extends JavaObjectGenerator {

	private final JavaModelExecutorSet executorSet;

	private IJavaType classType = null;
	private IJavaType interfaceType = null;

	public JavaExecutorSetGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelExecutorSet executorSet) {
		super(setup, context, model, executorSet.get());
		this.executorSet = executorSet;
	}

	public JavaModelExecutorSet getExecutorSet() {
		return executorSet;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this factory as a type
		classType = registerJavaType(getExecutorSet().getName(), CLASS);
		interfaceType = registerJavaType("I" + getExecutorSet().getName(), INTERFACE);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	private void addExtends(JavaObject object) {
		boolean isInterface = object.getType().isInterface();
		for (JavaModelExtends eextends : getExecutorSet().getExtendsList()) {
			IJavaType type = resolve(eextends.get().getType());
			eextends.setType(type);
			if (type.isInterface() == isInterface) {
				object.addExtends(type);
			}
		}
	}

	@Override
	public void generate() {

		// For each executor we generate a class and interface
		JavaObject objectInterface = new JavaObject(interfaceType);
		objectInterface.addExtends(resolve(IBeanExecutorSet.class));
		addExtends(objectInterface);

		JavaObject object = new JavaObject(classType);
		object.addExtends(interfaceType);
		addExtends(object);

		// The service field
		JavaField field = new JavaField("locator", IBeanExecutorLocator.class);
		field.setFinal(true);
		object.addBlock(field);

		// The service constructor
		object.addBlock(new ExecutorSetConstructor(executorSet.getName()));
		object.addBlock(new GetterMethod("locator", IBeanExecutorLocator.class));

		// Generate a method per bean
		for (JavaModelBean bean : getModel().getBeanList()) {
			if (bean.isExecutable()) {
				object.addBlock(new ExecutorSetMethod(bean, false));
				objectInterface.addBlock(new ExecutorSetMethod(bean, true));
			}
		}

		// Done!
		write(objectInterface);
		write(object);
	}

}
