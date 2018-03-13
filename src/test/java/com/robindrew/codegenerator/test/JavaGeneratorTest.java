package com.robindrew.codegenerator.test;

import org.junit.Test;

import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerator;
import com.robindrew.codegenerator.setup.ISetup;
import com.robindrew.codegenerator.setup.Setup;
import com.robindrew.codegenerator.util.SimpleResourceReader;

public class JavaGeneratorTest {

	@Test
	public void generatorTest() {
		ISetup setup = new SimpleResourceReader().read("setup.xml", Setup.class);
		JavaModelGenerator generator = new JavaModelGenerator(setup, 1);
		generator.generate();
	}
}
