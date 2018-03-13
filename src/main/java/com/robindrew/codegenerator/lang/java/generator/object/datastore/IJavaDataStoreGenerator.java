package com.robindrew.codegenerator.lang.java.generator.object.datastore;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;

public interface IJavaDataStoreGenerator {

	IJavaContext getContext();

	JavaModelDataStore getDataStore();

}
