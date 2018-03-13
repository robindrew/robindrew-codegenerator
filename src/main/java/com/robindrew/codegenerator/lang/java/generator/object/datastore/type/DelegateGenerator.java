package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreMethods;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateConstructor;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public class DelegateGenerator extends TypeGenerator {

	private IJavaType delegateType = null;

	public DelegateGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		delegateType = registerJavaType("Delegate" + getDataStore().getName(), CLASS);
	}

	@Override
	public void generate() {
		JavaObject object = new JavaObject(delegateType);
		object.addExtends(getInterfaceType());

		// Delegate field
		JavaField field = new JavaField("delegate", getInterfaceType()).setFinal(true);
		object.addBlock(field);

		// Delegate constructor
		object.addBlock(new DelegateConstructor(delegateType.getSimpleName(), field));

		// Delegate methods
		JavaDataStoreMethods methods = new JavaDataStoreMethods(this);
		methods.addDefaultMethods();
		for (JavaMethod method : methods) {
			object.addBlock(toDelegate(method));
		}

		// Done
		write(object);
	}

}
