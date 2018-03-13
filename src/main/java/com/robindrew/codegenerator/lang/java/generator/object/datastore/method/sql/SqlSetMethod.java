package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SetMethod;

public class SqlSetMethod extends SetMethod {

	public SqlSetMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		JavaModelBean bean = dataStore.getElementBean();
		JavaModelBean keyBean = dataStore.getKeyBean();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);
		getReferences().add(bean.getInterfaceType());

		setSqlContents(bean, keyBean);
	}

	private void setSqlContents(JavaModelBean bean, JavaModelBean keyBean) {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.updateSet(getTable());");

		// Set values
		Set<String> keyFieldNames = getKeyFieldNames(keyBean);
		List<JavaModelBeanField> fields = new ArrayList<JavaModelBeanField>();
		boolean comma = false;
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (keyFieldNames.contains(field.getName())) {
				continue;
			}
			if (comma) {
				code.line("sql.sql(',');");
			}
			comma = true;
			code.line("sql.column(\"" + toLower(field.getName()) + "\").sql(\"=?\");");
			fields.add(field);
		}

		// Where
		code.line("sql.where();");
		comma = false;
		for (JavaModelBeanField field : keyBean.getFieldList()) {
			if (comma) {
				code.line("sql.sql(',');");
			}
			comma = true;
			code.line("sql.column(\"" + toLower(field.getName()) + "\").sql(\"=?\");");
			fields.add(field);
		}

		// Values
		code.emptyLine();
		code.line("// Values");
		code.line("ISqlValues values = new SqlValues();");
		for (JavaModelBeanField field : fields) {
			code.line("values.add(element.get" + toUpper(field.getName()) + "());");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql, values);");
		setContents(code);
	}

	private Set<String> getKeyFieldNames(JavaModelBean keyBean) {
		Set<String> set = new HashSet<String>();
		for (JavaModelBeanField field : keyBean.getFieldList()) {
			set.add(field.getName());
		}
		return set;
	}
}
