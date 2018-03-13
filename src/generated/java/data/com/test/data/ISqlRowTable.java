package com.test.data;

import com.robindrew.codegenerator.api.sql.builder.ISqlTable;
import com.robindrew.codegenerator.api.sql.executor.ISqlExecutor;
import java.util.concurrent.locks.ReadWriteLock;

public interface ISqlRowTable extends ISqlTable<IRow>, IRowTable {

	/**
	 * Getter for the reentrantLock field.
	 * @return the value of the reentrantLock field.
	 */
	ReadWriteLock getReentrantLock();

	/**
	 * Getter for the table field.
	 * @return the value of the table field.
	 */
	String getTable();

	/**
	 * Getter for the database field.
	 * @return the value of the database field.
	 */
	String getDatabase();

	/**
	 * Getter for the executor field.
	 * @return the value of the executor field.
	 */
	ISqlExecutor getExecutor();

	/**
	 * Setter for the table field.
	 * @param table the table value to set.
	 */
	void setTable(String table);

	/**
	 * Setter for the database field.
	 * @param database the database value to set.
	 */
	void setDatabase(String database);

	/**
	 * Setter for the executor field.
	 * @param executor the executor value to set.
	 */
	void setExecutor(ISqlExecutor executor);
}
