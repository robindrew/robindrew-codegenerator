package com.test.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MapRowTable implements IRowTable {

	private final ReadWriteLock reentrantLock;
	private final Map<IRowKey, IRow> map;
	private final AtomicInteger autoIncrement;
	private final Set<String> nameSet = new HashSet<String>();

	public MapRowTable(Map<IRowKey, IRow> map, ReadWriteLock reentrantLock) {
		if (map == null) {
			throw new NullPointerException("map");
		}
		this.map = map;
		this.reentrantLock = reentrantLock;
		this.autoIncrement = new AtomicInteger(0);
	}

	public MapRowTable(Map<IRowKey, IRow> map) {
		if (map == null) {
			throw new NullPointerException("map");
		}
		this.map = map;
		this.reentrantLock = new ReentrantReadWriteLock(true);
		this.autoIncrement = new AtomicInteger(0);
	}

	/**
	 * Getter for the map field.
	 * @return the value of the map field.
	 */
	public Map<IRowKey, IRow> getMap() {
		return map;
	}

	/**
	 * Getter for the reentrantLock field.
	 * @return the value of the reentrantLock field.
	 */
	public ReadWriteLock getReentrantLock() {
		return reentrantLock;
	}

	@Override
	public Lock getReadLock() {
		return reentrantLock.readLock();
	}

	@Override
	public Lock getWriteLock() {
		return reentrantLock.writeLock();
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public void clear() {
		map.clear();
		nameSet.clear();
	}

	@Override
	public void addAll(Collection<? extends IRow> elements) {
		for (IRow element : elements) {
			add(element);
		}
	}

	@Override
	public void setAll(Collection<? extends IRow> elements) {
		for (IRow element : elements) {
			set(element);
		}
	}

	@Override
	public void removeAll(Collection<? extends IRow> elements) {
		for (IRow element : elements) {
			remove(element);
		}
	}

	@Override
	public List<IRow> getAll() {
		return new ArrayList<IRow>(map.values());
	}

	@Override
	public IRow get(int id) {
		return map.get(new RowKey(id));
	}

	@Override
	public boolean contains(IRow element) {
		IRowKey key = getKey(element);
		return map.containsKey(key);
	}

	@Override
	public void remove(IRow element) {
		IRowKey key = getKey(element);
		map.remove(key);
		nameSet.remove(element.getName());
	}

	@Override
	public void add(IRow element) {
		IRowKey key = getKey(element);
		map.put(key, element);
	}

	@Override
	public void set(IRow element) {
		IRowKey key = getKey(element);
		IRow replaced = map.get(key);
		if (replaced == null) {
			return;
		}
		if (!element.getName().equals(replaced.getName())) {
			nameSet.remove(replaced.getName());
			nameSet.add(element.getName());
		}
		map.put(key, element);
	}

	@Override
	public void create() {
	}

	@Override
	public void destroy() {
		map.clear();
		autoIncrement.set(0);
		nameSet.clear();
	}

	public IRowKey getKey(IRow element) {
		return new RowKey(element);
	}

	@Override
	public IRow getByRowKey(IRowKey key) {
		if (key == null) {
			throw new NullPointerException("key");
		}
		return map.get(key);
	}

	@Override
	public boolean containsRowKey(IRowKey row) {
		return getByRowKey(row) != null;
	}

	@Override
	public void addAuto(IRow element) {
		if (nameSet.contains(element.getName())) {
			throw new IllegalStateException("name already exists: " + element.getName());
		}
		// We loop until we find a key we can add ...
		while (true) {
			element.setId(autoIncrement.addAndGet(1));
			IRowKey key = getKey(element);
			if (!map.containsKey(key)) {
				map.put(key, element);
				return;
			}
		}
	}

	@Override
	public boolean containsId(int id) {
		for (IRow element : map.values()) {
			if (element.getId() == id) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IRow getRowById(int id) {
		for (IRow element : map.values()) {
			if (element.getId() == id) {
				return element;
			}
		}
		return null;
	}

	@Override
	public void removeRowById(int id) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getId() == id) {
				remove(element);
			}
		}
	}

	@Override
	public IRowData getRowDataById(int id) {
		for (IRow element : map.values()) {
			if (element.getId() == id) {
				return new RowData(element);
			}
		}
		return null;
	}

	@Override
	public void removeRowDataById(int id) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getId() == id) {
				remove(element);
			}
		}
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {
		for (IRow element : map.values()) {
			if (element.getId() == id) {
				return new RowDimensions(element);
			}
		}
		return null;
	}

	@Override
	public void removeRowDimensionsById(int id) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getId() == id) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRow> getRowListBetweenIds(Integer from, Integer to) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow dataStore : map.values()) {
			if (from != null) {
				if (dataStore.getId() <  from ){
					continue;
				}
			}
			if (to != null) {
				if (dataStore.getId() >  to ){
					continue;
				}
			}
			list.add(dataStore);
		}
		return list;
	}

	@Override
	public boolean containsName(String name) {
		for (IRow element : map.values()) {
			if (element.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IRow getRowByName(String name) {
		for (IRow element : map.values()) {
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}

	@Override
	public void removeRowByName(String name) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getName().equals(name)) {
				remove(element);
			}
		}
	}

	@Override
	public IRowData getRowDataByName(String name) {
		for (IRow element : map.values()) {
			if (element.getName().equals(name)) {
				return new RowData(element);
			}
		}
		return null;
	}

	@Override
	public void removeRowDataByName(String name) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getName().equals(name)) {
				remove(element);
			}
		}
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {
		for (IRow element : map.values()) {
			if (element.getName().equals(name)) {
				return new RowDimensions(element);
			}
		}
		return null;
	}

	@Override
	public void removeRowDimensionsByName(String name) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getName().equals(name)) {
				remove(element);
			}
		}
	}

	@Override
	public boolean containsData(byte[] data) {
		for (IRow element : map.values()) {
			if (element.getData().equals(data)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IRow> getRowListByData(byte[] data) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow element : map.values()) {
			if (element.getData().equals(data)) {
				list.add(element);
			}
		}
		return list;
	}

	@Override
	public void removeRowListByData(byte[] data) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getData().equals(data)) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {
		List<IRowData> list = new ArrayList<IRowData>();
		for (IRow element : map.values()) {
			if (element.getData().equals(data)) {
				list.add(new RowData(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDataListByData(byte[] data) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getData().equals(data)) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {
		List<IRowDimensions> list = new ArrayList<IRowDimensions>();
		for (IRow element : map.values()) {
			if (element.getData().equals(data)) {
				list.add(new RowDimensions(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByData(byte[] data) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getData().equals(data)) {
				remove(element);
			}
		}
	}

	@Override
	public boolean containsWidth(long width) {
		for (IRow element : map.values()) {
			if (element.getWidth() == width) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IRow> getRowListByWidth(long width) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow element : map.values()) {
			if (element.getWidth() == width) {
				list.add(element);
			}
		}
		return list;
	}

	@Override
	public void removeRowListByWidth(long width) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getWidth() == width) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowData> getRowDataListByWidth(long width) {
		List<IRowData> list = new ArrayList<IRowData>();
		for (IRow element : map.values()) {
			if (element.getWidth() == width) {
				list.add(new RowData(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDataListByWidth(long width) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getWidth() == width) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {
		List<IRowDimensions> list = new ArrayList<IRowDimensions>();
		for (IRow element : map.values()) {
			if (element.getWidth() == width) {
				list.add(new RowDimensions(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByWidth(long width) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getWidth() == width) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRow> getRowListBetweenWidths(Long from, Long to) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow dataStore : map.values()) {
			if (from != null) {
				if (dataStore.getWidth() <  from ){
					continue;
				}
			}
			if (to != null) {
				if (dataStore.getWidth() >  to ){
					continue;
				}
			}
			list.add(dataStore);
		}
		return list;
	}

	@Override
	public boolean containsHeight(long height) {
		for (IRow element : map.values()) {
			if (element.getHeight() == height) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IRow> getRowListByHeight(long height) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow element : map.values()) {
			if (element.getHeight() == height) {
				list.add(element);
			}
		}
		return list;
	}

	@Override
	public void removeRowListByHeight(long height) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getHeight() == height) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowData> getRowDataListByHeight(long height) {
		List<IRowData> list = new ArrayList<IRowData>();
		for (IRow element : map.values()) {
			if (element.getHeight() == height) {
				list.add(new RowData(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDataListByHeight(long height) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getHeight() == height) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {
		List<IRowDimensions> list = new ArrayList<IRowDimensions>();
		for (IRow element : map.values()) {
			if (element.getHeight() == height) {
				list.add(new RowDimensions(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByHeight(long height) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getHeight() == height) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRow> getRowListBetweenHeights(Long from, Long to) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow dataStore : map.values()) {
			if (from != null) {
				if (dataStore.getHeight() <  from ){
					continue;
				}
			}
			if (to != null) {
				if (dataStore.getHeight() >  to ){
					continue;
				}
			}
			list.add(dataStore);
		}
		return list;
	}

	@Override
	public boolean containsUnit(TimeUnit unit) {
		for (IRow element : map.values()) {
			if (element.getUnit().equals(unit)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<IRow> getRowListByUnit(TimeUnit unit) {
		List<IRow> list = new ArrayList<IRow>();
		for (IRow element : map.values()) {
			if (element.getUnit().equals(unit)) {
				list.add(element);
			}
		}
		return list;
	}

	@Override
	public void removeRowListByUnit(TimeUnit unit) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getUnit().equals(unit)) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {
		List<IRowData> list = new ArrayList<IRowData>();
		for (IRow element : map.values()) {
			if (element.getUnit().equals(unit)) {
				list.add(new RowData(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDataListByUnit(TimeUnit unit) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getUnit().equals(unit)) {
				remove(element);
			}
		}
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {
		List<IRowDimensions> list = new ArrayList<IRowDimensions>();
		for (IRow element : map.values()) {
			if (element.getUnit().equals(unit)) {
				list.add(new RowDimensions(element));
			}
		}
		return list;
	}

	@Override
	public void removeRowDimensionsListByUnit(TimeUnit unit) {
		for (IRow element : new ArrayList<>(map.values())) {
			if (element.getUnit().equals(unit)) {
				remove(element);
			}
		}
	}

	public List<IRowData> getRowDataList() {
		List<IRow> elements = getAll();
		List<IRowData> list = new ArrayList<IRowData>(elements.size());
		for (IRow element : elements) {
			list.add(new RowData(element));
		}
		return list;
	}

	@Override
	public boolean containsRowData(IRowData row) {
		for (IRow element : map.values()) {
			if (!element.getData().equals(row.getData())) {
				continue;
			}
			return true;
		}
		return false;
	}

	@Override
	public IRowData getRowDataByRowNameKey(int id, String name) {
		for (IRow element : map.values()) {
			if (element.getId() == id && element.getName().equals(name)) {
				return new RowData(element);
			}
		}
		return null;
	}

	public List<IRowDimensions> getRowDimensionsList() {
		List<IRow> elements = getAll();
		List<IRowDimensions> list = new ArrayList<IRowDimensions>(elements.size());
		for (IRow element : elements) {
			list.add(new RowDimensions(element));
		}
		return list;
	}

	@Override
	public boolean containsRowDimensions(IRowDimensions row) {
		for (IRow element : map.values()) {
			if (element.getId() != row.getId()) {
				continue;
			}
			if (element.getWidth() != row.getWidth()) {
				continue;
			}
			if (element.getHeight() != row.getHeight()) {
				continue;
			}
			return true;
		}
		return false;
	}

	@Override
	public IRowDimensions getRowDimensionsByRowNameKey(int id, String name) {
		for (IRow element : map.values()) {
			if (element.getId() == id && element.getName().equals(name)) {
				return new RowDimensions(element);
			}
		}
		return null;
	}

	@Override
	public IRow getRowByRowNameKey(int id, String name) {
		for (IRow element : map.values()) {
			if (element.getId() == id && element.getName().equals(name)) {
				return new Row(element);
			}
		}
		return null;
	}

	@Override
	public boolean containsRowNameKey(IRowNameKey row) {
		for (IRow element : map.values()) {
			if (element.getId() != row.getId()) {
				continue;
			}
			if (!element.getName().equals(row.getName())) {
				continue;
			}
			return true;
		}
		return false;
	}
}
