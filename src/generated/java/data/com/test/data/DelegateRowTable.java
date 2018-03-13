package com.test.data;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class DelegateRowTable implements IRowTable {

	private final IRowTable delegate;

	public DelegateRowTable(IRowTable delegate) {
		if (delegate == null) {
			throw new NullPointerException("delegate");
		}
		this.delegate = delegate;
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
		return delegate.size();
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public boolean exists() {
		return delegate.exists();
	}

	@Override
	public boolean contains(IRow element) {
		return delegate.contains(element);
	}

	@Override
	public List<IRow> getAll() {
		return delegate.getAll();
	}

	@Override
	public IRow get(int id) {
		return delegate.get(id);
	}

	@Override
	public IRow get(IRowKey key) {
		return delegate.get(key);
	}

	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public void create() {
		delegate.create();
	}

	@Override
	public void destroy() {
		delegate.destroy();
	}

	@Override
	public void add(IRow element) {
		delegate.add(element);
	}

	@Override
	public void set(IRow element) {
		delegate.set(element);
	}

	@Override
	public void remove(IRow element) {
		delegate.remove(element);
	}

	@Override
	public void addAll(Collection<? extends IRow> elements) {
		delegate.addAll(elements);
	}

	@Override
	public void setAll(Collection<? extends IRow> elements) {
		delegate.setAll(elements);
	}

	@Override
	public void removeAll(Collection<? extends IRow> elements) {
		delegate.removeAll(elements);
	}

	@Override
	public void addAuto(IRow element) {
		delegate.addAuto(element);
	}

	@Override
	public boolean containsId(int id) {
		return delegate.containsId(id);
	}

	@Override
	public IRow getRowById(int id) {
		return delegate.getRowById(id);
	}

	@Override
	public List<IRow> getRowListBetweenIds(Integer from, Integer to) {
		return delegate.getRowListBetweenIds(from, to);
	}

	@Override
	public IRowData getRowDataById(int id) {
		return delegate.getRowDataById(id);
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {
		return delegate.getRowDimensionsById(id);
	}

	@Override
	public boolean containsName(String name) {
		return delegate.containsName(name);
	}

	@Override
	public IRow getRowByName(String name) {
		return delegate.getRowByName(name);
	}

	@Override
	public IRowData getRowDataByName(String name) {
		return delegate.getRowDataByName(name);
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {
		return delegate.getRowDimensionsByName(name);
	}

	@Override
	public boolean containsData(byte[] data) {
		return delegate.containsData(data);
	}

	@Override
	public List<IRow> getRowListByData(byte[] data) {
		return delegate.getRowListByData(data);
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {
		return delegate.getRowDataListByData(data);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {
		return delegate.getRowDimensionsListByData(data);
	}

	@Override
	public boolean containsWidth(long width) {
		return delegate.containsWidth(width);
	}

	@Override
	public List<IRow> getRowListByWidth(long width) {
		return delegate.getRowListByWidth(width);
	}

	@Override
	public List<IRow> getRowListBetweenWidths(Long from, Long to) {
		return delegate.getRowListBetweenWidths(from, to);
	}

	@Override
	public List<IRowData> getRowDataListByWidth(long width) {
		return delegate.getRowDataListByWidth(width);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {
		return delegate.getRowDimensionsListByWidth(width);
	}

	@Override
	public boolean containsHeight(long height) {
		return delegate.containsHeight(height);
	}

	@Override
	public List<IRow> getRowListByHeight(long height) {
		return delegate.getRowListByHeight(height);
	}

	@Override
	public List<IRow> getRowListBetweenHeights(Long from, Long to) {
		return delegate.getRowListBetweenHeights(from, to);
	}

	@Override
	public List<IRowData> getRowDataListByHeight(long height) {
		return delegate.getRowDataListByHeight(height);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {
		return delegate.getRowDimensionsListByHeight(height);
	}

	@Override
	public boolean containsUnit(TimeUnit unit) {
		return delegate.containsUnit(unit);
	}

	@Override
	public List<IRow> getRowListByUnit(TimeUnit unit) {
		return delegate.getRowListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {
		return delegate.getRowDataListByUnit(unit);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {
		return delegate.getRowDimensionsListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataList() {
		return delegate.getRowDataList();
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {
		return delegate.getRowDimensionsList();
	}
}
