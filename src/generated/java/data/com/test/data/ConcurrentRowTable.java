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
	public IRow getByRowKey(IRowKey key) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getByRowKey(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsRowKey(IRowKey row) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsRowKey(row);
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
	public void removeRowById(int id) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowById(id);
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
	public void removeRowDataById(int id) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDataById(id);
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
	public void removeRowDimensionsById(int id) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDimensionsById(id);
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
	public void removeRowByName(String name) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowByName(name);
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
	public void removeRowDataByName(String name) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDataByName(name);
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
	public void removeRowDimensionsByName(String name) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDimensionsByName(name);
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
	public void removeRowListByData(byte[] data) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowListByData(data);
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
	public void removeRowDataListByData(byte[] data) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDataListByData(data);
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
	public void removeRowDimensionsListByData(byte[] data) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDimensionsListByData(data);
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
	public void removeRowListByWidth(long width) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowListByWidth(width);
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
	public void removeRowDataListByWidth(long width) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDataListByWidth(width);
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
	public void removeRowDimensionsListByWidth(long width) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDimensionsListByWidth(width);
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
	public void removeRowListByHeight(long height) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowListByHeight(height);
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
	public void removeRowDataListByHeight(long height) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDataListByHeight(height);
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
	public void removeRowDimensionsListByHeight(long height) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDimensionsListByHeight(height);
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
	public void removeRowListByUnit(TimeUnit unit) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowListByUnit(unit);
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
	public void removeRowDataListByUnit(TimeUnit unit) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDataListByUnit(unit);
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
	public void removeRowDimensionsListByUnit(TimeUnit unit) {
		Lock lock = getWriteLock();
		lock.lock();
		try {
			delegate.removeRowDimensionsListByUnit(unit);
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
	public boolean containsRowData(IRowData row) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsRowData(row);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRowData getRowDataByRowNameKey(int id, String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDataByRowNameKey(id, name);
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

	@Override
	public boolean containsRowDimensions(IRowDimensions row) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsRowDimensions(row);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRowDimensions getRowDimensionsByRowNameKey(int id, String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowDimensionsByRowNameKey(id, name);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public IRow getRowByRowNameKey(int id, String name) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.getRowByRowNameKey(id, name);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean containsRowNameKey(IRowNameKey row) {
		Lock lock = getReadLock();
		lock.lock();
		try {
			return delegate.containsRowNameKey(row);
		} finally {
			lock.unlock();
		}
	}
}
