package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.xml.method;

import static com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedFieldType.XML;

import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedField;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedFieldList;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class WriteObjectMethod extends JavaMethod {

	private final SerializedFieldList fieldList;

	public WriteObjectMethod(JavaModelBean bean, IJavaModelSet modelSet, IJavaTypeResolver resolver) {
		super("writeObject");
		this.fieldList = new SerializedFieldList(bean, modelSet, resolver, XML);

		getParameters().add("writer", IXmlWriter.class);
		getParameters().add("object", bean.getInterfaceType());

		// Imports
		fieldList.addReferences(getReferences());

		setContents(getWriteContents());
		setOverride();
	}

	private IJavaCodeBlock getWriteContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("writer.startElement(getName());");
		int index = fieldList.size();
		for (SerializedField field : fieldList) {
			String getter = "object.get" + field.getName().toUpper() + "()";
			code.line("writer." + field.getWriteMethod(getter, index > 1));
			index--;
		}
		code.line("writer.endElement(getName());");
		return code;
	}

}
