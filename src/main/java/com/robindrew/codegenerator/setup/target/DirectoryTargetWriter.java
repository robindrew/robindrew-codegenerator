package com.robindrew.codegenerator.setup.target;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Stopwatch;
import com.google.common.io.Files;
import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerationException;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaTypedWritable;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;
import com.robindrew.codegenerator.lang.java.type.writable.JavaStringWriter;

public class DirectoryTargetWriter implements ITargetWriter {

	private static final Logger log = LoggerFactory.getLogger(DirectoryTargetWriter.class);

	private final File targetDirectory;

	public DirectoryTargetWriter(String target) {
		if (target.isEmpty()) {
			throw new IllegalArgumentException("target is empty");
		}
		this.targetDirectory = new File(target);
		if (!targetDirectory.exists()) {
			throw new IllegalArgumentException("targetDirectory does not exist: '" + targetDirectory.getAbsolutePath() + "'");
		}
		if (!targetDirectory.isDirectory()) {
			throw new IllegalArgumentException("not a targetDirectory: '" + targetDirectory.getAbsolutePath() + "'");
		}
	}

	@Override
	public void write(IJavaTypedWritable writable) {

		// Write object
		IJavaWriter writer = new JavaStringWriter();
		writable.writeTo(writer);

		// Directory & File
		IJavaType type = writable.getType();
		File directory = createDirectory(type);
		File file = createFile(type, directory);
		writeFile(file, writer);
	}

	private File createFile(IJavaType type, File directory) {
		return new File(directory, type.getSimpleName() + ".java");
	}

	private void writeFile(File file, IJavaWriter writer) {
		try {
			if (file.exists()) {
				throw new IllegalStateException("File already exists: '" + file + "'");
			}
			Stopwatch timer = Stopwatch.createUnstarted();
			timer.start();
			Files.write(writer.toString(), file, Charsets.UTF_8);
			timer.stop();
			log.info("Written: " + file + " in " + timer);
		} catch (IOException e) {
			throw new JavaModelGenerationException(e);
		}
	}

	private File createDirectory(IJavaType type) {
		String packageName = type.getPackageName();
		if (packageName.isEmpty()) {
			return targetDirectory;
		}
		packageName = packageName.replace('.', '/');
		File typeDirectory = new File(targetDirectory, packageName);
		typeDirectory.mkdirs();
		if (!typeDirectory.exists()) {
			throw new IllegalStateException("targetDirectory does not exist: '" + typeDirectory.getAbsolutePath() + "'");
		}
		return typeDirectory;
	}

	@Override
	public void clean() {
		Stopwatch timer = Stopwatch.createUnstarted();
		timer.start();
		new DirectoryCleaner().clean(targetDirectory);
		timer.stop();
		log.info("Cleaned Target Directory: " + targetDirectory + " in " + timer);
	}
}
