package com.robindrew.codegenerator.lang.java.generator.model;

import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.builder.JavaModelBuilder;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.executor.JavaModelExecutorSet;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelXmlSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.servlet.JavaModelServletRequestParser;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.model.IModel;

public class JavaModel implements Comparable<JavaModel> {

	private final IModel model;
	private volatile List<JavaModelValidator> validatorList;
	private volatile List<JavaModelBean> beanList;
	private volatile List<JavaModelEnum> enumList;
	private volatile List<JavaModelResultSetParser> resultSetParserList;
	private volatile List<JavaModelServletRequestParser> servletRequestParserList;
	private volatile List<JavaModelDataSerializer> dataSerializerList;
	private volatile List<JavaModelXmlSerializer> xmlSerializerList;
	private volatile List<JavaModelJsonSerializer> jsonSerializerList;
	private volatile List<JavaModelDataStore> dataStoreList;
	private volatile List<JavaModelInterface> interfaceList;
	private volatile List<JavaModelExecutorSet> executorSetList;
	private volatile List<JavaModelBuilder> builderList;

	public JavaModel(IModel model) {
		if (model == null) {
			throw new NullPointerException("model");
		}
		this.model = model;
	}

	public IModel get() {
		return model;
	}

	public List<JavaModelExecutorSet> getExecutorSetList() {
		return executorSetList;
	}

	public IModel getModel() {
		return model;
	}

	public List<JavaModelBuilder> getBuilderList() {
		return builderList;
	}

	public List<JavaModelValidator> getValidatorList() {
		return validatorList;
	}

	public List<JavaModelBean> getBeanList() {
		return beanList;
	}

	public List<JavaModelEnum> getEnumList() {
		return enumList;
	}

	public List<JavaModelDataSerializer> getDataSerializerList() {
		return dataSerializerList;
	}

	public List<JavaModelXmlSerializer> getXmlSerializerList() {
		return xmlSerializerList;
	}

	public List<JavaModelJsonSerializer> getJsonSerializerList() {
		return jsonSerializerList;
	}

	public List<JavaModelDataStore> getDataStoreList() {
		return dataStoreList;
	}

	public List<JavaModelInterface> getInterfaceList() {
		return interfaceList;
	}

	public List<JavaModelResultSetParser> getResultSetParserList() {
		return resultSetParserList;
	}

	public List<JavaModelServletRequestParser> getServletRequestParserList() {
		return servletRequestParserList;
	}

	public void setValidatorList(List<JavaModelValidator> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.validatorList = list;
	}

	public void setBeanList(List<JavaModelBean> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.beanList = list;
	}

	public void setEnumList(List<JavaModelEnum> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.enumList = list;
	}

	public void setResultSetParserList(List<JavaModelResultSetParser> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.resultSetParserList = list;
	}

	public void setServletRequestParserList(List<JavaModelServletRequestParser> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.servletRequestParserList = list;
	}

	public void setDataSerializerList(List<JavaModelDataSerializer> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.dataSerializerList = list;
	}

	public void setXmlSerializerList(List<JavaModelXmlSerializer> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.xmlSerializerList = list;
	}

	public void setJsonSerializerList(List<JavaModelJsonSerializer> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.jsonSerializerList = list;
	}

	public void setDataStoreList(List<JavaModelDataStore> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.dataStoreList = list;
	}

	public void setInterfaceList(List<JavaModelInterface> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.interfaceList = list;
	}

	public void setBuilderList(List<JavaModelBuilder> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.builderList = list;
	}

	public void setExecutorSetList(List<JavaModelExecutorSet> list) {
		if (list == null) {
			throw new NullPointerException("list");
		}
		this.executorSetList = list;
	}

	@Override
	public int compareTo(JavaModel that) {
		CompareToBuilder compare = new CompareToBuilder();
		compare.append(this.get().getId(), that.get().getId());
		compare.append(this.get().getPackage(), that.get().getPackage());
		compare.append(this.get().getTarget(), that.get().getTarget());
		return compare.toComparison();
	}

}
