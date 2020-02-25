package com.robindrew.codegenerator.lang.java.generator.object.factory.method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelXmlSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.servlet.JavaModelServletRequestParser;
import com.robindrew.codegenerator.lang.java.generator.object.factory.FactoryType;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;

public class FactoryConstructor extends JavaConstructor {

	public FactoryConstructor(IJavaType type, List<JavaModel> models, FactoryType factoryType) {
		super(type);

		setConstructorContents(models, factoryType);
	}

	private void setConstructorContents(List<JavaModel> models, FactoryType factoryType) {
		IJavaCodeLines code = new JavaCodeLines();

		// Ensure models are always in the same order
		models = new ArrayList<>(models);
		Collections.sort(models);

		for (JavaModel model : models) {

			code.emptyLine();
			code.line("// Mappings by id");
			for (JavaModelBean bean : model.getBeanList()) {
				putById(code, bean, factoryType);
			}

			code.emptyLine();
			code.line("// Mappings by name");
			for (JavaModelBean bean : model.getBeanList()) {
				putByName(code, bean, factoryType);
			}
		}

		setContents(code);
	}

	private void putByName(IJavaCodeLines code, JavaModelBean bean, FactoryType type) {
		switch (type) {
			case BEAN:
				putByName(code, bean, bean.getType());
				break;
			case EXECUTABLE_BEAN:
				if (bean.isExecutable()) {
					putByName(code, bean, bean.getType());
				}
				break;
			case DATA_SERIALIZER:
				if (bean.hasDataSerializer()) {
					JavaModelDataSerializer serializer = bean.getDataSerializer();
					putByName(code, bean, serializer.getType());
				}
				break;
			case XML_SERIALIZER:
				if (bean.hasXmlSerializer()) {
					JavaModelXmlSerializer serializer = bean.getXmlSerializer();
					putByName(code, bean, serializer.getType());
				}
				break;
			case XML_SERIALIZER_RETURN_TYPE:
				if (bean.hasXmlSerializer()) {
					JavaModelXmlSerializer serializer = bean.getXmlSerializer();
					if (serializer.hasReturnType()) {
						putByName(code, bean, serializer.getReturnType());
					}
				}
				break;
			case JSON_SERIALIZER:
				if (bean.hasJsonSerializer()) {
					JavaModelJsonSerializer serializer = bean.getJsonSerializer();
					putByName(code, bean, serializer.getType());
				}
				break;
			case SERVLET_REQUEST_PARSER:
				if (bean.getServletRequestParser() != null) {
					JavaModelServletRequestParser serializer = bean.getServletRequestParser();
					putByName(code, bean, serializer.getType());
				}
				break;
			default:
				throw new IllegalArgumentException("type not supported: " + type);
		}
	}

	private void putById(IJavaCodeLines code, JavaModelBean bean, FactoryType type) {
		switch (type) {
			case BEAN:
				putById(code, bean, bean.getType());
				break;
			case EXECUTABLE_BEAN:
				if (bean.isExecutable()) {
					putById(code, bean, bean.getType());
				}
				break;
			case DATA_SERIALIZER:
				if (bean.hasDataSerializer()) {
					JavaModelDataSerializer serializer = bean.getDataSerializer();
					putById(code, bean, serializer.getType());
				}
				break;
			case XML_SERIALIZER:
				if (bean.hasXmlSerializer()) {
					JavaModelXmlSerializer serializer = bean.getXmlSerializer();
					putById(code, bean, serializer.getType());
				}
				break;
			case XML_SERIALIZER_RETURN_TYPE:
				if (bean.hasXmlSerializer()) {
					JavaModelXmlSerializer serializer = bean.getXmlSerializer();
					if (serializer.hasReturnType()) {
						putById(code, bean, serializer.getReturnType());
					}
				}
				break;
			case JSON_SERIALIZER:
				if (bean.hasJsonSerializer()) {
					JavaModelJsonSerializer serializer = bean.getJsonSerializer();
					putById(code, bean, serializer.getType());
				}
				break;
			case SERVLET_REQUEST_PARSER:
				if (bean.getServletRequestParser() != null) {
					JavaModelServletRequestParser serializer = bean.getServletRequestParser();
					putById(code, bean, serializer.getType());
				}
				break;
			default:
				throw new IllegalArgumentException("type not supported: " + type);
		}
	}

	private void putByName(IJavaCodeLines code, JavaModelBean bean, IJavaType classType) {
		IJavaType beanType = bean.getType();
		getReferences().add(beanType);
		getReferences().add(classType);
		String beanName = beanType.getSimpleName();
		String className = classType.getSimpleName();
		code.line("objectMap.put(\"" + beanName + "\", " + className + ".class);");
	}

	private void putById(IJavaCodeLines code, JavaModelBean bean, IJavaType classType) {
		IJavaType beanType = bean.getType();
		getReferences().add(beanType);
		getReferences().add(classType);
		String beanName = beanType.getSimpleName();
		String className = classType.getSimpleName();
		code.line("objectMap.put(" + beanName + ".SERIALIZATION_ID, " + className + ".class);");
	}

}
