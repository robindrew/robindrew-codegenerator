package com.robindrew.codegenerator.lang.java.generator.object.servlet;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import com.robindrew.codegenerator.api.servlet.IServletRequestAdapter;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.servlet.JavaModelServletRequestParser;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.generator.object.servlet.method.AdaptMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaServletRequestParserGenerator extends JavaObjectGenerator {

	private final JavaModelServletRequestParser parser;

	private IJavaType parserType = null;
	private JavaModelBean bean = null;

	public JavaServletRequestParserGenerator(ISetup setup, IJavaContext context, JavaModel model, JavaModelServletRequestParser parser) {
		super(setup, context, model, parser.get());
		this.parser = parser;
	}

	public JavaModelServletRequestParser getServletRequestParser() {
		return parser;
	}

	@Override
	public void registerPrimaryTypes() {
		parserType = registerJavaType(parser.getName(), CLASS);
		parser.setType(parserType);
	}

	@Override
	public void registerSecondaryTypes() {

		// Result bean type and link
		IJavaType beanType = resolve(parser.get().getType());
		bean = getContext().getModelSet().getBean(beanType, false);
		bean.setServletRequestParser(parser);
	}

	@Override
	public void verifyReferencedTypes() {
	}

	@Override
	public void generate() {

		// For each comparator we generate a class
		JavaObject object = new JavaObject(parserType);
		object.addExtends(getBeanServletRequestParserInterface());

		// Method
		addBlocks(object);

		// Done!
		write(object);

	}

	private IJavaType getBeanServletRequestParserInterface() {
		IJavaType parser = resolve(IServletRequestAdapter.class, 1);
		return new JavaTypeWithGenerics(parser, bean.getInterfaceType());
	}

	private void addBlocks(JavaObject object) {
		object.addBlock(new AdaptMethod(bean).setOverride());
	}
}
