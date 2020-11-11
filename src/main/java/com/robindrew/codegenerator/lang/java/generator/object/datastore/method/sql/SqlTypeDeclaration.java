package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class SqlTypeDeclaration {

	private final Map<Class<?>, String> basicMap = new LinkedHashMap<Class<?>, String>();

	public SqlTypeDeclaration() {
		map(byte.class, "TINYINT");
		map(short.class, "SMALLINT");
		map(int.class, "INT");
		map(long.class, "BIGINT");
		map(float.class, "FLOAT");
		map(double.class, "DOUBLE");
		map(boolean.class, "BOOLEAN");
		map(Byte.class, "TINYINT");
		map(Short.class, "SMALLINT");
		map(Integer.class, "INT");
		map(Long.class, "BIGINT");
		map(Float.class, "FLOAT");
		map(Double.class, "DOUBLE");
		map(Boolean.class, "BOOLEAN");
		map(BigDecimal.class, "DECIMAL");
	}

	public String get(Class<?> type) {
		return basicMap.get(type);
	}
	
	private void map(Class<?> type, String name) {
		basicMap.put(type, name);
	}

	public String get(JavaModelBeanField field) {

		// Get declaration
		String declaration = getBasicDeclaration(field);
		if (declaration == null) {
			declaration = getComplexDeclaration(field);
		}
		return declaration;
	}

	private String getBasicDeclaration(JavaModelBeanField field) {
		IJavaType type = field.getType();
		for (Entry<Class<?>, String> entry : basicMap.entrySet()) {
			if (type.isType(entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}

	protected String getComplexDeclaration(JavaModelBeanField field) {
		IJavaType type = field.getType();

		// Text
		long length = getLength(field.getValidator());
		if (type.isType(String.class)) {
			if (length <= 255) {
				return "VARCHAR(" + length + ")";
			}
			return "CLOB";
		}

		// Bytes
		if (type.isType(byte[].class)) {
			if (length <= 255) {
				return "VARBINARY(" + length + ")";
			}
			return "BLOB";
		}

		// Enum
		if (type.isEnum()) {
			return get(Short.class);
		}

		// Not Supported
		throw new IllegalArgumentException("type not supported: " + type);
	}

	protected long getLength(JavaModelValidator validator) {
		if (validator == null) {
			return 255;
		}
		String max = validator.get().getMax();
		if (max == null) {
			return 255;
		}
		return Long.parseLong(max);
	}

}
