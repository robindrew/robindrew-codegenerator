package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.derby;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlTypeDeclaration;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class DerbyTypeDeclaration extends SqlTypeDeclaration {

	protected String getComplexDeclaration(JavaModelBeanField field) {
		IJavaType type = field.getType();

		// Bytes
		long length = getLength(field.getValidator());
		if (type.isType(byte[].class)) {
			if (length <= 255) {
				return "VARCHAR(" + length + ") FOR BIT DATA";
			}
			return "BLOB";
		}

		return super.getComplexDeclaration(field);
	}

}
