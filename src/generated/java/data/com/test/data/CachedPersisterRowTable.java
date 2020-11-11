package com.test.data;

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
	public IRow getByRowKey(IRowKey key) {
		return cache.getByRowKey(key);
	}

	@Override
	public boolean containsRowKey(IRowKey row) {
		return cache.containsRowKey(row);
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

	@Override
	public void removeByRowKey(IRowKey key) {
		cache.removeByRowKey(key);
		persister.removeByRowKey(key);
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
	public void removeRowById(int id) {
		cache.removeRowById(id);
		persister.removeRowById(id);
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
	public void removeRowDataById(int id) {
		cache.removeRowDataById(id);
		persister.removeRowDataById(id);
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {
		return cache.getRowDimensionsById(id);
	}

	@Override
	public void removeRowDimensionsById(int id) {
		cache.removeRowDimensionsById(id);
		persister.removeRowDimensionsById(id);
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
	public void removeRowByName(String name) {
		cache.removeRowByName(name);
		persister.removeRowByName(name);
	}

	@Override
	public IRowData getRowDataByName(String name) {
		return cache.getRowDataByName(name);
	}

	@Override
	public void removeRowDataByName(String name) {
		cache.removeRowDataByName(name);
		persister.removeRowDataByName(name);
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {
		return cache.getRowDimensionsByName(name);
	}

	@Override
	public void removeRowDimensionsByName(String name) {
		cache.removeRowDimensionsByName(name);
		persister.removeRowDimensionsByName(name);
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
	public void removeRowListByData(byte[] data) {
		cache.removeRowListByData(data);
		persister.removeRowListByData(data);
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {
		return cache.getRowDataListByData(data);
	}

	@Override
	public void removeRowDataListByData(byte[] data) {
		cache.removeRowDataListByData(data);
		persister.removeRowDataListByData(data);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {
		return cache.getRowDimensionsListByData(data);
	}

	@Override
	public void removeRowDimensionsListByData(byte[] data) {
		cache.removeRowDimensionsListByData(data);
		persister.removeRowDimensionsListByData(data);
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
	public void removeRowListByWidth(long width) {
		cache.removeRowListByWidth(width);
		persister.removeRowListByWidth(width);
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
	public void removeRowDataListByWidth(long width) {
		cache.removeRowDataListByWidth(width);
		persister.removeRowDataListByWidth(width);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {
		return cache.getRowDimensionsListByWidth(width);
	}

	@Override
	public void removeRowDimensionsListByWidth(long width) {
		cache.removeRowDimensionsListByWidth(width);
		persister.removeRowDimensionsListByWidth(width);
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
	public void removeRowListByHeight(long height) {
		cache.removeRowListByHeight(height);
		persister.removeRowListByHeight(height);
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
	public void removeRowDataListByHeight(long height) {
		cache.removeRowDataListByHeight(height);
		persister.removeRowDataListByHeight(height);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {
		return cache.getRowDimensionsListByHeight(height);
	}

	@Override
	public void removeRowDimensionsListByHeight(long height) {
		cache.removeRowDimensionsListByHeight(height);
		persister.removeRowDimensionsListByHeight(height);
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
	public void removeRowListByUnit(TimeUnit unit) {
		cache.removeRowListByUnit(unit);
		persister.removeRowListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {
		return cache.getRowDataListByUnit(unit);
	}

	@Override
	public void removeRowDataListByUnit(TimeUnit unit) {
		cache.removeRowDataListByUnit(unit);
		persister.removeRowDataListByUnit(unit);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {
		return cache.getRowDimensionsListByUnit(unit);
	}

	@Override
	public void removeRowDimensionsListByUnit(TimeUnit unit) {
		cache.removeRowDimensionsListByUnit(unit);
		persister.removeRowDimensionsListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataList() {
		return cache.getRowDataList();
	}

	@Override
	public boolean containsRowData(IRowData row) {
		return cache.containsRowData(row);
	}

	@Override
	public List<IRow> getByRowData(byte[] data) {
		return cache.getByRowData(data);
	}

	@Override
	public IRowData getRowDataByRowNameKey(int id, String name) {
		return cache.getRowDataByRowNameKey(id, name);
	}

	@Override
	public void removeRowDataByRowNameKey(int id, String name) {
		cache.removeRowDataByRowNameKey(id, name);
		persister.removeRowDataByRowNameKey(id, name);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {
		return cache.getRowDimensionsList();
	}

	@Override
	public boolean containsRowDimensions(IRowDimensions row) {
		return cache.containsRowDimensions(row);
	}

	@Override
	public List<IRow> getByRowDimensions(long width, long height) {
		return cache.getByRowDimensions(width, height);
	}

	@Override
	public IRowDimensions getRowDimensionsByRowNameKey(int id, String name) {
		return cache.getRowDimensionsByRowNameKey(id, name);
	}

	@Override
	public void removeRowDimensionsByRowNameKey(int id, String name) {
		cache.removeRowDimensionsByRowNameKey(id, name);
		persister.removeRowDimensionsByRowNameKey(id, name);
	}

	@Override
	public IRow getRowByRowNameKey(int id, String name) {
		return cache.getRowByRowNameKey(id, name);
	}

	@Override
	public boolean containsRowNameKey(IRowNameKey row) {
		return cache.containsRowNameKey(row);
	}

	@Override
	public void removeRowByRowNameKey(int id, String name) {
		cache.removeRowByRowNameKey(id, name);
		persister.removeRowByRowNameKey(id, name);
	}
}
