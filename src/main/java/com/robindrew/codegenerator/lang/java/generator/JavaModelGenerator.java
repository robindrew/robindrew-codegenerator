package com.robindrew.codegenerator.lang.java.generator;

import static java.util.Collections.synchronizedList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModelBuilder;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.executor.JavaModelExecutorSet;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.JavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.model.option.OptionProcessor;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelJsonSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelXmlSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.servlet.JavaModelServletRequestParser;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.generator.object.IJavaGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.adapter.JavaAdapterGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.alias.JavaAliasGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.annotation.JavaAnnotationGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.bean.JavaBeanGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.comparator.JavaComparatorGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.context.JavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.delegate.JavaDelegateGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.eenum.JavaEnumGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.executor.JavaExecutorSetGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.factory.JavaObjectFactoryGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.iinterface.JavaInterfaceGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.data.JavaDataSerializerGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.json.JavaJsonSerializerGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.serializer.generator.xml.JavaXmlSerializerGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.servlet.JavaServletRequestParserGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.sql.JavaResultSetParserGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.stats.IJavaGeneratorStats;
import com.robindrew.codegenerator.lang.java.generator.object.stats.JavaGeneratorStats;
import com.robindrew.codegenerator.lang.java.generator.object.validator.JavaValidatorGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.IJavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.JavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.type.interfacemap.IJavaExtendsMap;
import com.robindrew.codegenerator.lang.java.type.interfacemap.JavaExtendsMap;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;
import com.robindrew.codegenerator.lang.java.type.resolver.JavaTypeResolver;
import com.robindrew.codegenerator.model.IModel;
import com.robindrew.codegenerator.model.object.adapter.ModelAdapter;
import com.robindrew.codegenerator.model.object.alias.ModelAlias;
import com.robindrew.codegenerator.model.object.annotation.ModelAnnotation;
import com.robindrew.codegenerator.model.object.comparator.ModelComparator;
import com.robindrew.codegenerator.model.object.delegate.ModelDelegate;
import com.robindrew.codegenerator.model.object.factory.ModelObjectFactory;
import com.robindrew.codegenerator.model.object.id.ModelIdRegistry;
import com.robindrew.codegenerator.model.reader.IModelReader;
import com.robindrew.codegenerator.model.reader.ModelReaderLookup;
import com.robindrew.codegenerator.setup.ISetup;
import com.robindrew.codegenerator.setup.source.ISource;
import com.robindrew.codegenerator.setup.target.ITarget;
import com.robindrew.codegenerator.util.LatchedExecutor;

public class JavaModelGenerator {

	private static final Logger log = LoggerFactory.getLogger(JavaModelGenerator.class);

	private final ExecutorService service;
	private final IJavaTypeResolver resolver;
	private final IJavaExtendsMap extendsMap = new JavaExtendsMap();
	private final IJavaValidatorLookup validatorLookup = new JavaValidatorLookup();
	private final ISetup setup;

	public JavaModelGenerator(ISetup setup, int threads) {
		this(setup, Executors.newFixedThreadPool(threads));
	}

	public JavaModelGenerator(ISetup setup, ExecutorService service) {
		if (service == null) {
			throw new NullPointerException("service");
		}
		if (setup == null) {
			throw new NullPointerException("setup");
		}
		this.service = service;
		this.setup = setup;

		// Prepare the resolver
		resolver = new JavaTypeResolver().registerCommonTypes();
	}

	public void generate() {
		log.info("[Generating Java Classes]");
		Stopwatch timer = Stopwatch.createStarted();

		IJavaGeneratorStats generatorStats = new JavaGeneratorStats();
		try {

			// Clean directories
			cleanTargetDirectories();

			// Model set
			IJavaModelSet modelSet = readModelSet();

			// Read models and register types
			List<IJavaGenerator> generators = readAndRegisterTypes(modelSet, generatorStats);

			// Secondary types
			registerSecondaryTypes(generators);

			// Verify types
			verifyTypes(generators);

			// Generate!
			generate(generators);

			// Finished
			timer.stop();
			int classCount = generatorStats.getGeneratedClassCount();
			log.info("[Generated Java Classes] {} classes generated in {}", classCount, timer);

		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			throw new JavaModelGenerationException(e);
		}
	}

	private IJavaModelSet readModelSet() {
		final ModelIdRegistry registry = new ModelIdRegistry();
		List<IModelReader> readerList = getReaderList();

		final List<JavaModel> modelList = synchronizedList(new ArrayList<JavaModel>());
		LatchedExecutor executor = new LatchedExecutor(service, readerList);
		for (final IModelReader reader : readerList) {
			executor.submit(() -> {
				IModel model = reader.readModel();

				new OptionProcessor().processOptions(model);

				registry.setIds(model);

				JavaModel javaModel = new JavaModelBuilder(model).build();
				modelList.add(javaModel);
			});
		}

		executor.await();
		return new JavaModelSet(modelList);
	}

	private void cleanTargetDirectories() {
		log.info("[Cleaning Target Directories]");
		Stopwatch timer = Stopwatch.createStarted();

		List<ITarget> list = setup.getTargetList();
		LatchedExecutor executor = new LatchedExecutor(service, list);
		for (final ITarget target : list) {
			executor.submit(() -> target.getWriter().clean());
		}
		executor.await();

		// Finished
		timer.stop();
		log.info("[Cleaning Target Directories] completed in {}", timer);
	}

	private void generate(List<IJavaGenerator> generators) {
		// Asynchronous
		LatchedExecutor executor = new LatchedExecutor(service, generators);
		for (final IJavaGenerator generator : generators) {
			executor.submit(() -> generator.generate());
		}
		executor.await();
	}

	private void registerSecondaryTypes(List<IJavaGenerator> generators) {
		// Asynchronous
		LatchedExecutor executor = new LatchedExecutor(service, generators);
		for (final IJavaGenerator generator : generators) {
			executor.submit(() -> generator.registerSecondaryTypes());
		}
		executor.await();
	}

	private void verifyTypes(List<IJavaGenerator> generators) {
		// Asynchronous
		LatchedExecutor executor = new LatchedExecutor(service, generators);
		for (final IJavaGenerator generator : generators) {
			executor.submit(() -> generator.verifyReferencedTypes());
		}
		executor.await();
	}

	private List<IJavaGenerator> readAndRegisterTypes(final IJavaModelSet modelSet, final IJavaGeneratorStats generatorStats) {

		// Thread safe list please!
		final List<IJavaGenerator> generators = synchronizedList(new ArrayList<IJavaGenerator>());

		// Asynchronous
		List<JavaModel> modelList = modelSet.getModelList();
		LatchedExecutor executor = new LatchedExecutor(service, modelList);
		for (final JavaModel model : modelList) {
			executor.submit(() -> {
				List<IJavaGenerator> list = readAndRegisterTypes(modelSet, model, generatorStats);
				generators.addAll(list);
			});
		}
		executor.await();
		return generators;
	}

	private List<IModelReader> getReaderList() {
		ModelReaderLookup lookup = new ModelReaderLookup();
		for (ISource source : setup.getSourceList()) {
			lookup.add(source.getName());
		}
		return lookup.getReaderList();
	}

	private List<IJavaGenerator> readAndRegisterTypes(IJavaModelSet modelSet, JavaModel javaModel, IJavaGeneratorStats generatorStats) {
		List<IJavaGenerator> generators = new ArrayList<IJavaGenerator>();

		IModel model = javaModel.get();
		IJavaContext context = new JavaContext(modelSet, resolver, extendsMap, validatorLookup, generatorStats);

		// Aliases
		for (ModelAlias alias : model.getAliasList()) {
			IJavaGenerator generator = new JavaAliasGenerator(setup, context, javaModel, alias);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Validator
		for (JavaModelValidator validator : javaModel.getValidatorList()) {
			IJavaGenerator generator = new JavaValidatorGenerator(setup, context, javaModel, validator);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Enums
		for (JavaModelEnum eenum : javaModel.getEnumList()) {
			IJavaGenerator generator = new JavaEnumGenerator(setup, context, javaModel, eenum);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Beans
		for (JavaModelBean bean : javaModel.getBeanList()) {
			IJavaGenerator generator = new JavaBeanGenerator(setup, context, javaModel, bean);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Comparators
		for (ModelComparator comparator : model.getComparatorList()) {
			IJavaGenerator generator = new JavaComparatorGenerator(setup, context, javaModel, comparator);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Adaptors
		for (ModelAdapter adapter : model.getAdapterList()) {
			IJavaGenerator generator = new JavaAdapterGenerator(setup, context, javaModel, adapter);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Interfaces
		for (JavaModelInterface iinterface : javaModel.getInterfaceList()) {
			IJavaGenerator generator = new JavaInterfaceGenerator(setup, context, javaModel, iinterface);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Factory
		for (ModelObjectFactory factory : model.getObjectFactoryList()) {
			IJavaGenerator generator = new JavaObjectFactoryGenerator(setup, context, javaModel, factory);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Executor Set
		for (JavaModelExecutorSet executorSet : javaModel.getExecutorSetList()) {
			IJavaGenerator generator = new JavaExecutorSetGenerator(setup, context, javaModel, executorSet);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Data Stores
		for (JavaModelDataStore dataStore : javaModel.getDataStoreList()) {
			IJavaGenerator generator = new JavaDataStoreGenerator(setup, context, javaModel, dataStore);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Delegates
		for (ModelDelegate delegate : model.getDelegateList()) {
			IJavaGenerator generator = new JavaDelegateGenerator(setup, context, javaModel, delegate);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// ResultSet Parsers
		for (JavaModelResultSetParser parser : javaModel.getResultSetParserList()) {
			IJavaGenerator generator = new JavaResultSetParserGenerator(setup, context, javaModel, parser);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Servlet Request Parsers
		for (JavaModelServletRequestParser parser : javaModel.getServletRequestParserList()) {
			IJavaGenerator generator = new JavaServletRequestParserGenerator(setup, context, javaModel, parser);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Data Serializer
		for (JavaModelDataSerializer serializer : javaModel.getDataSerializerList()) {
			IJavaGenerator generator = new JavaDataSerializerGenerator(setup, context, javaModel, serializer);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// XML Serializer
		for (JavaModelXmlSerializer serializer : javaModel.getXmlSerializerList()) {
			IJavaGenerator generator = new JavaXmlSerializerGenerator(setup, context, javaModel, serializer);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// JSON Serializer
		for (JavaModelJsonSerializer serializer : javaModel.getJsonSerializerList()) {
			IJavaGenerator generator = new JavaJsonSerializerGenerator(setup, context, javaModel, serializer);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		// Annotations
		for (ModelAnnotation annot : model.getAnnotationList()) {
			IJavaGenerator generator = new JavaAnnotationGenerator(setup, context, javaModel, annot);
			generator.registerPrimaryTypes();
			generators.add(generator);
		}

		return generators;
	}

}
