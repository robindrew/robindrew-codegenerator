package com.test.data;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ConcurrentRowTable implements IRowTable {

	private final IRowTable delegate;

	public ConcurrentRowTable(IRowTable delegate) {
		if (delegate == null) {
			throw new NullPointerException("delegate");
		}
		this.delegate = delegate;
	}

	/**
	 * Getter for the delegate field.
	 * @return the value of the delegate field.
	 */
	public IRowTable getDelegate() {
		return delegate;
	}

	@Override
	public Lock getReadLock() {
		return delegate.getReadLock();
	}

	@Override
	public Lock getWriteLock() {
		return delegate.getWriteLock();
	}

	@Override
	public int size() {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.size();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.isEmpty();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean exists() {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.exists();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean contains(IRow element) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.contains(element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getAll() {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getAll();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRow get(int id) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.get(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRow get(IRowKey key) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.get(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void clear() {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.clear();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void create() {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.create();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void destroy() {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.destroy();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void add(IRow element) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.add(element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void set(IRow element) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.set(element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void remove(IRow element) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.remove(element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void addAll(Collection<? extends IRow> elements) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.addAll(elements);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void setAll(Collection<? extends IRow> elements) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.setAll(elements);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void removeAll(Collection<? extends IRow> elements) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeAll(elements);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void addAuto(IRow element) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.addAuto(element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsId(int id) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsId(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRow getRowById(int id) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowById(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListBetweenIds(Integer from, Integer to) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListBetweenIds(from, to);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRowData getRowDataById(int id) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataById(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsById(id);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsName(String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsName(name);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRow getRowByName(String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowByName(name);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRowData getRowDataByName(String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataByName(name);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsByName(name);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsData(byte[] data) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsData(data);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListByData(byte[] data) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListByData(data);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataListByData(data);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsListByData(data);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsWidth(long width) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsWidth(width);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListByWidth(long width) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListByWidth(width);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListBetweenWidths(Long from, Long to) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListBetweenWidths(from, to);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowData> getRowDataListByWidth(long width) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataListByWidth(width);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsListByWidth(width);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsHeight(long height) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsHeight(height);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListByHeight(long height) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListByHeight(height);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListBetweenHeights(Long from, Long to) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListBetweenHeights(from, to);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowData> getRowDataListByHeight(long height) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataListByHeight(height);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsListByHeight(height);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsUnit(TimeUnit unit) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsUnit(unit);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRow> getRowListByUnit(TimeUnit unit) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowListByUnit(unit);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataListByUnit(unit);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsListByUnit(unit);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowData> getRowDataList() {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataList();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsList();
		} finally {
			lock.unlock();
		}
	}
}
