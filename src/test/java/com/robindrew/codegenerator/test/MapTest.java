package com.robindrew.codegenerator.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.h2.jdbcx.JdbcConnectionPool;
import org.hsqldb.jdbc.JDBCPool;
import org.junit.Test;

import com.robindrew.codegenerator.api.sql.executor.LoggingSqlExecutor;
import com.robindrew.codegenerator.api.sql.executor.SqlExecutor;
import com.test.data.CopyRowTable;
import com.test.data.DerbyRowTable;
import com.test.data.H2RowTable;
import com.test.data.HsqldbRowTable;
import com.test.data.IRow;
import com.test.data.IRowTable;
import com.test.data.MapRowTable;
import com.test.data.Row;
import com.test.data.RowData;
import com.test.data.RowDimensions;
import com.test.data.RowNameKey;

import junit.framework.Assert;

public class MapTest {

	@Test
	public void testMap() {
		IRowTable table = new MapRowTable(new ConcurrentHashMap<>());
		table = new CopyRowTable(table, true, false);
		testTable(table, true);
	}

	@Test
	public void testDerby() throws ClassNotFoundException {

		// In-memory Derby database
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		String url = "jdbc:derby:memory:TestDatabase;create=true";
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, null);
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
		poolableConnectionFactory.setPool(connectionPool);
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);
		LoggingSqlExecutor executor = new LoggingSqlExecutor(new SqlExecutor(dataSource));

		DerbyRowTable table = new DerbyRowTable();
		table.setDatabase("TestTable");
		table.setExecutor(executor);
		table.create();

		testTable(table, false);
	}

	@Test
	public void testHsqldb() {

		// In-memory HSQLDB database
		JDBCPool pool = new JDBCPool(1);
		pool.setUrl("jdbc:hsqldb:mem:TestDatabase");
		LoggingSqlExecutor executor = new LoggingSqlExecutor(new SqlExecutor(pool));

		HsqldbRowTable table = new HsqldbRowTable();
		table.setDatabase("TestTable");
		table.setExecutor(executor);
		table.create();

		testTable(table, false);
	}

	@Test
	public void testH2() {

		// In-memory H2 database
		JdbcConnectionPool pool = JdbcConnectionPool.create("jdbc:h2:mem:TestDatabase", "", "");
		LoggingSqlExecutor executor = new LoggingSqlExecutor(new SqlExecutor(pool));

		H2RowTable table = new H2RowTable();
		table.setDatabase("TestTable");
		table.setExecutor(executor);
		table.create();

		testTable(table, false);
	}

	private void testTable(IRowTable table, boolean isMap) {

		IRow row1 = new Row(1, "One", new byte[1], 11, 101, TimeUnit.NANOSECONDS);
		IRow row2 = new Row(2, "Two", new byte[2], 22, 102, TimeUnit.MICROSECONDS);
		IRow row3 = new Row(3, "Three", new byte[3], 33, 103, TimeUnit.MILLISECONDS);
		IRow row4 = new Row(4, "Four", new byte[4], 33, 103, TimeUnit.NANOSECONDS);
		IRow row5 = new Row(5, "Five", new byte[4], 22, 109, TimeUnit.NANOSECONDS);

		table.add(row1);
		table.add(row2);
		table.add(row3);
		table.add(row4);
		table.add(row5);

		Assert.assertTrue(table.get(1) != row1);
		Assert.assertTrue(table.get(2) != row2);
		Assert.assertTrue(table.get(3) != row3);
		Assert.assertTrue(table.get(4) != row4);
		Assert.assertTrue(table.get(5) != row5);

		Assert.assertEquals(5, table.size());
		Assert.assertEquals(2, table.getWidthCount(33));
		Assert.assertEquals(2, table.getHeightCount(103));
		Assert.assertEquals(1, table.getHeightCount(109));
		Assert.assertEquals(3, table.getUnitCount(TimeUnit.NANOSECONDS));

		Assert.assertTrue(table.contains(row1));
		Assert.assertTrue(table.contains(row2));
		Assert.assertTrue(table.contains(row3));

		Assert.assertFalse(table.containsId(10));
		Assert.assertTrue(table.containsId(1));
		Assert.assertTrue(table.containsId(2));
		Assert.assertTrue(table.containsId(3));

		Assert.assertFalse(table.containsName("Ten"));
		Assert.assertTrue(table.containsName("One"));
		Assert.assertTrue(table.containsName("Two"));
		Assert.assertTrue(table.containsName("Three"));

		Assert.assertFalse(table.containsRowNameKey(new RowNameKey(10, "Ten")));
		Assert.assertTrue(table.containsRowNameKey(new RowNameKey(1, "One")));
		Assert.assertTrue(table.containsRowNameKey(new RowNameKey(2, "Two")));
		Assert.assertTrue(table.containsRowNameKey(new RowNameKey(3, "Three")));

		Assert.assertEquals(1, table.getByRowDimensions(11, 101).size());
		Assert.assertEquals(2, table.getByRowDimensions(33, 103).size());
		Assert.assertEquals(3, table.getRowDimensionsListByUnit(TimeUnit.NANOSECONDS).size());

		Assert.assertTrue(table.getRowDimensionsById(1).equals(new RowDimensions(11, 101)));
		Assert.assertTrue(table.getRowDimensionsById(5).equals(new RowDimensions(22, 109)));

		Assert.assertTrue(table.getAll().contains(row1));
		Assert.assertTrue(table.getAll().contains(row2));
		Assert.assertTrue(table.getAll().contains(row3));
		Assert.assertTrue(table.getAll().contains(row4));
		Assert.assertTrue(table.getAll().contains(row5));

		// TODO: Fix array equality checks for databases
		if (isMap) {
			Assert.assertTrue(table.containsRowData(new RowData(new byte[1])));
		}

	}

}
