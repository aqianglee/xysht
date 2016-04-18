package com.aqiang.xysht.service.impl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;

public class DBConnectTest extends BaseTest {

	@Test
	public void testDBConnect() throws SQLException, ClassNotFoundException,
			PropertyVetoException {
		isNotNull(applicationContext.getBean(DataSource.class).getConnection());
	}
}
