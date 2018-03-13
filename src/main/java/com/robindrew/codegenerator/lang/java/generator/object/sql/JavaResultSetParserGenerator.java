package com.robindrew.codegenerator.lang.java.generator.object.sql;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.sql.method.AdaptMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaResultSetParserGenerator extends JavaObjectGenerator {

	private final JavaModelResultSetParser parser;

	private IJavaType parserType = null;
	private JavaModelBean bean = null;

	public JavaResultSetParserGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelResultSetParser parser) {
		super(setup, context, model, parser.get());
		this.parser = parser;
	}

	public JavaModelResultSetParser getResultSetParser() {
		return parser;
	}

	@Override
	public void registerPrimaryTypes() {
		parserType = registerJavaType(getResultSetParser().getName(), CLASS);
		getResultSetParser().setType(parserType);
	}

	@Override
	public void registerSecondaryTypes() {

		// Result bean type and link
		IJavaType beanType = resolve(getResultSetParser().get().getType());
		bean = getContext().getModelSet().getBean(beanType, false);
		bean.setResultSetParser(parser);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {

		// For each comparator we generate a class
		JavaObject object = new JavaObject(parserType);
		object.addExtends(getBeanResultSetParserInterface());

		// Method
		addBlocks(object);

		// Done!
		write(object);

	}

	private IJavaType getBeanResultSetParserInterface() {
		IJavaType parser = resolve(IResultSetAdapter.class, 1);
		return new JavaTypeWithGenerics(parser, bean.getInterfaceType());
	}

	private void addBlocks(JavaObject object) {
		object.addBlock(new AdaptMethod(bean).setOverride());
	}
}
