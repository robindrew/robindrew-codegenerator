package com.robindrew.codegenerator.setup.target;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.robindrew.codegenerator.util.LatchedExecutor;

public class TargetFilesystem {

	private final ExecutorService service = Executors.newCachedThreadPool();
	private final Map<File, TargetDirectory> directoryMap = new ConcurrentHashMap<>();

	public void register(List<ITarget> targets) {
		for (ITarget target : targets) {
			register(target);
		}
	}

	public void register(ITarget target) {
		File file = new File(target.getDirectory());
		TargetDirectory directory = directoryMap.get(file);
		if (directory == null) {
			directory = new TargetDirectory(file);
			directoryMap.put(file, directory);
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
