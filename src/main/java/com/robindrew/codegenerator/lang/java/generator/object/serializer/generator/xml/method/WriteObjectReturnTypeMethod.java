package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.xml.method;

import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type.XmlSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type.XmlSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class WriteObjectReturnTypeMethod extends JavaMethod {

	private final XmlSerializedType type;

	public WriteObjectReturnTypeMethod(JavaModelBean bean, IJavaModelSet modelSet, IJavaTypeResolver resolver) {
		super("writeObject");

		getParameters().add("writer", IXmlWriter.class);
		getParameters().add("object", bean.getReturnType().toObjectType());

		XmlSerializedTypeFactory factory = new XmlSerializedTypeFactory(modelSet);
		type = factory.getType(bean.getReturnType().toObjectType());
		type.addReferences(getReferences());

		setContents(getWriteContents());
		setOverride();
	}

	private IJavaCodeBlock getWriteContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line(type.getNewSerializer("getName()", true) + ".writeObject(writer, object);");
		return code;
	}

}
