package com.robindrew.codegenerator.lang.java.generator.object.datastore;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.api.datastore.IDataStore;
import com.robindrew.codegenerator.api.datastore.IDataView;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelMethod;
import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelMethodParameter;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.JavaGeneratorSet;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.CachedPersisterGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.ConcurrentGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.CopyGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.DelegateGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.MapGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.WriteBehindGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql.DerbyGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql.H2Generator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql.HsqldbGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql.InterfaceGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql.MysqlGenerator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.common.ModelExtends;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStore;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStoreKey;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStoreRow;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaDataStoreGenerator extends JavaObjectGenerator implements IJavaDataStoreGenerator {

	private final JavaModelDataStore dataStore;
	private final JavaGeneratorSet generatorSet = new JavaGeneratorSet();

	private IJavaNamedTypeSet keyFields = new JavaNamedTypeSet();

	public JavaDataStoreGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelDataStore dataStore) {
		super(setup, context, model, dataStore.get());
		this.dataStore = dataStore;

		// Child generators
		populateGeneratorSet();
	}

	private void populateGeneratorSet() {
		if (dataStore.get().isDelegate()) {
			generatorSet.add(new DelegateGenerator(this));
		}
		if (dataStore.get().isConcurrent()) {
			generatorSet.add(new ConcurrentGenerator(this));
		}
		if (dataStore.get().isMap()) {
			generatorSet.add(new MapGenerator(this));
		}
		if (dataStore.get().isCopy()) {
			generatorSet.add(new CopyGenerator(this));
		}
		if (dataStore.get().isWriteBehind()) {
			generatorSet.add(new WriteBehindGenerator(this));
		}
		if (dataStore.get().isCachedPersister()) {
			generatorSet.add(new CachedPersisterGenerator(this));
		}
		if (dataStore.get().isSql()) {
			generatorSet.add(new InterfaceGenerator(this));
			generatorSet.add(new DerbyGenerator(this));
			generatorSet.add(new HsqldbGenerator(this));
			generatorSet.add(new H2Generator(this));
			generatorSet.add(new MysqlGenerator(this));
		}
	}

	public JavaModelDataStore getDataStore() {
		return dataStore;
	}

	public IJavaNamedTypeSet getKeyFields() {
		return keyFields;
	}

	@Override
	public void registerPrimaryTypes() {
		ModelDataStore model = getDataStore().get();
		String interfaceName = getName(model.getInterfaceName(), model.getName());
		String readOnlyName = getName(model.getReadOnlyName(), model.getName());

		// Base interface type
		IJavaType interfaceType = registerJavaType(interfaceName, INTERFACE);
		IJavaType readOnlyType = registerJavaType(readOnlyName, INTERFACE);
		dataStore.setInterfaceTypes(interfaceType, readOnlyType);

		// Children
		generatorSet.registerPrimaryTypes();
	}

	@Override
	public void registerSecondaryTypes() {

		// Lookup the secondary type
		JavaModelBean elementBean = getBean(dataStore.get().getElement());
		JavaModelBean keyBean = getBean(dataStore.get().getKey());
		List<JavaModelBean> rowBeans = getRowBeans();
		List<JavaModelDataStoreKey> keyBeans = getKeyBeans();

		dataStore.setBeans(elementBean, keyBean, rowBeans, keyBeans);
		dataStore.setResolver(getContext().getResolver());

		// Method
		for (JavaModelMethod method : dataStore.getMethodList()) {
			method.setType(resolve(method.get().getReturnType()));

			// Parameters
			for (JavaModelMethodParameter parameter : method.getParameterList()) {
				parameter.setType(resolve(parameter.get().getType()));
			}
		}

		// Children
		generatorSet.registerSecondaryTypes();
	}

	private List<JavaModelDataStoreKey> getKeyBeans() {
		List<JavaModelDataStoreKey> list = new ArrayList<JavaModelDataStoreKey>();
		for (ModelDataStoreKey key : dataStore.get().getKeyList()) {
			JavaModelBean bean = getBean(key.getType());
			list.add(new JavaModelDataStoreKey(key, bean));
		}
		return list;
	}

	private JavaModelBean getBean(String name) {
		IJavaType type = resolve(name);
		return getContext().getModelSet().getBean(type, false);
	}

	private List<JavaModelBean> getRowBeans() {
		List<JavaModelBean> list = new ArrayList<JavaModelBean>();
		for (ModelDataStoreRow row : dataStore.get().getRowList()) {
			JavaModelBean bean = getBean(row.getType());
			list.add(bean);
		}
		return list;
	}

	public void addMethods(JavaObject object, boolean isInterface) {
		for (JavaMethod method : getMethods(object, isInterface)) {
			object.addBlock(method);
		}
	}

	public List<JavaMethod> getMethods(JavaObject object, boolean isInterface) {
		List<JavaMethod> list = new ArrayList<>();
		for (JavaModelMethod method : dataStore.getMethodList()) {
			list.add(getMethod(object, method, isInterface));
		}
		return list;
	}

	private JavaMethod getMethod(JavaObject object, JavaModelMethod method, boolean isInterface) {
		JavaMethod value = new JavaMethod(method.getName(), method.getReturnType());
		for (JavaModelMethodParameter parameter : method.getParameterList()) {
			value.getParameters().add(parameter.toNamedType());
		}
		if (!isInterface) {
			if (method.getReturnValue() != null) {
				String returnValue = getMethodReturnValue(object, value.getReturnType(), method.getReturnValue());
				value.setContents(new JavaCodeLines("return " + returnValue + ";"));
			} else {
				value.setUnsupportedOperationContents();
			}
		}
		value.setInterface(isInterface);
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

		return returnValue;
	}

	@Override
	public void verifyReferencedTypes() {

		// Extends
		for (JavaModelExtends eextends : getDataStore().getExtendsList()) {
			eextends.setType(resolve(eextends.get().getType()));
		}

		// Children
		generatorSet.verifyReferencedTypes();
	}

	@Override
	public void generate() {
		generateInterface(true);
		generateInterface(false);

		// Children
		generatorSet.generate();
	}

	private void generateInterface(boolean readOnly) {
		IJavaType type = readOnly ? getDataStore().getReadOnlyInterfaceType() : getDataStore().getInterfaceType();
		JavaObject object = new JavaObject(type);
		addExtends(object, readOnly);

		// Add the methods ...
		JavaDataStoreMethods methods = new JavaDataStoreMethods(this);
		methods.addDefaultMethods(readOnly);
		methods.addInterfaceMethods();
		for (JavaMethod method : methods) {
			object.addBlock(method.setInterface(true));
		}

		// Only add methods to the base interface
		if (readOnly) {
			addMethods(object, true);
		}

		// Done
		write(object);
	}

	private void addExtends(JavaObject object, boolean readOnly) {
		object.addExtends(getDataStoreInterface(readOnly));
		if (!readOnly) {
			object.addExtends(getDataStore().getReadOnlyInterfaceType());
		}
		boolean isInterface = object.getType().isInterface();
		for (ModelExtends eextends : getDataStore().get().getExtendsList()) {
			IJavaType type = resolve(eextends.getType());
			if (type.isInterface() == isInterface) {
				object.addExtends(type);
			}
		}
	}

	private IJavaType getDataStoreInterface(boolean view) {
		IJavaType type = resolve(view ? IDataView.class : IDataStore.class, 1);
		return new JavaTypeWithGenerics(type, dataStore.getElementBean().getInterfaceType());
	}
}
