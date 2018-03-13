package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method;

import static com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedFieldType.JSON;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedFieldList;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class ReadObjectMethod extends JavaMethod {

	private final JavaModelBean bean;
	private final SerializedFieldList fieldList;

	public ReadObjectMethod(JavaModelBean bean, IJavaModelSet modelSet, IJavaTypeResolver resolver) {
		super("readObject", bean.getInterfaceType());
		this.bean = bean;
		this.fieldList = new SerializedFieldList(bean, modelSet, resolver, JSON);

		getParameters().add("reader", IJsonReader.class);

		// Imports
		fieldList.addReferences(getReferences());

		setContents(getReadContents());
		setOverride();
	}

	private IJavaCodeBlock getReadContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("throw new UnsupportedOperationException(\"readObject\");");
		return code;
	}

	public JavaModelBean getBean() {
		return bean;
	}
}
