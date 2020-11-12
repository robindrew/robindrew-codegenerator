package com.test.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class CopyRowTable implements IRowTable {

	private final boolean copyOnRead;
	private final boolean copyOnWrite;
	private final IRowTable delegate;

	public CopyRowTable(IRowTable delegate, boolean copyOnRead, boolean copyOnWrite) {
		if (delegate == null) {
			throw new NullPointerException("delegate");
		}
		this.delegate = delegate;
		this.copyOnRead = copyOnRead;
		this.copyOnWrite = copyOnWrite;
	}

	/**
	 * Getter for the delegate field.
	 * @return the value of the delegate field.
	 */
	public IRowTable getDelegate() {
		return delegate;
	}

	/**
	 * Getter for the copyOnRead field.
	 * @return the value of the copyOnRead field.
	 */
	public boolean getCopyOnRead() {
		return copyOnRead;
	}

	/**
	 * Getter for the copyOnWrite field.
	 * @return the value of the copyOnWrite field.
	 */
	public boolean getCopyOnWrite() {
		return copyOnWrite;
	}

	@Override
	public Lock getWriteLock() {
		return delegate.getWriteLock();
	}

	@Override
	public Lock getReadLock() {
		return delegate.getReadLock();
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
	public void clear() {
		delegate.clear();
	}

	@Override
	public boolean exists() {
		return delegate.exists();
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
	public boolean contains(IRow element) {
		return delegate.contains(element);
	}

	@Override
	public boolean containsRowKey(IRowKey row) {
		return delegate.containsRowKey(row);
	}

	@Override
	public void remove(IRow element) {
		delegate.remove(element);
	}

	@Override
	public void removeAll(Collection<? extends IRow> elements) {
		delegate.removeAll(elements);
	}

	@Override
	public void removeByRowKey(IRowKey key) {
		delegate.removeByRowKey(key);
	}

	@Override
	public void add(IRow element) {
		if (copyOnWrite) {
			element = new Row(element);
		}
		delegate.add(element);
	}

	@Override
	public void set(IRow element) {
		if (copyOnWrite) {
			element = new Row(element);
		}
		delegate.set(element);
	}

	@Override
	public void addAll(Collection<? extends IRow> elements) {
		if (copyOnWrite) {
			List<IRow> copy = new ArrayList<IRow>(elements.size());
			for (IRow element : elements) {
				copy.add(new Row(element));
			}
			elements = copy;
		}
		delegate.addAll(elements);
	}

	@Override
	public void setAll(Collection<? extends IRow> elements) {
		if (copyOnWrite) {
			List<IRow> copy = new ArrayList<IRow>(elements.size());
			for (IRow element : elements) {
				copy.add(new Row(element));
			}
			elements = copy;
		}
		delegate.setAll(elements);
	}

	@Override
	public List<IRow> getAll() {
		List<IRow> list = delegate.getAll();
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public IRow get(int id) {
		IRow returnValue = delegate.get(id);
		if (copyOnRead) {
			returnValue = new Row(returnValue);
		}
		return returnValue;
	}

	@Override
	public IRow getByRowKey(IRowKey key) {
		IRow returnValue = delegate.getByRowKey(key);
		if (copyOnRead) {
			returnValue = new Row(returnValue);
		}
		return returnValue;
	}

	@Override
	public boolean containsId(int id) {
		return delegate.containsId(id);
	}

	@Override
	public int getIdCount(int id) {
		return delegate.getIdCount(id);
	}

	@Override
	public IRow getRowById(int id) {
		IRow returnValue = delegate.getRowById(id);
		if (copyOnRead) {
			returnValue = new Row(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowById(int id) {
		delegate.removeRowById(id);
	}

	@Override
	public IRowData getRowDataById(int id) {
		IRowData returnValue = delegate.getRowDataById(id);
		if (copyOnRead) {
			returnValue = new RowData(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowDataById(int id) {
		delegate.removeRowDataById(id);
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {
		IRowDimensions returnValue = delegate.getRowDimensionsById(id);
		if (copyOnRead) {
			returnValue = new RowDimensions(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowDimensionsById(int id) {
		delegate.removeRowDimensionsById(id);
	}

	@Override
	public List<IRow> getRowListBetweenIds(Integer from, Integer to) {
		List<IRow> list = delegate.getRowListBetweenIds(from, to);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public boolean containsName(String name) {
		return delegate.containsName(name);
	}

	@Override
	public int getNameCount(String name) {
		return delegate.getNameCount(name);
	}

	@Override
	public IRow getRowByName(String name) {
		IRow returnValue = delegate.getRowByName(name);
		if (copyOnRead) {
			returnValue = new Row(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowByName(String name) {
		delegate.removeRowByName(name);
	}

	@Override
	public IRowData getRowDataByName(String name) {
		IRowData returnValue = delegate.getRowDataByName(name);
		if (copyOnRead) {
			returnValue = new RowData(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowDataByName(String name) {
		delegate.removeRowDataByName(name);
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {
		IRowDimensions returnValue = delegate.getRowDimensionsByName(name);
		if (copyOnRead) {
			returnValue = new RowDimensions(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowDimensionsByName(String name) {
		delegate.removeRowDimensionsByName(name);
	}

	@Override
	public boolean containsData(byte[] data) {
		return delegate.containsData(data);
	}

	@Override
	public int getDataCount(byte[] data) {
		return delegate.getDataCount(data);
	}

	@Override
	public List<IRow> getRowListByData(byte[] data) {
		List<IRow> list = delegate.getRowListByData(data);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowListByData(byte[] data) {
		delegate.removeRowListByData(data);
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {
		List<IRowData> list = delegate.getRowDataListByData(data);
		if (copyOnRead) {
			List<IRowData> copy = new ArrayList<IRowData>(list.size());
			for (IRowData dataStore : list) {
				copy.add(new RowData(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDataListByData(byte[] data) {
		delegate.removeRowDataListByData(data);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {
		List<IRowDimensions> list = delegate.getRowDimensionsListByData(data);
		if (copyOnRead) {
			List<IRowDimensions> copy = new ArrayList<IRowDimensions>(list.size());
			for (IRowDimensions dataStore : list) {
				copy.add(new RowDimensions(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByData(byte[] data) {
		delegate.removeRowDimensionsListByData(data);
	}

	@Override
	public boolean containsWidth(long width) {
		return delegate.containsWidth(width);
	}

	@Override
	public int getWidthCount(long width) {
		return delegate.getWidthCount(width);
	}

	@Override
	public List<IRow> getRowListByWidth(long width) {
		List<IRow> list = delegate.getRowListByWidth(width);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowListByWidth(long width) {
		delegate.removeRowListByWidth(width);
	}

	@Override
	public List<IRowData> getRowDataListByWidth(long width) {
		List<IRowData> list = delegate.getRowDataListByWidth(width);
		if (copyOnRead) {
			List<IRowData> copy = new ArrayList<IRowData>(list.size());
			for (IRowData dataStore : list) {
				copy.add(new RowData(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDataListByWidth(long width) {
		delegate.removeRowDataListByWidth(width);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {
		List<IRowDimensions> list = delegate.getRowDimensionsListByWidth(width);
		if (copyOnRead) {
			List<IRowDimensions> copy = new ArrayList<IRowDimensions>(list.size());
			for (IRowDimensions dataStore : list) {
				copy.add(new RowDimensions(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByWidth(long width) {
		delegate.removeRowDimensionsListByWidth(width);
	}

	@Override
	public List<IRow> getRowListBetweenWidths(Long from, Long to) {
		List<IRow> list = delegate.getRowListBetweenWidths(from, to);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public boolean containsHeight(long height) {
		return delegate.containsHeight(height);
	}

	@Override
	public int getHeightCount(long height) {
		return delegate.getHeightCount(height);
	}

	@Override
	public List<IRow> getRowListByHeight(long height) {
		List<IRow> list = delegate.getRowListByHeight(height);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowListByHeight(long height) {
		delegate.removeRowListByHeight(height);
	}

	@Override
	public List<IRowData> getRowDataListByHeight(long height) {
		List<IRowData> list = delegate.getRowDataListByHeight(height);
		if (copyOnRead) {
			List<IRowData> copy = new ArrayList<IRowData>(list.size());
			for (IRowData dataStore : list) {
				copy.add(new RowData(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDataListByHeight(long height) {
		delegate.removeRowDataListByHeight(height);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {
		List<IRowDimensions> list = delegate.getRowDimensionsListByHeight(height);
		if (copyOnRead) {
			List<IRowDimensions> copy = new ArrayList<IRowDimensions>(list.size());
			for (IRowDimensions dataStore : list) {
				copy.add(new RowDimensions(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByHeight(long height) {
		delegate.removeRowDimensionsListByHeight(height);
	}

	@Override
	public List<IRow> getRowListBetweenHeights(Long from, Long to) {
		List<IRow> list = delegate.getRowListBetweenHeights(from, to);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public boolean containsUnit(TimeUnit unit) {
		return delegate.containsUnit(unit);
	}

	@Override
	public int getUnitCount(TimeUnit unit) {
		return delegate.getUnitCount(unit);
	}

	@Override
	public List<IRow> getRowListByUnit(TimeUnit unit) {
		List<IRow> list = delegate.getRowListByUnit(unit);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowListByUnit(TimeUnit unit) {
		delegate.removeRowListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {
		List<IRowData> list = delegate.getRowDataListByUnit(unit);
		if (copyOnRead) {
			List<IRowData> copy = new ArrayList<IRowData>(list.size());
			for (IRowData dataStore : list) {
				copy.add(new RowData(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDataListByUnit(TimeUnit unit) {
		delegate.removeRowDataListByUnit(unit);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {
		List<IRowDimensions> list = delegate.getRowDimensionsListByUnit(unit);
		if (copyOnRead) {
			List<IRowDimensions> copy = new ArrayList<IRowDimensions>(list.size());
			for (IRowDimensions dataStore : list) {
				copy.add(new RowDimensions(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByUnit(TimeUnit unit) {
		delegate.removeRowDimensionsListByUnit(unit);
	}

	@Override
	public List<IRowData> getRowDataList() {
		List<IRowData> list = delegate.getRowDataList();
		if (copyOnRead) {
			List<IRowData> copy = new ArrayList<IRowData>(list.size());
			for (IRowData dataStore : list) {
				copy.add(new RowData(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public boolean containsRowData(IRowData row) {
		return delegate.containsRowData(row);
	}

	@Override
	public List<IRow> getByRowData(byte[] data) {
		List<IRow> list = delegate.getByRowData(data);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public IRowData getRowDataByRowNameKey(int id, String name) {
		IRowData returnValue = delegate.getRowDataByRowNameKey(id, name);
		if (copyOnRead) {
			returnValue = new RowData(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowDataByRowNameKey(int id, String name) {
		delegate.removeRowDataByRowNameKey(id, name);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {
		List<IRowDimensions> list = delegate.getRowDimensionsList();
		if (copyOnRead) {
			List<IRowDimensions> copy = new ArrayList<IRowDimensions>(list.size());
			for (IRowDimensions dataStore : list) {
				copy.add(new RowDimensions(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public boolean containsRowDimensions(IRowDimensions row) {
		return delegate.containsRowDimensions(row);
	}

	@Override
	public List<IRow> getByRowDimensions(long width, long height) {
		List<IRow> list = delegate.getByRowDimensions(width, height);
		if (copyOnRead) {
			List<IRow> copy = new ArrayList<IRow>(list.size());
			for (IRow dataStore : list) {
				copy.add(new Row(dataStore));
			}
			list = copy;
		}
		return list;
	}

	@Override
	public IRowDimensions getRowDimensionsByRowNameKey(int id, String name) {
		IRowDimensions returnValue = delegate.getRowDimensionsByRowNameKey(id, name);
		if (copyOnRead) {
			returnValue = new RowDimensions(returnValue);
		}
		return returnValue;
	}

	@Override
	public void removeRowDimensionsByRowNameKey(int id, String name) {
		delegate.removeRowDimensionsByRowNameKey(id, name);
	}

	@Override
	public IRow getRowByRowNameKey(int id, String name) {
		IRow returnValue = delegate.getRowByRowNameKey(id, name);
		if (copyOnRead) {
			returnValue = new Row(returnValue);
		}
		return returnValue;
	}

	@Override
	public boolean containsRowNameKey(IRowNameKey row) {
		return delegate.containsRowNameKey(row);
	}

	@Override
	public void removeRowByRowNameKey(int id, String name) {
		delegate.removeRowByRowNameKey(id, name);
	}
}
