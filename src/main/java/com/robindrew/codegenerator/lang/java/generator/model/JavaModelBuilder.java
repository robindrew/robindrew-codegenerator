package com.robindrew.codegenerator.lang.java.generator.model;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanAnnotation;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanConstructor;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanMethod;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanParameter;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnumConstant;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnumField;
import com.robindrew.codegenerator.lang.java.generator.model.executor.JavaModelExecutorSet;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterfaceMethod;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterfaceParameter;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelXmlSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.servlet.JavaModelServletRequestParser;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.model.IModel;
import com.robindrew.codegenerator.model.object.bean.ModelBean;
import com.robindrew.codegenerator.model.object.bean.ModelBeanAnnotation;
import com.robindrew.codegenerator.model.object.bean.ModelBeanConstructor;
import com.robindrew.codegenerator.model.object.bean.ModelBeanField;
import com.robindrew.codegenerator.model.object.bean.ModelBeanMethod;
import com.robindrew.codegenerator.model.object.bean.ModelBeanParameter;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStore;
import com.robindrew.codegenerator.model.object.eenum.ModelEnum;
import com.robindrew.codegenerator.model.object.eenum.ModelEnumConstant;
import com.robindrew.codegenerator.model.object.eenum.ModelEnumField;
import com.robindrew.codegenerator.model.object.executor.ModelExecutorSet;
import com.robindrew.codegenerator.model.object.iinterface.ModelExtends;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterface;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterfaceMethod;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterfaceParameter;
import com.robindrew.codegenerator.model.object.serializer.ModelDataSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelJsonSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelXmlSerializer;
import com.robindrew.codegenerator.model.object.servlet.ModelServletRequestParser;
import com.robindrew.codegenerator.model.object.sql.ModelResultSetParser;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class JavaModelBuilder {

	private final IModel model;

	public JavaModelBuilder(IModel model) {
		if (model == null) {
			throw new NullPointerException("model");
		}
		this.model = model;
	}

	public JavaModel build() {

		List<JavaModelBean> beanList = getBeanList();
		List<JavaModelEnum> enumList = getEnumList();
		List<JavaModelExecutorSet> executorSetList = getExecutorSetList();
		List<JavaModelValidator> validatorList = getValidatorList();
		List<JavaModelResultSetParser> resultSetParserList = getResultSetParserList();
		List<JavaModelServletRequestParser> servletRequestParserList = getServletRequestParserList();
		List<JavaModelDataSerializer> dataSerializerList = getDataSerializerList();
		List<JavaModelXmlSerializer> xmlSerializerList = getXmlSerializerList();
		List<JavaModelJsonSerializer> jsonSerializerList = getJsonSerializerList();
		List<JavaModelDataStore> dataStoreList = getDataStoreList();
		List<JavaModelInterface> interfaceList = getInterfaceList();

		JavaModel build = new JavaModel(model);
		build.setBeanList(beanList);
		build.setEnumList(enumList);
		build.setExecutorSetList(executorSetList);
		build.setValidatorList(validatorList);
		build.setResultSetParserList(resultSetParserList);
		build.setServletRequestParserList(servletRequestParserList);
		build.setDataSerializerList(dataSerializerList);
		build.setXmlSerializerList(xmlSerializerList);
		build.setJsonSerializerList(jsonSerializerList);
		build.setDataStoreList(dataStoreList);
		build.setInterfaceList(interfaceList);
		return build;
	}

	private List<JavaModelInterface> getInterfaceList() {
		List<JavaModelInterface> list = new ArrayList<JavaModelInterface>();
		for (ModelInterface store : model.getInterfaceList()) {
			JavaModelInterface typed = getInterface(store);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelDataStore> getDataStoreList() {
		List<JavaModelDataStore> list = new ArrayList<JavaModelDataStore>();
		for (ModelDataStore store : model.getDataStoreList()) {
			JavaModelDataStore typed = getDataStore(store);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private JavaModelInterface getInterface(ModelInterface iinterface) {

		// Extends
		List<JavaModelExtends> extendsList = getExtendsList(iinterface.getExtendsList());

		// Methods
		List<JavaModelInterfaceMethod> methodList = new ArrayList<JavaModelInterfaceMethod>();
		for (ModelInterfaceMethod method : iinterface.getMethodList()) {

			// Parameters
			List<JavaModelInterfaceParameter> parameterList = new ArrayList<JavaModelInterfaceParameter>();
			for (ModelInterfaceParameter parameter : method.getParameterList()) {
				parameterList.add(new JavaModelInterfaceParameter(parameter));
			}
			methodList.add(new JavaModelInterfaceMethod(method, parameterList));
		}

		return new JavaModelInterface(iinterface, extendsList, methodList);
	}

	private JavaModelDataStore getDataStore(ModelDataStore store) {

		// Extends
		List<JavaModelExtends> extendsList = getExtendsList(store.getExtendsList());

		// Done
		return new JavaModelDataStore(store, extendsList);
	}

	private List<JavaModelDataSerializer> getDataSerializerList() {
		List<JavaModelDataSerializer> list = new ArrayList<JavaModelDataSerializer>();
		for (ModelDataSerializer store : model.getDataSerializerList()) {
			JavaModelDataSerializer typed = new JavaModelDataSerializer(store);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelXmlSerializer> getXmlSerializerList() {
		List<JavaModelXmlSerializer> list = new ArrayList<JavaModelXmlSerializer>();
		for (ModelXmlSerializer store : model.getXmlSerializerList()) {
			JavaModelXmlSerializer typed = new JavaModelXmlSerializer(store);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelJsonSerializer> getJsonSerializerList() {
		List<JavaModelJsonSerializer> list = new ArrayList<JavaModelJsonSerializer>();
		for (ModelJsonSerializer store : model.getJsonSerializerList()) {
			JavaModelJsonSerializer typed = new JavaModelJsonSerializer(store);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelValidator> getValidatorList() {
		List<JavaModelValidator> list = new ArrayList<JavaModelValidator>();
		for (ModelValidator validator : model.getValidatorList()) {
			JavaModelValidator typed = new JavaModelValidator(validator);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelResultSetParser> getResultSetParserList() {
		List<JavaModelResultSetParser> list = new ArrayList<JavaModelResultSetParser>();
		for (ModelResultSetParser parser : model.getResultSetParserList()) {
			JavaModelResultSetParser typed = new JavaModelResultSetParser(parser);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelServletRequestParser> getServletRequestParserList() {
		List<JavaModelServletRequestParser> list = new ArrayList<JavaModelServletRequestParser>();
		for (ModelServletRequestParser parser : model.getServletRequestParserList()) {
			JavaModelServletRequestParser typed = new JavaModelServletRequestParser(parser);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelBean> getBeanList() {
		List<JavaModelBean> list = new ArrayList<JavaModelBean>();
		for (ModelBean bean : model.getBeanList()) {
			JavaModelBean typed = getBean(bean);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private JavaModelBean getBean(ModelBean bean) {

		// Fields
		List<JavaModelBeanField> fieldList = new ArrayList<JavaModelBeanField>();
		for (ModelBeanField field : bean.getFieldList()) {

			// Annotation
			List<JavaModelBeanAnnotation> annotationList = new ArrayList<JavaModelBeanAnnotation>();
			for (ModelBeanAnnotation annotation : field.getAnnotationList()) {
				JavaModelBeanAnnotation typed = new JavaModelBeanAnnotation(annotation);
				annotationList.add(typed);
			}

			JavaModelBeanField typed = new JavaModelBeanField(field);
			typed.setAnnotationList(annotationList);
			fieldList.add(typed);
		}

		// Extends
		List<JavaModelExtends> extendsList = getExtendsList(bean.getExtendsList());

		// Constructor
		List<JavaModelBeanConstructor> constructorList = new ArrayList<JavaModelBeanConstructor>();
		for (ModelBeanConstructor constructor : bean.getConstructorList()) {
			JavaModelBeanConstructor typed = new JavaModelBeanConstructor(constructor);
			constructorList.add(typed);
		}

		// Methods
		List<JavaModelBeanMethod> methodList = new ArrayList<JavaModelBeanMethod>();
		for (ModelBeanMethod method : bean.getMethodList()) {

			// Parameters
			List<JavaModelBeanParameter> parameterList = new ArrayList<JavaModelBeanParameter>();
			for (ModelBeanParameter parameter : method.getParameterList()) {
				parameterList.add(new JavaModelBeanParameter(parameter));
			}
			methodList.add(new JavaModelBeanMethod(method, parameterList));
		}

		// Done
		return new JavaModelBean(bean, fieldList, extendsList, constructorList, methodList);
	}

	private List<JavaModelEnum> getEnumList() {
		List<JavaModelEnum> list = new ArrayList<JavaModelEnum>();
		for (ModelEnum eenum : model.getEnumList()) {
			JavaModelEnum typed = getEnum(eenum);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private List<JavaModelExecutorSet> getExecutorSetList() {
		List<JavaModelExecutorSet> list = new ArrayList<JavaModelExecutorSet>();
		for (ModelExecutorSet eenum : model.getExecutorSetList()) {
			JavaModelExecutorSet typed = getExecutorSet(eenum);
			list.add(typed);
		}
		return ImmutableList.copyOf(list);
	}

	private JavaModelExecutorSet getExecutorSet(ModelExecutorSet set) {

		// Extends
		List<JavaModelExtends> extendsList = getExtendsList(set.getExtendsList());

		// Done
		return new JavaModelExecutorSet(set, extendsList);

	}

	private JavaModelEnum getEnum(ModelEnum eenum) {

		// Constants
		List<JavaModelEnumConstant> constantList = new ArrayList<JavaModelEnumConstant>();
		for (ModelEnumConstant constant : eenum.getConstantList()) {

			// Fields
			List<JavaModelEnumField> fieldList = new ArrayList<JavaModelEnumField>();
			for (ModelEnumField field : constant.getFieldList()) {
				JavaModelEnumField typed = new JavaModelEnumField(field);
				fieldList.add(typed);
			}

			JavaModelEnumConstant typed = new JavaModelEnumConstant(constant, fieldList);
			constantList.add(typed);
		}

		// Extends
		List<JavaModelExtends> extendsList = getExtendsList(eenum.getExtendsList());

		// Done
		return new JavaModelEnum(eenum, extendsList, constantList);
	}

	private List<JavaModelExtends> getExtendsList(List<ModelExtends> extendsList) {
		List<JavaModelExtends> list = new ArrayList<JavaModelExtends>();
		for (ModelExtends eextends : extendsList) {
			JavaModelExtends typed = new JavaModelExtends(eextends);
			list.add(typed);
		}
		return list;
	}

}
