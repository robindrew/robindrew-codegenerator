package com.robindrew.codegenerator.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.robindrew.codegenerator.model.object.adapter.ModelAdapter;
import com.robindrew.codegenerator.model.object.alias.ModelAlias;
import com.robindrew.codegenerator.model.object.annotation.ModelAnnotation;
import com.robindrew.codegenerator.model.object.bean.ModelBean;
import com.robindrew.codegenerator.model.object.builder.ModelBuilder;
import com.robindrew.codegenerator.model.object.comparator.ModelComparator;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStore;
import com.robindrew.codegenerator.model.object.delegate.ModelDelegate;
import com.robindrew.codegenerator.model.object.eenum.ModelEnum;
import com.robindrew.codegenerator.model.object.executor.ModelExecutorSet;
import com.robindrew.codegenerator.model.object.factory.ModelObjectFactory;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterface;
import com.robindrew.codegenerator.model.object.option.ModelOption;
import com.robindrew.codegenerator.model.object.serializer.ModelDataSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelJsonSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelXmlSerializer;
import com.robindrew.codegenerator.model.object.servlet.ModelServletRequestParser;
import com.robindrew.codegenerator.model.object.sql.ModelResultSetParser;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

@Root
public class Model implements IModel {

	@Attribute
	private int id;
	@Attribute(name = "package")
	private String packageName;
	@Attribute
	private String target;
	@ElementList(entry = "Option", inline = true, required = false)
	private List<ModelOption> optionList = new ArrayList<>();
	@ElementList(entry = "Alias", inline = true, required = false)
	private List<ModelAlias> aliasList = new ArrayList<>();
	@ElementList(entry = "Validator", inline = true, required = false)
	private List<ModelValidator> validatorList = new ArrayList<>();
	@ElementList(entry = "Bean", inline = true, required = false)
	private List<ModelBean> beanList = new ArrayList<>();
	@ElementList(entry = "Enum", inline = true, required = false)
	private List<ModelEnum> enumList = new ArrayList<>();
	@ElementList(entry = "Adapter", inline = true, required = false)
	private List<ModelAdapter> adapterList = new ArrayList<>();
	@ElementList(entry = "Comparator", inline = true, required = false)
	private List<ModelComparator> comparatorList = new ArrayList<>();
	@ElementList(entry = "Interface", inline = true, required = false)
	private List<ModelInterface> interfaceList = new ArrayList<>();
	@ElementList(entry = "ObjectFactory", inline = true, required = false)
	private List<ModelObjectFactory> objectFactoryList = new ArrayList<>();
	@ElementList(entry = "ExecutorSet", inline = true, required = false)
	private List<ModelExecutorSet> executorSetList = new ArrayList<>();
	@ElementList(entry = "DataStore", inline = true, required = false)
	private List<ModelDataStore> dataStoreList = new ArrayList<>();
	@ElementList(entry = "DataSerializer", inline = true, required = false)
	private List<ModelDataSerializer> dataSerializerList = new ArrayList<>();
	@ElementList(entry = "XmlSerializer", inline = true, required = false)
	private List<ModelXmlSerializer> xmlSerializerList = new ArrayList<>();
	@ElementList(entry = "JsonSerializer", inline = true, required = false)
	private List<ModelJsonSerializer> jsonSerializerList = new ArrayList<>();
	@ElementList(entry = "Delegate", inline = true, required = false)
	private List<ModelDelegate> delegateList = new ArrayList<>();
	@ElementList(entry = "ResultSetParser", inline = true, required = false)
	private List<ModelResultSetParser> resultSetParserList = new ArrayList<>();
	@ElementList(entry = "ServletRequestParser", inline = true, required = false)
	private List<ModelServletRequestParser> servletRequestParserList = new ArrayList<>();
	@ElementList(entry = "Annotation", inline = true, required = false)
	private List<ModelAnnotation> annotationList = new ArrayList<>();
	@ElementList(entry = "Builder", inline = true, required = false)
	private List<ModelBuilder> builderList = new ArrayList<>();

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getPackage() {
		return packageName;
	}

	@Override
	public String getTarget() {
		return target;
	}

	@Override
	public List<ModelOption> getOptionList() {
		return optionList;
	}

	@Override
	public List<ModelAlias> getAliasList() {
		return aliasList;
	}

	@Override
	public List<ModelBean> getBeanList() {
		return beanList;
	}

	@Override
	public List<ModelEnum> getEnumList() {
		return enumList;
	}

	@Override
	public List<ModelAdapter> getAdapterList() {
		return adapterList;
	}

	@Override
	public List<ModelComparator> getComparatorList() {
		return comparatorList;
	}

	@Override
	public List<ModelInterface> getInterfaceList() {
		return interfaceList;
	}

	@Override
	public List<ModelObjectFactory> getObjectFactoryList() {
		return objectFactoryList;
	}

	@Override
	public List<ModelExecutorSet> getExecutorSetList() {
		return executorSetList;
	}

	@Override
	public List<ModelValidator> getValidatorList() {
		return validatorList;
	}

	@Override
	public List<ModelDataStore> getDataStoreList() {
		return dataStoreList;
	}

	@Override
	public List<ModelDelegate> getDelegateList() {
		return delegateList;
	}

	@Override
	public List<ModelResultSetParser> getResultSetParserList() {
		return resultSetParserList;
	}

	@Override
	public List<ModelAnnotation> getAnnotationList() {
		return annotationList;
	}

	@Override
	public List<ModelDataSerializer> getDataSerializerList() {
		return dataSerializerList;
	}

	@Override
	public List<ModelXmlSerializer> getXmlSerializerList() {
		return xmlSerializerList;
	}

	@Override
	public List<ModelJsonSerializer> getJsonSerializerList() {
		return jsonSerializerList;
	}

	@Override
	public List<ModelServletRequestParser> getServletRequestParserList() {
		return servletRequestParserList;
	}

	@Override
	public List<ModelBuilder> getBuilderList() {
		return builderList;
	}
}
