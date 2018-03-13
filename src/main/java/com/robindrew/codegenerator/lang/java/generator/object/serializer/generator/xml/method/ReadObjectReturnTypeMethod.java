package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.xml.method;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type.XmlSerializedType;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.xml.type.XmlSerializedTypeFactory;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class ReadObjectReturnTypeMethod extends JavaMethod {

	private final XmlSerializedType type;

	public ReadObjectReturnTypeMethod(JavaModelBean bean, IJavaModelSet modelSet, IJavaTypeResolver resolver) {
		super("readObject", bean.getReturnType().toObjectType());

		getParameters().add("reader", IXmlReader.class);

		XmlSerializedTypeFactory factory = new XmlSerializedTypeFactory(modelSet);
		type = factory.getType(bean.getReturnType().toObjectType());
		type.addReferences(getReferences());

		setContents(getReadContents());
		setOverride();
	}

	private IJavaCodeBlock getReadContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("return " + type.getNewSerializer("getName()", true) + ".readObject(reader);");
		return code;
	}
}
