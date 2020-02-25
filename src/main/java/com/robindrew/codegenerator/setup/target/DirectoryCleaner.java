package com.robindrew.codegenerator.setup.target;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectoryCleaner {

	private static final Logger log = LoggerFactory.getLogger(DirectoryCleaner.class);

	private final Map<File, byte[]> files = new LinkedHashMap<>();

	public void clean(File directory) {

		// Sanity checks
		verifyDirectory(directory);

		// Delete the contents of the directory
		deleteContents(directory);
	}

	private void deleteContents(File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				delete(file);
			}
		}
	}

	private void delete(File file) {
		if (file.isDirectory()) {
			if (file.getName().equals(".svn")) {
				return;
			}
			deleteContents(file);
		} else {
			forceDelete(file);
		}
	}

	private void forceDelete(File file) {
		// cache(file);

		file.delete();
		while (file.exists()) {
			log.warn("File deleted, but still exists: '" + file + "'");
			file.delete();
		}
	}

	private void verifyDirectory(File directory) {
		if (!directory.exists()) {
			throw new IllegalArgumentException("directory does not exist: '" + directory + "'");
		}
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException("file is not a directory: '" + directory + "'");
		}
	}

}
