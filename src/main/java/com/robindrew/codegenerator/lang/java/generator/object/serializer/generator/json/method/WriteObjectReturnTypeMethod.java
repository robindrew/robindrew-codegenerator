package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.method;

import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type.JsonSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type.JsonSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class WriteObjectReturnTypeMethod extends JavaMethod {

	private final JsonSerializedType type;

	public WriteObjectReturnTypeMethod(JavaModelBean bean, IJavaModelSet modelSet, IJavaTypeResolver resolver) {
		super("writeObject");

		getParameters().add("writer", IJsonWriter.class);
		getParameters().add("object", bean.getReturnType().toObjectType());

		JsonSerializedTypeFactory factory = new JsonSerializedTypeFactory(modelSet);
		type = factory.getType(bean.getReturnType().toObjectType());
		type.addReferences(getReferences());

		setContents(getWriteContents());
		setOverride();
	}

	private IJavaCodeBlock getWriteContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line(type.getNewSerializer() + ".writeObject(writer, object);");
		return code;
	}

}
