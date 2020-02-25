package com.robindrew.codegenerator.setup.target;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robindrew.codegenerator.util.LatchedExecutor;

public class TargetFilesystem {

	private static final Logger log = LoggerFactory.getLogger(TargetFilesystem.class);

	private final ExecutorService service = Executors.newCachedThreadPool();
	private final Map<String, TargetDirectory> directoryMap = new TreeMap<>();

	public void register(List<ITarget> targets) {
		for (ITarget target : targets) {
			register(target);
		}
	}

	public void register(ITarget target) {
		TargetDirectory directory = directoryMap.get(target.getDirectory());
		if (directory == null) {
			directory = new TargetDirectory(target.getDirectory());
			directoryMap.put(directory.getName(), directory);
		}
		directory.register(target);
		target.setWriter(directory);
	}

	public void beforeGeneration() {
		LatchedExecutor executor = new LatchedExecutor(service, directoryMap.size());
		for (TargetDirectory directory : directoryMap.values()) {
			executor.submit(() -> directory.beforeGeneration());
		}
		executor.await();

	}

	public void afterGeneration() {
		LatchedExecutor executor = new LatchedExecutor(service, directoryMap.size());
		for (TargetDirectory directory : directoryMap.values()) {
			executor.submit(() -> directory.afterGeneration());
		}
		executor.await();
	}

}
