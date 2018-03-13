package com.robindrew.codegenerator.lang.java.generator.object.stats;

import java.util.concurrent.atomic.AtomicInteger;

public class JavaGeneratorStats implements IJavaGeneratorStats {

	private final AtomicInteger generatedClassCount = new AtomicInteger(0);

	@Override
	public int getGeneratedClassCount() {
		return generatedClassCount.get();
	}

	@Override
	public void generatedClass() {
		generatedClassCount.incrementAndGet();
	}

}
