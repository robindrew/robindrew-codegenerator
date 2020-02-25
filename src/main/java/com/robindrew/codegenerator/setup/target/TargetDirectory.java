package com.robindrew.codegenerator.setup.target;

import static com.google.common.base.Charsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.google.common.io.Files;
import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerationException;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaTypedWritable;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;
import com.robindrew.codegenerator.lang.java.type.writable.JavaStringWriter;

public class TargetDirectory implements ITargetWriter {

	private static final Logger log = LoggerFactory.getLogger(TargetDirectory.class);

	private final File directory;
	private final Map<File, Boolean> cachedFileMap = new ConcurrentHashMap<>();
	private final Map<String, ITarget> targetMap = new ConcurrentHashMap<>();

	public TargetDirectory(File directory) {
		this.directory = directory;
		if (!directory.exists()) {
			throw new IllegalArgumentException("directory does not exist: " + directory);
		}

		log.info("[Directory] {}", directory.getAbsolutePath());
	}

	public String getName() {
		return directory.getAbsolutePath();
	}

	public void register(ITarget target) {
		if (target == null) {
			throw new NullPointerException();
		}
		targetMap.put(target.getName(), target);
	}

	public void beforeGeneration() {
		Stopwatch timer = Stopwatch.createUnstarted();
		timer.start();
		cacheFiles(directory);
		timer.stop();
		log.info("Cached {} files in {}", cachedFileMap.size(), timer);
	}

	public void afterGeneration() {
		Stopwatch timer = Stopwatch.createUnstarted();
		timer.start();
		int count = 0;
		for (Entry<File, Boolean> entry : cachedFileMap.entrySet()) {
			if (!entry.getValue()) {
				forceDelete(entry.getKey());
				count++;
			}
		}
		timer.stop();
		log.info("Deleted {} files from {} in {}", count, directory, timer);
	}

	private void forceDelete(File file) {
		file.delete();
		while (file.exists()) {
			log.warn("File deleted, but still exists: '" + file + "'");
			file.delete();
		}
	}

	private void cacheFiles(File file) {
		if (file.isDirectory()) {
			for (File child : com.robindrew.common.io.Files.listContents(file)) {
				cacheFiles(child);
			}
		} else {
			cachedFileMap.put(file, false);
		}
	}

	@Override
	public void clean() {

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
		writeFile(file, writer.toString().getBytes(UTF_8));
	}

	private File createFile(IJavaType type, File directory) {
		return new File(directory, type.getSimpleName() + ".java");
	}

	private void writeFile(File file, byte[] newContent) {
		cachedFileMap.put(file, true);
		try {
			// Does the file already exist with matching content?
			if (file.exists()) {
				byte[] oldContent = Files.toByteArray(file);
				if (Arrays.equals(oldContent, newContent)) {
					return;
				}
			}

			Stopwatch timer = Stopwatch.createUnstarted();
			timer.start();
			Files.write(newContent, file);
			timer.stop();
			log.info("Written: {} in {}", file, timer);
		} catch (IOException e) {
			throw new JavaModelGenerationException(e);
		}
	}

	private File createDirectory(IJavaType type) {
		String packageName = type.getPackageName();
		if (packageName.isEmpty()) {
			return directory;
		}
		packageName = packageName.replace('.', '/');
		File typeDirectory = new File(directory, packageName);
		typeDirectory.mkdirs();
		if (!typeDirectory.exists()) {
			throw new IllegalStateException("targetDirectory does not exist: '" + typeDirectory.getAbsolutePath() + "'");
		}
		return typeDirectory;
	}

}
