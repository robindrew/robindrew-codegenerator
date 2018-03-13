package com.robindrew.codegenerator.lang.java.generator.model.bean;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelXmlSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.servlet.JavaModelServletRequestParser;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.model.object.bean.ModelBean;

public class JavaModelBean {

	private final ModelBean bean;
	private final IJavaTypeSet extendsSet = new JavaTypeSet();
	private final List<JavaModelBeanField> fieldList;
	private final List<JavaModelExtends> extendsList;
	private final List<JavaModelBeanConstructor> constructorList;
	private final List<JavaModelBeanMethod> methodList;
	private final List<JavaModelBeanField> identityList = new ArrayList<JavaModelBeanField>();
	private final List<JavaModelBeanField> uniqueFieldList = new ArrayList<JavaModelBeanField>();
	private final JavaModelBeanField autoIncrementField;

	private volatile IJavaType type;
	private volatile IJavaType interfaceType;
	private volatile IJavaType returnType;

	private volatile JavaModelDataSerializer dataSerializer;
	private volatile JavaModelXmlSerializer xmlSerializer;
	private volatile JavaModelJsonSerializer jsonSerializer;
	private volatile JavaModelResultSetParser resultSetParser;
	private volatile JavaModelServletRequestParser servletRequestParser;

	public JavaModelBean(ModelBean bean, List<JavaModelBeanField> fieldList, List<JavaModelExtends> extendsList, List<JavaModelBeanConstructor> constructorList, List<JavaModelBeanMethod> methodList) {
		if (bean == null) {
			throw new NullPointerException("bean");
		}
		if (fieldList == null) {
			throw new NullPointerException("fieldList");
		}
		if (extendsList == null) {
			throw new NullPointerException("extendsList");
		}
		if (constructorList == null) {
			throw new NullPointerException("constructorList");
		}
		if (methodList == null) {
			throw new NullPointerException("methodList");
		}
		this.bean = bean;
		this.fieldList = fieldList;
		this.extendsList = extendsList;
		this.constructorList = constructorList;
		this.methodList = methodList;

		// Identity
		JavaModelBeanField auto = null;
		for (JavaModelBeanField field : fieldList) {
			if (field.get().isUnique()) {
				uniqueFieldList.add(field);
			}
			if (field.get().isIdentity()) {
				identityList.add(field);
			}
			if (field.isAutoIncrement()) {
				if (auto != null) {
					throw new IllegalStateException("Multiple fields declared as autoIncrement: " + field + ", " + auto);
				}
				auto = field;
			}
		}
		this.autoIncrementField = auto;

		// Unique?
		if (identityList.size() == 1) {
			identityList.get(0).setUnique(true);
		}
	}

	public ModelBean get() {
		return bean;
	}

	public boolean isImmutable() {
		return get().isImmutable();
	}

	public String getName() {
		return get().getName();
	}

	public IJavaTypeSet getExtendsSet() {
		return extendsSet;
	}

	public List<JavaModelBeanField> getFieldList() {
		return fieldList;
	}

	public List<JavaModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<JavaModelBeanConstructor> getConstructorList() {
		return constructorList;
	}

	public List<JavaModelBeanMethod> getMethodList() {
		return methodList;
	}

	public IJavaType getType() {
		return type;
	}

	public IJavaType getInterfaceType() {
		return interfaceType;
	}

	public boolean hasDataSerializer() {
		return dataSerializer != null;
	}

	public boolean hasXmlSerializer() {
		return xmlSerializer != null;
	}

	public boolean hasJsonSerializer() {
		return jsonSerializer != null;
	}

	public JavaModelDataSerializer getDataSerializer() {
		if (dataSerializer == null) {
			throw new IllegalStateException("DataSerializer not defined for bean: " + bean);
		}
		return dataSerializer;
	}

	public JavaModelXmlSerializer getXmlSerializer() {
		if (xmlSerializer == null) {
			throw new IllegalStateException("XmlSerializer not defined for bean: " + bean);
		}
		return xmlSerializer;
	}

	public JavaModelJsonSerializer getJsonSerializer() {
		if (jsonSerializer == null) {
			throw new IllegalStateException("JsonSerializer not defined for bean: " + bean);
		}
		return jsonSerializer;
	}

	public JavaModelResultSetParser getResultSetParser() {
		if (resultSetParser == null) {
			throw new IllegalStateException("ResultSetParser not defined for bean: " + bean.getName());
		}
		return resultSetParser;
	}

	public JavaModelServletRequestParser getServletRequestParser() {
		return servletRequestParser;
	}

	public JavaModelBeanField getAutoIncrementField() {
		return autoIncrementField;
	}

	public List<JavaModelBeanField> getUniqueFieldList() {
		return uniqueFieldList;
	}

	public List<IJavaNamedType> getFields() {
		List<IJavaNamedType> list = new ArrayList<IJavaNamedType>();
		for (JavaModelBeanField field : getFieldList()) {
			list.add(field.toNamedType());
		}
		return list;
	}

	public IJavaType getReturnType() {
		return returnType;
	}

	public void setReturnType(IJavaType type) {
		this.returnType = type;
	}

	public void setTypes(IJavaType type, IJavaType interfaceType) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		if (interfaceType == null) {
			throw new NullPointerException("interfaceType");
		}
		this.type = type;
		this.interfaceType = interfaceType;
	}

	public void setDataSerializer(JavaModelDataSerializer serializer) {
		if (serializer == null) {
			throw new NullPointerException("serializer");
		}
		this.dataSerializer = serializer;
	}

	public void setXmlSerializer(JavaModelXmlSerializer serializer) {
		if (serializer == null) {
			throw new NullPointerException("serializer");
		}
		this.xmlSerializer = serializer;
	}

	public void setJsonSerializer(JavaModelJsonSerializer serializer) {
		if (serializer == null) {
			throw new NullPointerException("serializer");
		}
		this.jsonSerializer = serializer;
	}

	public void setResultSetParser(JavaModelResultSetParser parser) {
		if (parser == null) {
			throw new NullPointerException("parser");
		}
		this.resultSetParser = parser;
	}

	public void setServletRequestParser(JavaModelServletRequestParser parser) {
		if (parser == null) {
			throw new NullPointerException("parser");
		}
		this.servletRequestParser = parser;
	}

	public boolean isExecutable() {
		return returnType != null;
	}

}
