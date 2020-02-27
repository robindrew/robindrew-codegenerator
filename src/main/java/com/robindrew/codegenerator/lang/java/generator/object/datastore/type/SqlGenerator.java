package com.robindrew.codegenerator.lang.java.generator.object.datastore.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.List;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlExecutor;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreMethods;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.IsEmptyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SetAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.NewSqlBuilderMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlAddAutoMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlAddMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlClearMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlConstructor;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlContainsColumnMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlContainsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlContainsRowMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlCreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlDestroyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetAllMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetByBeanMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetByKeyMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetByRowMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetListBetweenMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetListByMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetListMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlGetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlRemoveListByMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlRemoveMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlSetMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlSizeMethod;
import com.robindrew.codegenerator.lang.java.generator.object.methods.JavaMethodSet;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetReadLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.GetWriteLockMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.concurrent.ReadWriteLockField;
import com.robindrew.codegenerator.lang.java.type.block.method.getter.GetterMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.setter.SetterMethod;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;

public abstract class SqlGenerator extends TypeGenerator {

	private IJavaType databaseType = null;

	public SqlGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	public void registerSecondaryTypes() {
		databaseType = registerJavaType(getPrefix() + getDataStore().getName(), CLASS);
	}

	@Override
	public void generate() {
		IJavaType interfaceType = resolve("ISql" + getDataStore().getName());

		JavaObject object = new JavaObject(databaseType);
		object.addExtends(interfaceType);

		// Fields
		JavaField tableField = new JavaField("table", String.class).setVolatile(true).setAssignment("\"" + getDataStore().getName() + "\"");
		JavaField databaseField = new JavaField("database", String.class).setVolatile(true);
		JavaField executorField = new JavaField("executor", ISqlExecutor.class).setVolatile(true);
		JavaField lockField = new ReadWriteLockField().setFinal(true);

		object.addBlock(tableField);
		object.addBlock(databaseField);
		object.addBlock(executorField);
		object.addBlock(lockField);

		// Constructors
		object.addBlock(new SqlConstructor(databaseType.getSimpleName(), lockField, false));
		object.addBlock(new SqlConstructor(databaseType.getSimpleName(), lockField, true));

		JavaDataStoreMethods methods = new JavaDataStoreMethods(this);

		// Field Methods
		methods.add(new GetterMethod(lockField).setDefaultContents());
		methods.add(new GetterMethod(tableField).setNonNull(true).setDefaultContents());
		methods.add(new GetterMethod(databaseField).setNonNull(true).setDefaultContents());
		methods.add(new GetterMethod(executorField).setNonNull(true).setDefaultContents());
		methods.add(new SetterMethod(tableField));
		methods.add(new SetterMethod(databaseField));
		methods.add(new SetterMethod(executorField));
		addMethods(methods);

		object.addBlocks(methods);

		// Custom Methods
		getGenerator().addMethods(object, false);

		// Done
		write(object);
	}

	private void addMethods(JavaMethodSet methods) {

		// Locks
		methods.add(new GetReadLockMethod().setDefaultContents().setOverride());
		methods.add(new GetWriteLockMethod().setDefaultContents().setOverride());

		// Read Methods
		methods.add(new NewSqlBuilderMethod(getSqlBuilderType()));
		methods.add(new SqlSizeMethod());
		methods.add(new IsEmptyMethod().setDefaultContents().setOverride());
		methods.add(new SqlContainsMethod(getDataStore()));
		methods.add(new SqlGetAllMethod(getDataStore()));
		methods.add(new SqlGetByBeanMethod(getDataStore()));
		methods.add(new SqlGetMethod(getDataStore()));
		methods.add(new SqlContainsRowMethod(getDataStore(), getDataStore().getKeyBean()));

		// Write Methods
		methods.add(new SqlClearMethod());
		methods.add(new SqlCreateMethod(getDataStore()));
		methods.add(new SqlDestroyMethod());
		methods.add(new SqlAddMethod(getDataStore()));
		methods.add(new SqlSetMethod(getDataStore()));
		methods.add(new SqlRemoveMethod(getDataStore()));
		methods.add(new AddAllMethod(getDataStore()).setDefaultContents().setOverride());
		methods.add(new SetAllMethod(getDataStore()).setDefaultContents().setOverride());
		methods.add(new RemoveAllMethod(getDataStore()).setDefaultContents().setOverride());

		// Auto Increment
		if (getDataStore().isAutoIncrement()) {
			methods.add(new SqlAddAutoMethod(getDataStore()));
		}

		// GetListBy ....
		List<JavaModelBeanField> fields = getElementBean().getFieldList();
		for (JavaModelBeanField field : fields) {

			methods.add(new SqlContainsColumnMethod(field));

			boolean unique = field.isUnique();
			methods.add(new SqlGetListByMethod(getDataStore(), field, getElementBean(), unique));
			methods.add(new SqlRemoveListByMethod(getDataStore(), field, getElementBean(), unique));

			for (JavaModelBean row : getDataStore().getRowBeans()) {
				methods.add(new SqlGetListByMethod(getDataStore(), field, row, unique));
				methods.add(new SqlRemoveListByMethod(getDataStore(), field, row, unique));
			}

			if (field.isNumeric()) {
				methods.add(new SqlGetListBetweenMethod(getDataStore(), field));
			}
		}

		// Rows
		for (JavaModelBean row : getDataStore().getRowBeans()) {
			methods.add(new SqlGetListMethod(getDataStore(), row));
			methods.add(new SqlContainsRowMethod(getDataStore(), row));
			methods.add(new SqlGetByRowMethod(getDataStore(), getDataStore().getElementBean(), row));

			for (JavaModelDataStoreKey key : getDataStore().getKeyBeans()) {
				methods.add(new SqlGetByKeyMethod(getDataStore(), row, key));
			}
		}

		// Keys
		for (JavaModelDataStoreKey key : getDataStore().getKeyBeans()) {
			JavaModelBean bean = getDataStore().getElementBean();
			methods.add(new SqlGetByKeyMethod(getDataStore(), bean, key));
			methods.add(new SqlContainsRowMethod(getDataStore(), key.getBean()));
		}

		// Special implementations
		addSpecialImplementations(methods);

		// Interface Methods
		methods.addInterfaceMethods();
	}

	protected abstract Class<? extends ISqlBuilder> getSqlBuilderType();

	protected abstract void addSpecialImplementations(JavaMethodSet methods);

	protected abstract String getPrefix();

}
