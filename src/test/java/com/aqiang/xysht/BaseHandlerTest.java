package com.aqiang.xysht;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:beans-test.xml")
public class BaseHandlerTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	public void isTrue(boolean result) {
		assertTrue(result);
	}

	public void isNull(Object object) {
		assertNull(object);
	}

	public void isNotNull(Object object) {
		assertNotNull(object);
	}

	public void isEq(Object expected, Object actual) {
		assertEquals(expected, actual);
	}

	@After
	public void destroy() {
		clearDB();
	}

	private void clearDB() {
	}

	public Date date(String dateStr, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Before
	public void setup() throws Exception {

	}

}
