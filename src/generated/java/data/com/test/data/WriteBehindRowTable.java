package com.test.data;

import com.robindrew.common.util.Java;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class WriteBehindRowTable implements IRowTable {

	private final IRowTable delegate;
	private final ExecutorService executor;

	public WriteBehindRowTable(IRowTable delegate, ExecutorService executor) {
		if (delegate == null) {
			throw new NullPointerException("delegate");
		}
		if (executor == null) {
			throw new NullPointerException("executor");
		}
		this.delegate = delegate;
		this.executor = executor;
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
		// Synchronous
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			public Integer call() {
				return delegate.size();
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean isEmpty() {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.isEmpty();
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean exists() {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.exists();
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean contains(final IRow element) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.contains(element);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getAll() {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getAll();
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRow get(final int id) {
		// Synchronous
		Future<IRow> future = executor.submit(new Callable<IRow>() {
			public IRow call() {
				return delegate.get(id);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRow getByRowKey(final IRowKey key) {
		// Synchronous
		Future<IRow> future = executor.submit(new Callable<IRow>() {
			public IRow call() {
				return delegate.getByRowKey(key);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean containsRowKey(final IRowKey row) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsRowKey(row);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void clear() {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.clear();
			}
		});
	}

	@Override
	public void create() {
		// Synchronous
		Future<Object> future = executor.submit(new Callable<Object>() {
			public Object call() {
				delegate.create();
				return null;
			}
		});

		try {
			future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void destroy() {
		// Synchronous
		Future<Object> future = executor.submit(new Callable<Object>() {
			public Object call() {
				delegate.destroy();
				return null;
			}
		});

		try {
			future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void add(final IRow element) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.add(element);
			}
		});
	}

	@Override
	public void set(final IRow element) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.set(element);
			}
		});
	}

	@Override
	public void remove(final IRow element) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.remove(element);
			}
		});
	}

	@Override
	public void addAll(final Collection<? extends IRow> elements) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.addAll(elements);
			}
		});
	}

	@Override
	public void setAll(final Collection<? extends IRow> elements) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.setAll(elements);
			}
		});
	}

	@Override
	public void removeAll(final Collection<? extends IRow> elements) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeAll(elements);
			}
		});
	}

	@Override
	public void removeByRowKey(final IRowKey key) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeByRowKey(key);
			}
		});
	}

	@Override
	public void addAuto(final IRow element) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.addAuto(element);
			}
		});
	}

	@Override
	public boolean containsId(final int id) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsId(id);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRow getRowById(final int id) {
		// Synchronous
		Future<IRow> future = executor.submit(new Callable<IRow>() {
			public IRow call() {
				return delegate.getRowById(id);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowById(final int id) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowById(id);
			}
		});
	}

	@Override
	public List<IRow> getRowListBetweenIds(final Integer from, final Integer to) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListBetweenIds(from, to);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRowData getRowDataById(final int id) {
		// Synchronous
		Future<IRowData> future = executor.submit(new Callable<IRowData>() {
			public IRowData call() {
				return delegate.getRowDataById(id);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataById(final int id) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataById(id);
			}
		});
	}

	@Override
	public IRowDimensions getRowDimensionsById(final int id) {
		// Synchronous
		Future<IRowDimensions> future = executor.submit(new Callable<IRowDimensions>() {
			public IRowDimensions call() {
				return delegate.getRowDimensionsById(id);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsById(final int id) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsById(id);
			}
		});
	}

	@Override
	public boolean containsName(final String name) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsName(name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRow getRowByName(final String name) {
		// Synchronous
		Future<IRow> future = executor.submit(new Callable<IRow>() {
			public IRow call() {
				return delegate.getRowByName(name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowByName(final String name) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowByName(name);
			}
		});
	}

	@Override
	public IRowData getRowDataByName(final String name) {
		// Synchronous
		Future<IRowData> future = executor.submit(new Callable<IRowData>() {
			public IRowData call() {
				return delegate.getRowDataByName(name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataByName(final String name) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataByName(name);
			}
		});
	}

	@Override
	public IRowDimensions getRowDimensionsByName(final String name) {
		// Synchronous
		Future<IRowDimensions> future = executor.submit(new Callable<IRowDimensions>() {
			public IRowDimensions call() {
				return delegate.getRowDimensionsByName(name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsByName(final String name) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsByName(name);
			}
		});
	}

	@Override
	public boolean containsData(final byte[] data) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsData(data);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getRowListByData(final byte[] data) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListByData(data);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowListByData(final byte[] data) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowListByData(data);
			}
		});
	}

	@Override
	public List<IRowData> getRowDataListByData(final byte[] data) {
		// Synchronous
		Future<List<IRowData>> future = executor.submit(new Callable<List<IRowData>>() {
			public List<IRowData> call() {
				return delegate.getRowDataListByData(data);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataListByData(final byte[] data) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataListByData(data);
			}
		});
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(final byte[] data) {
		// Synchronous
		Future<List<IRowDimensions>> future = executor.submit(new Callable<List<IRowDimensions>>() {
			public List<IRowDimensions> call() {
				return delegate.getRowDimensionsListByData(data);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsListByData(final byte[] data) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsListByData(data);
			}
		});
	}

	@Override
	public boolean containsWidth(final long width) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsWidth(width);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getRowListByWidth(final long width) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListByWidth(width);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowListByWidth(final long width) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowListByWidth(width);
			}
		});
	}

	@Override
	public List<IRow> getRowListBetweenWidths(final Long from, final Long to) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListBetweenWidths(from, to);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRowData> getRowDataListByWidth(final long width) {
		// Synchronous
		Future<List<IRowData>> future = executor.submit(new Callable<List<IRowData>>() {
			public List<IRowData> call() {
				return delegate.getRowDataListByWidth(width);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataListByWidth(final long width) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataListByWidth(width);
			}
		});
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(final long width) {
		// Synchronous
		Future<List<IRowDimensions>> future = executor.submit(new Callable<List<IRowDimensions>>() {
			public List<IRowDimensions> call() {
				return delegate.getRowDimensionsListByWidth(width);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsListByWidth(final long width) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsListByWidth(width);
			}
		});
	}

	@Override
	public boolean containsHeight(final long height) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsHeight(height);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getRowListByHeight(final long height) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListByHeight(height);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowListByHeight(final long height) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowListByHeight(height);
			}
		});
	}

	@Override
	public List<IRow> getRowListBetweenHeights(final Long from, final Long to) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListBetweenHeights(from, to);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRowData> getRowDataListByHeight(final long height) {
		// Synchronous
		Future<List<IRowData>> future = executor.submit(new Callable<List<IRowData>>() {
			public List<IRowData> call() {
				return delegate.getRowDataListByHeight(height);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataListByHeight(final long height) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataListByHeight(height);
			}
		});
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(final long height) {
		// Synchronous
		Future<List<IRowDimensions>> future = executor.submit(new Callable<List<IRowDimensions>>() {
			public List<IRowDimensions> call() {
				return delegate.getRowDimensionsListByHeight(height);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsListByHeight(final long height) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsListByHeight(height);
			}
		});
	}

	@Override
	public boolean containsUnit(final TimeUnit unit) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsUnit(unit);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getRowListByUnit(final TimeUnit unit) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getRowListByUnit(unit);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowListByUnit(final TimeUnit unit) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowListByUnit(unit);
			}
		});
	}

	@Override
	public List<IRowData> getRowDataListByUnit(final TimeUnit unit) {
		// Synchronous
		Future<List<IRowData>> future = executor.submit(new Callable<List<IRowData>>() {
			public List<IRowData> call() {
				return delegate.getRowDataListByUnit(unit);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataListByUnit(final TimeUnit unit) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataListByUnit(unit);
			}
		});
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(final TimeUnit unit) {
		// Synchronous
		Future<List<IRowDimensions>> future = executor.submit(new Callable<List<IRowDimensions>>() {
			public List<IRowDimensions> call() {
				return delegate.getRowDimensionsListByUnit(unit);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsListByUnit(final TimeUnit unit) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsListByUnit(unit);
			}
		});
	}

	@Override
	public List<IRowData> getRowDataList() {
		// Synchronous
		Future<List<IRowData>> future = executor.submit(new Callable<List<IRowData>>() {
			public List<IRowData> call() {
				return delegate.getRowDataList();
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean containsRowData(final IRowData row) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsRowData(row);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getByRowData(final byte[] data) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getByRowData(data);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRowData getRowDataByRowNameKey(final int id, final String name) {
		// Synchronous
		Future<IRowData> future = executor.submit(new Callable<IRowData>() {
			public IRowData call() {
				return delegate.getRowDataByRowNameKey(id, name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDataByRowNameKey(final int id, final String name) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDataByRowNameKey(id, name);
			}
		});
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {
		// Synchronous
		Future<List<IRowDimensions>> future = executor.submit(new Callable<List<IRowDimensions>>() {
			public List<IRowDimensions> call() {
				return delegate.getRowDimensionsList();
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean containsRowDimensions(final IRowDimensions row) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsRowDimensions(row);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public List<IRow> getByRowDimensions(final int id, final long width, final long height) {
		// Synchronous
		Future<List<IRow>> future = executor.submit(new Callable<List<IRow>>() {
			public List<IRow> call() {
				return delegate.getByRowDimensions(id, width, height);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public IRowDimensions getRowDimensionsByRowNameKey(final int id, final String name) {
		// Synchronous
		Future<IRowDimensions> future = executor.submit(new Callable<IRowDimensions>() {
			public IRowDimensions call() {
				return delegate.getRowDimensionsByRowNameKey(id, name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowDimensionsByRowNameKey(final int id, final String name) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowDimensionsByRowNameKey(id, name);
			}
		});
	}

	@Override
	public IRow getRowByRowNameKey(final int id, final String name) {
		// Synchronous
		Future<IRow> future = executor.submit(new Callable<IRow>() {
			public IRow call() {
				return delegate.getRowByRowNameKey(id, name);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public boolean containsRowNameKey(final IRowNameKey row) {
		// Synchronous
		Future<Boolean> future = executor.submit(new Callable<Boolean>() {
			public Boolean call() {
				return delegate.containsRowNameKey(row);
			}
		});

		try {
			return future.get();
		} catch(Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void removeRowByRowNameKey(final int id, final String name) {
		// Aysyncronous (Write Behind)
		executor.submit(new Runnable() {
			public void run() {
				delegate.removeRowByRowNameKey(id, name);
			}
		});
	}
}
