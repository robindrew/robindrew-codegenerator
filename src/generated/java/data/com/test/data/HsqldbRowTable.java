package com.test.data;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.hsqldb.HsqldbSqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlExecutor;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HsqldbRowTable implements ISqlRowTable {

	private volatile String table = "RowTable";
	private volatile String database;
	private volatile ISqlExecutor executor;
	private final ReadWriteLock reentrantLock;

	public HsqldbRowTable() {
		this(new ReentrantReadWriteLock(true));
	}

	public HsqldbRowTable(ReadWriteLock reentrantLock) {
		if (reentrantLock == null) {
			throw new NullPointerException("reentrantLock");
		}
		this.reentrantLock = reentrantLock;
	}

	/**
	 * Getter for the reentrantLock field.
	 * @return the value of the reentrantLock field.
	 */
	public ReadWriteLock getReentrantLock() {
		return reentrantLock;
	}

	/**
	 * Getter for the table field.
	 * @return the value of the table field.
	 */
	public String getTable() {
		if (table == null) {
			throw new NullPointerException("table");
		}
		return table;
	}

	/**
	 * Getter for the database field.
	 * @return the value of the database field.
	 */
	public String getDatabase() {
		if (database == null) {
			throw new NullPointerException("database");
		}
		return database;
	}

	/**
	 * Getter for the executor field.
	 * @return the value of the executor field.
	 */
	public ISqlExecutor getExecutor() {
		if (executor == null) {
			throw new NullPointerException("executor");
		}
		return executor;
	}

	/**
	 * Setter for the table field.
	 * @param table the table value to set.
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * Setter for the database field.
	 * @param database the database value to set.
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * Setter for the executor field.
	 * @param executor the executor value to set.
	 */
	public void setExecutor(ISqlExecutor executor) {
		this.executor = executor;
	}

	@Override
	public Lock getReadLock() {
		return reentrantLock.readLock();
	}

	@Override
	public Lock getWriteLock() {
		return reentrantLock.writeLock();
	}

	public ISqlBuilder newSqlBuilder() {
		return new HsqldbSqlBuilder();
	}

	@Override
	public int size() {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());

		// Execute
		return getExecutor().getCount(sql);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(IRow element) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(element.getId());

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public List<IRow> getAll() {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public IRow getByRowKey(IRowKey key) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(key.getId());

		// Execute
		return getExecutor().get(sql, new RowResultSetParser());
	}

	@Override
	public IRow get(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		return getExecutor().get(sql, new RowResultSetParser());
	}

	public void removeByRowKey(IRowKey key) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(table);
		sql.where();
		sql.column("id").sql("=").value(key.getId());

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public boolean containsRowKey(IRowKey row) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(row.getId());

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public void clear() {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.truncateTable(getTable());

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public void create() {
		if (exists()) {
			return;
		}

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.createTable(getTable());
		sql.sql('(');
		sql.column("id").sql(" INT");
		sql.notNull();
		sql.autoIncrement();
		sql.sql(',');
		sql.column("name").sql(" VARCHAR(200)");
		sql.notNull();
		sql.sql(',');
		sql.column("data").sql(" VARBINARY(200)");
		sql.sql(',');
		sql.column("width").sql(" BIGINT");
		sql.notNull();
		sql.sql(',');
		sql.column("height").sql(" BIGINT");
		sql.notNull();
		sql.sql(',');
		sql.column("unit").sql(" SMALLINT");
		sql.sql(')');
		sql.sql(',');
		sql.sql("UNIQUE (");
		sql.column("name");
		sql.sql(')');
		sql.sql(')');

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public void destroy() {
		if (!exists()) {
			return;
		}

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.dropTable(getTable());

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public void add(IRow element) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.insertInto(getTable());
		sql.sql('(');
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.sql(") VALUES (?,?,?,?,?,?)");

		// Values
		ISqlValues values = new SqlValues();
		values.add(element.getId());
		values.add(element.getName());
		values.add(element.getData());
		values.add(element.getWidth());
		values.add(element.getHeight());
		values.add(element.getUnit());

		// Execute
		getExecutor().execute(sql, values);
	}

	@Override
	public void set(IRow element) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.updateSet(getTable());
		sql.column("name").sql("=?");
		sql.sql(',');
		sql.column("data").sql("=?");
		sql.sql(',');
		sql.column("width").sql("=?");
		sql.sql(',');
		sql.column("height").sql("=?");
		sql.sql(',');
		sql.column("unit").sql("=?");
		sql.where();
		sql.column("id").sql("=?");

		// Values
		ISqlValues values = new SqlValues();
		values.add(element.getName());
		values.add(element.getData());
		values.add(element.getWidth());
		values.add(element.getHeight());
		values.add(element.getUnit());
		values.add(element.getId());

		// Execute
		getExecutor().execute(sql, values);
	}

	@Override
	public void remove(IRow element) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("id").sql("=?");

		// Values
		ISqlValues values = new SqlValues();
		values.add(element.getId());

		// Execute
		getExecutor().execute(sql, values);
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
	public void addAuto(IRow element) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.insertInto(getTable());
		sql.sql('(');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.sql(") VALUES (?,?,?,?,?)");

		// Values
		ISqlValues values = new SqlValues();
		values.add(element.getName());
		values.add(element.getData());
		values.add(element.getWidth());
		values.add(element.getHeight());
		values.add(element.getUnit());

		// Execute
		int id = getExecutor().executeAutoIncrement(sql, values);
		element.setId(id);
	}

	@Override
	public boolean containsId(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public IRow getRowById(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		return getExecutor().get(sql, new RowResultSetParser());
	}

	@Override
	public void removeRowById(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public IRowData getRowDataById(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		return getExecutor().get(sql, new RowDataResultSetParser());
	}

	@Override
	public void removeRowDataById(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public IRowDimensions getRowDimensionsById(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		return getExecutor().get(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public void removeRowDimensionsById(int id) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRow> getRowListBetweenIds(Integer from, Integer to) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		if (from != null) {
			sql.column("id").sql(">=").value(from);
		}
		if (from != null && to != null) {
			sql.and();
		}
		if (to != null) {
			sql.column("id").sql("<=").value(to);
		}

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public boolean containsName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public IRow getRowByName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().get(sql, new RowResultSetParser());
	}

	@Override
	public void removeRowByName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public IRowData getRowDataByName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().get(sql, new RowDataResultSetParser());
	}

	@Override
	public void removeRowDataByName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public IRowDimensions getRowDimensionsByName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().get(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public void removeRowDimensionsByName(String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("name").sql("=").value(name);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public boolean containsData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public List<IRow> getRowListByData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public void removeRowListByData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowData> getRowDataListByData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		return getExecutor().getList(sql, new RowDataResultSetParser());
	}

	@Override
	public void removeRowDataListByData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		return getExecutor().getList(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public void removeRowDimensionsListByData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public boolean containsWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public List<IRow> getRowListByWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public void removeRowListByWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowData> getRowDataListByWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		return getExecutor().getList(sql, new RowDataResultSetParser());
	}

	@Override
	public void removeRowDataListByWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		return getExecutor().getList(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public void removeRowDimensionsListByWidth(long width) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("width").sql("=").value(width);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRow> getRowListBetweenWidths(Long from, Long to) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		if (from != null) {
			sql.column("width").sql(">=").value(from);
		}
		if (from != null && to != null) {
			sql.and();
		}
		if (to != null) {
			sql.column("width").sql("<=").value(to);
		}

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public boolean containsHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public List<IRow> getRowListByHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public void removeRowListByHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowData> getRowDataListByHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		return getExecutor().getList(sql, new RowDataResultSetParser());
	}

	@Override
	public void removeRowDataListByHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		return getExecutor().getList(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public void removeRowDimensionsListByHeight(long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("height").sql("=").value(height);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRow> getRowListBetweenHeights(Long from, Long to) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		if (from != null) {
			sql.column("height").sql(">=").value(from);
		}
		if (from != null && to != null) {
			sql.and();
		}
		if (to != null) {
			sql.column("height").sql("<=").value(to);
		}

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public boolean containsUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public List<IRow> getRowListByUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	@Override
	public void removeRowListByUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowData> getRowDataListByUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		return getExecutor().getList(sql, new RowDataResultSetParser());
	}

	@Override
	public void removeRowDataListByUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsListByUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		return getExecutor().getList(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public void removeRowDimensionsListByUnit(TimeUnit unit) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(getTable());
		sql.where();
		sql.column("unit").sql("=").value(unit.ordinal());

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowData> getRowDataList() {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());

		// Execute
		return getExecutor().getList(sql, new RowDataResultSetParser());
	}

	@Override
	public boolean containsRowData(IRowData row) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("data").sql("=").value(row.getData());

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	public List<IRow> getByRowData(byte[] data) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("data").sql("=").value(data);

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	public IRowData getRowDataByRowNameKey(int id, String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("data");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().get(sql, new RowDataResultSetParser());
	}

	public void removeRowDataByRowNameKey(int id, String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(table);
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("name").sql("=").value(name);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public List<IRowDimensions> getRowDimensionsList() {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());

		// Execute
		return getExecutor().getList(sql, new RowDimensionsResultSetParser());
	}

	@Override
	public boolean containsRowDimensions(IRowDimensions row) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(row.getId());
		sql.and();
		sql.column("width").sql("=").value(row.getWidth());
		sql.and();
		sql.column("height").sql("=").value(row.getHeight());

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	public List<IRow> getByRowDimensions(int id, long width, long height) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("width").sql("=").value(width);
		sql.and();
		sql.column("height").sql("=").value(height);

		// Execute
		return getExecutor().getList(sql, new RowResultSetParser());
	}

	public IRowDimensions getRowDimensionsByRowNameKey(int id, String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().get(sql, new RowDimensionsResultSetParser());
	}

	public void removeRowDimensionsByRowNameKey(int id, String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(table);
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("name").sql("=").value(name);

		// Execute
		getExecutor().execute(sql);
	}

	public IRow getRowByRowNameKey(int id, String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.select();
		sql.column("id");
		sql.sql(',');
		sql.column("name");
		sql.sql(',');
		sql.column("data");
		sql.sql(',');
		sql.column("width");
		sql.sql(',');
		sql.column("height");
		sql.sql(',');
		sql.column("unit");
		sql.from(getTable());
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("name").sql("=").value(name);

		// Execute
		return getExecutor().get(sql, new RowResultSetParser());
	}

	public void removeRowByRowNameKey(int id, String name) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.deleteFrom(table);
		sql.where();
		sql.column("id").sql("=").value(id);
		sql.and();
		sql.column("name").sql("=").value(name);

		// Execute
		getExecutor().execute(sql);
	}

	@Override
	public boolean containsRowNameKey(IRowNameKey row) {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.selectCountAllFrom(getTable());
		sql.where();
		sql.column("id").sql("=").value(row.getId());
		sql.and();
		sql.column("name").sql("=").value(row.getName());

		// Execute
		return getExecutor().getCount(sql) > 0;
	}

	@Override
	public boolean exists() {

		// SQL
		ISqlBuilder sql = newSqlBuilder();
		sql.sql("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME=").value(getTable());
		return getExecutor().getCount(sql) > 0;
	}
}
