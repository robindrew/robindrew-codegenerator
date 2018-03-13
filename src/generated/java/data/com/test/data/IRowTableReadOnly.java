package com.test.data;

import com.robindrew.codegenerator.api.datastore.IDataView;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public interface IRowTableReadOnly extends IDataView<IRow> {

	Lock getReadLock();

	int size();

	boolean isEmpty();

	boolean exists();

	boolean contains(IRow element);

	List<IRow> getAll();

	IRow get(int id);

	IRow get(IRowKey key);

	void addAuto(IRow element);

	boolean containsId(int id);

	IRow getRowById(int id);

	List<IRow> getRowListBetweenIds(Integer from, Integer to);

	IRowData getRowDataById(int id);

	IRowDimensions getRowDimensionsById(int id);

	boolean containsName(String name);

	IRow getRowByName(String name);

	IRowData getRowDataByName(String name);

	IRowDimensions getRowDimensionsByName(String name);

	boolean containsData(byte[] data);

	List<IRow> getRowListByData(byte[] data);

	List<IRowData> getRowDataListByData(byte[] data);

	List<IRowDimensions> getRowDimensionsListByData(byte[] data);

	boolean containsWidth(long width);

	List<IRow> getRowListByWidth(long width);

	List<IRow> getRowListBetweenWidths(Long from, Long to);

	List<IRowData> getRowDataListByWidth(long width);

	List<IRowDimensions> getRowDimensionsListByWidth(long width);

	boolean containsHeight(long height);

	List<IRow> getRowListByHeight(long height);

	List<IRow> getRowListBetweenHeights(Long from, Long to);

	List<IRowData> getRowDataListByHeight(long height);

	List<IRowDimensions> getRowDimensionsListByHeight(long height);

	boolean containsUnit(TimeUnit unit);

	List<IRow> getRowListByUnit(TimeUnit unit);

	List<IRowData> getRowDataListByUnit(TimeUnit unit);

	List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit);

	List<IRowData> getRowDataList();

	List<IRowDimensions> getRowDimensionsList();
}
