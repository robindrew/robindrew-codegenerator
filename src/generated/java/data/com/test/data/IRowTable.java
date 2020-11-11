package com.test.data;

import com.robindrew.codegenerator.api.datastore.IDataStore;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public interface IRowTable extends IDataStore<IRow>, IRowTableReadOnly {

	Lock getReadLock();

	Lock getWriteLock();

	int size();

	boolean isEmpty();

	boolean exists();

	boolean contains(IRow element);

	List<IRow> getAll();

	IRow get(int id);

	IRow getByRowKey(IRowKey key);

	boolean containsRowKey(IRowKey row);

	void clear();

	void create();

	void destroy();

	void add(IRow element);

	void set(IRow element);

	void remove(IRow element);

	void addAll(Collection<? extends IRow> elements);

	void setAll(Collection<? extends IRow> elements);

	void removeAll(Collection<? extends IRow> elements);

	void removeByRowKey(IRowKey key);

	boolean containsId(int id);

	IRow getRowById(int id);

	void removeRowById(int id);

	List<IRow> getRowListBetweenIds(Integer from, Integer to);

	IRowData getRowDataById(int id);

	void removeRowDataById(int id);

	IRowDimensions getRowDimensionsById(int id);

	void removeRowDimensionsById(int id);

	boolean containsName(String name);

	IRow getRowByName(String name);

	void removeRowByName(String name);

	IRowData getRowDataByName(String name);

	void removeRowDataByName(String name);

	IRowDimensions getRowDimensionsByName(String name);

	void removeRowDimensionsByName(String name);

	boolean containsData(byte[] data);

	List<IRow> getRowListByData(byte[] data);

	void removeRowListByData(byte[] data);

	List<IRowData> getRowDataListByData(byte[] data);

	void removeRowDataListByData(byte[] data);

	List<IRowDimensions> getRowDimensionsListByData(byte[] data);

	void removeRowDimensionsListByData(byte[] data);

	boolean containsWidth(long width);

	List<IRow> getRowListByWidth(long width);

	void removeRowListByWidth(long width);

	List<IRow> getRowListBetweenWidths(Long from, Long to);

	List<IRowData> getRowDataListByWidth(long width);

	void removeRowDataListByWidth(long width);

	List<IRowDimensions> getRowDimensionsListByWidth(long width);

	void removeRowDimensionsListByWidth(long width);

	boolean containsHeight(long height);

	List<IRow> getRowListByHeight(long height);

	void removeRowListByHeight(long height);

	List<IRow> getRowListBetweenHeights(Long from, Long to);

	List<IRowData> getRowDataListByHeight(long height);

	void removeRowDataListByHeight(long height);

	List<IRowDimensions> getRowDimensionsListByHeight(long height);

	void removeRowDimensionsListByHeight(long height);

	boolean containsUnit(TimeUnit unit);

	List<IRow> getRowListByUnit(TimeUnit unit);

	void removeRowListByUnit(TimeUnit unit);

	List<IRowData> getRowDataListByUnit(TimeUnit unit);

	void removeRowDataListByUnit(TimeUnit unit);

	List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit);

	void removeRowDimensionsListByUnit(TimeUnit unit);

	List<IRowData> getRowDataList();

	boolean containsRowData(IRowData row);

	List<IRow> getByRowData(byte[] data);

	IRowData getRowDataByRowNameKey(int id, String name);

	void removeRowDataByRowNameKey(int id, String name);

	List<IRowDimensions> getRowDimensionsList();

	boolean containsRowDimensions(IRowDimensions row);

	List<IRow> getByRowDimensions(long width, long height);

	IRowDimensions getRowDimensionsByRowNameKey(int id, String name);

	void removeRowDimensionsByRowNameKey(int id, String name);

	IRow getRowByRowNameKey(int id, String name);

	boolean containsRowNameKey(IRowNameKey row);

	void removeRowByRowNameKey(int id, String name);
}
