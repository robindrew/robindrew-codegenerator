package com.robindrew.codegenerator.lang.java.generator.object.datastore;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAutoMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ClearMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsColumnMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.CreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.DestroyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetByBeanMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetByKeyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListBetweenMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListByMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.IsEmptyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SetAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SizeMethod;
import com.robindrew.codegenerator.lang.java.generator.object.methods.JavaMethodSet;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetReadLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetWriteLockMethod;

public class JavaDataStoreMethods extends JavaMethodSet {

	private final IJavaDataStoreGenerator dataStore;

	public JavaDataStoreMethods(IJavaDataStoreGenerator dataStore) {
		super(dataStore.getContext().getModelSet(), dataStore.getDataStore().getExtendsList());
		this.dataStore = dataStore;
	}

	public void addDefaultMethods() {
		addDefaultMethods(false);
	}

	public void addDefaultMethods(boolean readOnly) {

		// Locks
		add(new GetReadLockMethod());
		if (!readOnly) {
			add(new GetWriteLockMethod());
		}

		// Standard Methods
		add(new SizeMethod());
		add(new IsEmptyMethod());
		add(new ExistsMethod());
		add(new ContainsMethod(dataStore.getDataStore()));
		add(new GetAllMethod(dataStore.getDataStore()));
		add(new GetMethod(dataStore.getDataStore()));
		add(new GetByBeanMethod(dataStore.getDataStore()));

		if (!readOnly) {
			add(new ClearMethod());
			add(new CreateMethod());
			add(new DestroyMethod());
			add(new AddMethod(dataStore.getDataStore()));
			add(new SetMethod(dataStore.getDataStore()));
			add(new RemoveMethod(dataStore.getDataStore()));
			add(new AddAllMethod(dataStore.getDataStore()));
			add(new SetAllMethod(dataStore.getDataStore()));
			add(new RemoveAllMethod(dataStore.getDataStore()));
		}

		// Auto Increment
		if (dataStore.getDataStore().isAutoIncrement()) {
			add(new AddAutoMethod(dataStore.getDataStore()));
		}

		// GetListBy ...
		List<JavaModelBeanField> fields = dataStore.getDataStore().getElementBean().getFieldList();
		for (JavaModelBeanField field : fields) {

			add(new ContainsColumnMethod(field));

			boolean unique = field.isUnique();
			JavaModelBean bean = dataStore.getDataStore().getElementBean();
			add(new GetListByMethod(dataStore.getDataStore(), field, bean, unique));

			// GetAllBetween
			if (field.isNumeric()) {
				add(new GetListBetweenMethod(dataStore.getDataStore(), field));
			}

			for (JavaModelBean row : dataStore.getDataStore().getRowBeans()) {
				add(new GetListByMethod(dataStore.getDataStore(), field, row, unique));
			}
		}

		// Rows
		for (JavaModelBean row : dataStore.getDataStore().getRowBeans()) {
			add(new GetListMethod(dataStore.getDataStore(), row));

			for (JavaModelDataStoreKey key : dataStore.getDataStore().getKeyBeans()) {
				add(new GetByKeyMethod(dataStore.getDataStore(), row, key));
			}
		}
		
		// Keys
		for (JavaModelDataStoreKey key : dataStore.getDataStore().getKeyBeans()) {
			add(new GetByKeyMethod(dataStore.getDataStore(), dataStore.getDataStore().getElementBean(), key));
		}
	}

}
