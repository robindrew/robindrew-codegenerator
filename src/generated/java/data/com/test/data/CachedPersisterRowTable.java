package com.test.data;

import com.robindrew.codegenerator.api.datastore.IDataStore;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class CachedPersisterRowTable implements IRowTable {

	private final IRowTable cache;
	private final IRowTable persister;

	public CachedPersisterRowTable(IRowTable cache, IRowTable persister) {
		this.cache = cache;
		this.persister = persister;
	}

	@Override
	public Lock getReadLock() {
		return cache.getReadLock();
	}

	@Override
	public Lock getWriteLock() {
		return cache.getWriteLock();
	}

	@Override
	public int size() {
		return cache.size();
	}

	@Override
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	@Override
	public boolean exists() {
		return cache.exists() && persister.exists();
	}

	@Override
	public boolean contains(IRow element) {
		return cache.contains(element);
	}

	@Override
	public List<IRow> getAll() {
		return cache.getAll();
	}

	@Override
	public IRow get(int id) {
		return cache.get(id);
	}

	@Override
	public IRow get(IRowKey key) {
		return cache.get(key);
	}

	@Override
	public void clear() {
		cache.clear();
		persister.clear();
	}

	@Override
	public void create() {
		if (!cache.exists()) {
			cache.create();
		}
		if (!persister.exists()) {
			persister.create();
		}

		// Populate Cache
		cache.clear();
		cache.addAll(persister.getAll());
	}

	@Override
	public void destroy() {
		if (cache.exists()) {
			cache.destroy();
		}
		
		if (persister.exists()) {
			persister.destroy();
		}
	}

	@Override
	public void add(IRow element) {
		cache.add(element);
		persister.add(element);
	}

	@Override
	public void set(IRow element) {
		cache.set(element);
		persister.set(element);
	}

	@Override
	public void remove(IRow element) {
		cache.remove(element);
		persister.remove(element);
	}

	@Override
	public void addAll(Collection<? extends IRow> elements) {
		cache.addAll(elements);
		persister.addAll(elements);
	}

	@Override
	public void setAll(Collection<? extends IRow> elements) {
		cache.setAll(elements);
		persister.setAll(elements);
	}

	@Override
	public void removeAll(Collection<? extends IRow> elements) {
		cache.removeAll(elements);
		persister.removeAll(elements);
	}

	/**
	 * Note: this method will not update the element parameter correctly
	 * if wrapped in a copy-on-write {@link IDataStore}.
	 */
	@Override
	public void addAuto(IRow element) {
		cache.addAuto(element);
		persister.add(element);
	}

	@Override
	public boolean containsId(int id) {
		return cache.containsId(id);
	}

	@Override
	public IRow getRowById(int id) {
		return cache.getRowById(id);
	}

	@Override
	public List<IRow> getRowListBetweenIds(Integer from, Integer to) {
		return cache.getRowListBetweenIds(from, to);
	}

	@Override
	public IRowData getRowDataById(int id) {
		return cache.getRowDataById(id);
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {
		return cache.getRowDimensionsById(id);
	}

	@Override
	public boolean containsName(String name) {
		return cache.containsName(name);
	}

	@Override
	public IRow getRowByName(String name) {
		return cache.getRowByName(name);
	}

	@Override
	public IRowData getRowDataByName(String name) {
		return cache.getRowDataByName(name);
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {
		return cache.getRowDimensionsByName(name);
	}

	@Override
	public boolean containsData(byte[] data) {
		return cache.containsData(data);
	}

	@Override
	public List<IRow> getRowListByData(byte[] data) {
		return cache.getRowListByData(data);
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {
		return cache.getRowDataListByData(data);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {
		return cache.getRowDimensionsListByData(data);
	}

	@Override
	public boolean containsWidth(long width) {
		return cache.containsWidth(width);
	}

	@Override
	public List<IRow> getRowListByWidth(long width) {
		return cache.getRowListByWidth(width);
	}

	@Override
	public List<IRow> getRowListBetweenWidths(Long from, Long to) {
		return cache.getRowListBetweenWidths(from, to);
	}

	@Override
	public List<IRowData> getRowDataListByWidth(long width) {
		return cache.getRowDataListByWidth(width);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {
		return cache.getRowDimensionsListByWidth(width);
	}

	@Override
	public boolean containsHeight(long height) {
		return cache.containsHeight(height);
	}

	@Override
	public List<IRow> getRowListByHeight(long height) {
		return cache.getRowListByHeight(height);
	}

	@Override
	public List<IRow> getRowListBetweenHeights(Long from, Long to) {
		return cache.getRowListBetweenHeights(from, to);
	}

	@Override
	public List<IRowData> getRowDataListByHeight(long height) {
		return cache.getRowDataListByHeight(height);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {
		return cache.getRowDimensionsListByHeight(height);
	}

	@Override
	public boolean containsUnit(TimeUnit unit) {
		return cache.containsUnit(unit);
	}

	@Override
	public List<IRow> getRowListByUnit(TimeUnit unit) {
		return cache.getRowListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {
		return cache.getRowDataListByUnit(unit);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {
		return cache.getRowDimensionsListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataList() {
		return cache.getRowDataList();
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {
		return cache.getRowDimensionsList();
	}
}
