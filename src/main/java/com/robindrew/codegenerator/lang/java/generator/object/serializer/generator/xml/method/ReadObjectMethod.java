package com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.xml.method;

import static com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedFieldType.XML;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.field.SerializedField;
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
		this.fieldList = new SerializedFieldList(bean, modelSet, resolver, XML);

		getParameters().add("reader", IXmlReader.class);

		// Imports
		fieldList.addReferences(getReferences());

		setContents(getReadContents());
		setOverride();
	}

	private IJavaCodeBlock getReadContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("reader.startElement(getName());");
		StringBuilder params = new StringBuilder();
		int count = 1;
		for (SerializedField field : fieldList) {
			String paramName = "param" + count;
			if (params.length() > 0) {
				params.append(", ");
			}
			params.append(paramName);
			count++;
			code.line(field.getType() + " " + paramName + " = reader." + field.getReadMethod());
		}
		code.line("reader.endElement(getName());");
		code.emptyLine();
		code.line("// Create the bean");
		code.line("return new " + bean.getType().getSimpleName(true) + "(" + params + ");");
		return code;
	}
}
