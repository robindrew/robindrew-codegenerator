package com.robindrew.codegenerator.util;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

import com.robindrew.codegenerator.lang.java.generator.JavaModelGenerationException;

public class LatchedExecutor {

	private final CountDownLatch latch;
	private final ExecutorService service;
	private final AtomicReference<Throwable> throwable = new AtomicReference<Throwable>();

	public LatchedExecutor(ExecutorService service, Collection<?> collection) {
		if (collection.isEmpty()) {
			throw new IllegalArgumentException("collection is empty");
		}
		if (service == null) {
			throw new NullPointerException("service");
		}
		this.service = service;
		this.latch = new CountDownLatch(collection.size());
	}

	public void submit(final Runnable runnable) {
		service.submit(new Runnable() {

			@Override
			public void run() {
				try {
					runnable.run();
				} catch (Throwable t) {
					registerThrowable(t);
				} finally {
					latch.countDown();
				}
			}

		});
	}

	private void registerThrowable(Throwable t) {
		// Only register the first throwable
		throwable.compareAndSet(null, t);
	}

	public void await() {
		try {
			latch.await();
		} catch (Exception e) {
			throw new JavaModelGenerationException(e);
		}

		// Failed?
		if (throwable.get() != null) {
			throw new JavaModelGenerationException(throwable.get());
		}
	}
}
