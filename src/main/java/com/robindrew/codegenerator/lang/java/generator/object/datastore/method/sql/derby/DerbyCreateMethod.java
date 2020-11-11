package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.derby;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlCreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlTypeDeclaration;

public class DerbyCreateMethod extends SqlCreateMethod {

	public DerbyCreateMethod(JavaModelDataStore dataStore) {
		super(dataStore);
	}

	public SqlTypeDeclaration getTypeDeclaration() {
		return new DerbyTypeDeclaration();
	}

}
