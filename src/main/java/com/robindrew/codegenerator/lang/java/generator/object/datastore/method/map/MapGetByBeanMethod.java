package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetByBeanMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;

public class MapGetByBeanMethod extends GetByBeanMethod {

	public MapGetByBeanMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();
		IJavaCodeLines code = new JavaCodeLines();
		new NullPointerConstraint("key").appendTo(code);
		code.line("return map.get(key);");
		setContents(code);
	}

}
