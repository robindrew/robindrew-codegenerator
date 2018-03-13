package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type.JsonSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type.JsonSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class ReadObjectReturnTypeMethod extends JavaMethod {

	private final JsonSerializedType type;

	public ReadObjectReturnTypeMethod(JavaModelBean bean, IJavaModelSet modelSet, IJavaTypeResolver resolver) {
		super("readObject", bean.getReturnType().toObjectType());

		getParameters().add("reader", IJsonReader.class);

		JsonSerializedTypeFactory factory = new JsonSerializedTypeFactory(modelSet);
		type = factory.getType(bean.getReturnType().toObjectType());
		type.addReferences(getReferences());

		setContents(getReadContents());
		setOverride();
	}

	private IJavaCodeBlock getReadContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("return " + type.getNewSerializer() + ".readObject(reader);");
		return code;
	}
}
