package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
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
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy.CopyConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy.CopyGetListMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy.CopyGetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy.CopySetListMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy.CopySetMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetReadLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetWriteLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public class CopyGenerator extends TypeGenerator {

	private IJavaType copyType = null;

	public CopyGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		copyType = registerJavaType("Copy" + getDataStore().getName(), CLASS);
	}

	@Override
	public void generate() {
		JavaObject object = new JavaObject(copyType);
		object.addExtends(getInterfaceType());

		// Locks
		JavaField copyOnRead = new JavaField("copyOnRead", boolean.class).setFinal(true);
		JavaField copyOnWrite = new JavaField("copyOnWrite", boolean.class).setFinal(true);
		object.addBlock(copyOnRead);
		object.addBlock(copyOnWrite);

		// Delegate
		JavaField delegateField = new JavaField("delegate", getInterfaceType()).setFinal(true);
		object.addBlock(delegateField);
		object.addBlock(new CopyConstructor(copyType.getSimpleName(), delegateField, copyOnRead, copyOnWrite));

		// Getters
		object.addBlock(new GetterMethod(delegateField));
		object.addBlock(new GetterMethod(copyOnRead));
		object.addBlock(new GetterMethod(copyOnWrite));

		addCopyMethods(object, delegateField);
		write(object);
	}

	public void addCopyMethods(JavaObject object, JavaField copyField) {

		object.addBlock(toDelegate(new GetWriteLockMethod()));
		object.addBlock(toDelegate(new GetReadLockMethod()));
		object.addBlock(toDelegate(new SizeMethod()));
		object.addBlock(toDelegate(new IsEmptyMethod()));
		object.addBlock(toDelegate(new ClearMethod()));
		object.addBlock(toDelegate(new ExistsMethod()));
		object.addBlock(toDelegate(new CreateMethod()));
		object.addBlock(toDelegate(new DestroyMethod()));
		object.addBlock(toDelegate(new ContainsMethod(getDataStore())));
		object.addBlock(toDelegate(new RemoveMethod(getDataStore())));
		object.addBlock(toDelegate(new RemoveAllMethod(getDataStore())));

		object.addBlock(set(new CopySetMethod(new AddMethod(getDataStore()), getDataStore())));
		object.addBlock(set(new CopySetMethod(new SetMethod(getDataStore()), getDataStore())));
		object.addBlock(new CopySetListMethod(new AddAllMethod(getDataStore()), getDataStore()).setOverride());
		object.addBlock(new CopySetListMethod(new SetAllMethod(getDataStore()), getDataStore()).setOverride());
		object.addBlock(new CopyGetListMethod(new GetAllMethod(getDataStore()), getDataStore(), false).setOverride());
		object.addBlock(new CopyGetMethod(new GetMethod(getDataStore()), getDataStore()).setOverride());
		object.addBlock(new CopyGetMethod(new GetByBeanMethod(getDataStore()), getDataStore()).setOverride());

		// Auto Increment
		if (getDataStore().isAutoIncrement()) {
			object.addBlock(set(new CopySetMethod(new AddAutoMethod(getDataStore()), getDataStore())));
		}

		// GetAllBy ...
		List<JavaModelBeanField> fields = getElementBean().getFieldList();
		for (JavaModelBeanField field : fields) {

			object.addBlock(toDelegate(new ContainsColumnMethod(field)));

			boolean unique = field.isUnique();
			object.addBlock(new CopyGetListMethod(new GetListByMethod(getDataStore(), field, getElementBean(), unique), getDataStore(), unique));

			for (JavaModelBean row : getDataStore().getRowBeans()) {
				object.addBlock(new CopyGetListMethod(new GetListByMethod(getDataStore(), field, row, unique), getDataStore(), unique, row));
			}

			// GetAllBetween
			if (field.isNumeric()) {
				object.addBlock(new CopyGetListMethod(new GetListBetweenMethod(getDataStore(), field), getDataStore(), false));
			}
		}

		// Rows
		for (JavaModelBean row : getDataStore().getRowBeans()) {
			object.addBlock(new CopyGetListMethod(new GetListMethod(getDataStore(), row), getDataStore(), false, row));

			// Row Keys
			for (JavaModelDataStoreKey key : getDataStore().getKeyBeans()) {
				GetByKeyMethod method = new GetByKeyMethod(getDataStore(), row, key);
				if (key.isUnique()) {
					object.addBlock(new CopyGetMethod(method, getDataStore()).setOverride());
				} else {
					object.addBlock(new CopyGetListMethod(method, getDataStore(), false).setOverride());
				}
			}
		}

		// Keys
		for (JavaModelDataStoreKey key : getDataStore().getKeyBeans()) {
			JavaModelBean bean = getDataStore().getElementBean();
			GetByKeyMethod method = new GetByKeyMethod(getDataStore(), bean, key);
			if (key.isUnique()) {
				object.addBlock(new CopyGetMethod(method, getDataStore()).setOverride());
			} else {
				object.addBlock(new CopyGetListMethod(method, getDataStore(), false).setOverride());
			}
		}
	}

	private IJavaClassBlock set(DelegateMethod method) {
		return method.setDelegateContents().setOverride();
	}

}
