package com.robindrew.codegenerator.lang.java.generator.object.executor.method;

import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorLocator;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;

public class ExecutorSetConstructor extends JavaConstructor {

	public ExecutorSetConstructor(String name) {
		super(name);
		getParameters().add("locator", IBeanExecutorLocator.class);

		IJavaCodeLines contents = new JavaCodeLines();
		new NullPointerConstraint("locator").appendTo(contents);
		contents.line("this.locator = locator;");
		setContents(contents);
	}

}
