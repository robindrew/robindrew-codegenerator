package com.robindrew.codegenerator.lang.java.generator.object;

/**
 * A Java Generator. Does not always produce objects, but may register types or other operations.
 */
public interface IJavaGenerator {

	/**
	 * Before generating we must first register java types (first pass).
	 */
	void registerPrimaryTypes();

	/**
	 * Before generating we must first register java types (second pass).
	 */
	void registerSecondaryTypes();

	/**
	 * Now we can verify types referenced actually exist.
	 */
	void verifyReferencedTypes();

	/**
	 * We are ready, generate the code!
	 */
	void generate();

}
