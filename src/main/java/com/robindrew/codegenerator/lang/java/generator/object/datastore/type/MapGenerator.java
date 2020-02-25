package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.IsEmptyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SizeMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapAddAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapAddAutoMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapAddMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapClearMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapContainsRowMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapContainsColumnMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapContainsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapCreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapDestroyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetByBeanMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetByKeyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetKeyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetListBetweenMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetListByMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetListMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapGetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapRemoveAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapRemoveListByMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapRemoveMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapSetAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapSetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map.MapUniqueField;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetReadLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetWriteLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.ReadWriteLockField;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public class MapGenerator extends TypeGenerator {

	private IJavaType mapType = null;
	private boolean autoIncrement = false;

	public MapGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		mapType = registerJavaType("Map" + getDataStore().getName(), CLASS);

		// Auto increment?
		List<JavaModelBeanField> fields = getElementBean().getFieldList();
		for (JavaModelBeanField field : fields) {
			if (field.isAutoIncrement()) {
				autoIncrement = true;
			}
		}
	}

	@Override
	public void generate() {
		JavaObject object = new JavaObject(mapType);
		object.addExtends(getInterfaceType());

		// Map Field & Constructor
		IJavaType map = new JavaTypeWithGenerics(resolve(Map.class), getKeyBean().getInterfaceType(), getElementBean().getInterfaceType());

		// Fields
		JavaField autoIncrementField = null;
		JavaField mapField = new JavaField("map", map).setFinal(true);
		JavaField lockField = new ReadWriteLockField().setFinal(true);
		object.addBlock(lockField);
		object.addBlock(mapField);

		// Auto increment field
		if (autoIncrement) {
			autoIncrementField = new JavaField("autoIncrement", AtomicInteger.class).setFinal(true);
			object.addBlock(autoIncrementField);
		}

		// Unique fields
		for (JavaModelBeanField field : getDataStore().getUniqueFieldList()) {
			object.addBlock(new MapUniqueField(field));
		}

		// Constructors
		object.addBlock(new MapConstructor(mapType.getSimpleName(), mapField, lockField, autoIncrementField, true));
		object.addBlock(new MapConstructor(mapType.getSimpleName(), mapField, lockField, autoIncrementField, false));

		// Methods
		object.addBlock(new GetterMethod(mapField));
		object.addBlock(new GetterMethod(lockField));
		object.addBlock(new GetReadLockMethod().setDefaultContents().setOverride());
		object.addBlock(new GetWriteLockMethod().setDefaultContents().setOverride());

		// Map Methods
		addMapMethods(object, mapField);

		// Custom Methods
		getGenerator().addMethods(object, false);

		write(object);
	}

	private void addMapMethods(JavaObject object, JavaField mapField) {
		String name = mapField.getName().get();
		object.addBlock(new ExistsMethod().setDefaultContents().setOverride());
		object.addBlock(new DelegateMethod(new SizeMethod()).setFieldName(name).setDelegateContents().setOverride());
		object.addBlock(new DelegateMethod(new IsEmptyMethod()).setFieldName(name).setDelegateContents().setOverride());
		object.addBlock(new MapClearMethod(getDataStore()));
		object.addBlock(new MapAddAllMethod(getDataStore()));
		object.addBlock(new MapSetAllMethod(getDataStore()));
		object.addBlock(new MapRemoveAllMethod(getDataStore()));
		object.addBlock(new MapGetAllMethod(getDataStore()));
		object.addBlock(new MapGetMethod(getDataStore()));
		object.addBlock(new MapContainsMethod(getDataStore()));
		object.addBlock(new MapRemoveMethod(getDataStore()));
		object.addBlock(new MapAddMethod(getDataStore()));
		object.addBlock(new MapSetMethod(getDataStore()));
		object.addBlock(new MapCreateMethod(getDataStore()));
		object.addBlock(new MapDestroyMethod(getDataStore()));
		object.addBlock(new MapGetKeyMethod(getDataStore()));
		object.addBlock(new MapGetByBeanMethod(getDataStore()));
		object.addBlock(new MapContainsRowMethod(getDataStore()));

		// Auto Increment
		if (getDataStore().isAutoIncrement()) {
			object.addBlock(new MapAddAutoMethod(getDataStore()));
		}

		// GetAllBy ...
		List<JavaModelBeanField> fields = getElementBean().getFieldList();
		for (JavaModelBeanField field : fields) {

			object.addBlock(new MapContainsColumnMethod(getDataStore(), field));

			boolean unique = field.isUnique();
			object.addBlock(new MapGetListByMethod(getDataStore(), field, getElementBean(), unique));
			object.addBlock(new MapRemoveListByMethod(getDataStore(), field, getElementBean(), unique));

			for (JavaModelBean row : getDataStore().getRowBeans()) {
				object.addBlock(new MapGetListByMethod(getDataStore(), field, row, unique));
				object.addBlock(new MapRemoveListByMethod(getDataStore(), field, row, unique));
			}

			// GetAllBetween
			if (field.isNumeric()) {
				object.addBlock(new MapGetListBetweenMethod(getDataStore(), field));
			}
		}

		// Rows
		for (JavaModelBean row : getDataStore().getRowBeans()) {
			object.addBlock(new MapGetListMethod(getDataStore(), row));
			object.addBlock(new MapContainsRowMethod(getDataStore(), row));

			for (JavaModelDataStoreKey key : getDataStore().getKeyBeans()) {
				object.addBlock(new MapGetByKeyMethod(getDataStore(), row, key));
			}
		}

		// Keys
		for (JavaModelDataStoreKey key : getDataStore().getKeyBeans()) {
			JavaModelBean bean = getDataStore().getElementBean();
			object.addBlock(new MapGetByKeyMethod(getDataStore(), bean, key));
			object.addBlock(new MapContainsRowMethod(getDataStore(), key.getBean()));
		}
	}
}
