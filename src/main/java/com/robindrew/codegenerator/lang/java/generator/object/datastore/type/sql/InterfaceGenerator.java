package com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;

import com.robindrew.codegenerator.api.sql.builder.ISqlTable;
import com.robindrew.codegenerator.api.sql.executor.ISqlExecutor;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.TypeGenerator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.ReadWriteLockField;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetterMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStore;

public class InterfaceGenerator extends TypeGenerator {

	private IJavaType type;

	public InterfaceGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		type = registerJavaType("ISql" + getDataStore().getName(), INTERFACE);
	}

	@Override
	public void generate() {
		ModelDataStore model = getDataStore().get();
		String interfaceName = getName(model.getInterfaceName(), model.getName());
		IJavaType interfaceType = resolve(interfaceName);

		JavaObject object = new JavaObject(type);
		object.addExtends(resolve(ISqlTable.class, getElementBean().getInterfaceType()));

		// Fields
		JavaField tableField = new JavaField("table", String.class).setVolatile(true);
		JavaField databaseField = new JavaField("database", String.class).setVolatile(true);
		JavaField executorField = new JavaField("executor", ISqlExecutor.class).setVolatile(true);
		JavaField lockField = new ReadWriteLockField().setFinal(true);

		object.addBlock(new GetterMethod(lockField).setInterface(true));
		object.addBlock(new GetterMethod(tableField).setInterface(true));
		object.addBlock(new GetterMethod(databaseField).setInterface(true));
		object.addBlock(new GetterMethod(executorField).setInterface(true));
		object.addBlock(new SetterMethod(tableField).setInterface(true));
		object.addBlock(new SetterMethod(databaseField).setInterface(true));
		object.addBlock(new SetterMethod(executorField).setInterface(true));

		object.addExtends(interfaceType);
		write(object);
	}

}
