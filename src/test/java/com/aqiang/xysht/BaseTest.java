package com.aqiang.xysht;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.SuperMarketService;
import com.aqiang.xysht.service.TagService;
import com.aqiang.xysht.service.ValidateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	protected ShopKeeperService shopKeeperService;
	@Autowired
	protected ValidateService validateService;
	@Autowired
	protected SuperMarketService superMarketService;
	@Autowired
	protected ParameterService parameterService;
	@Autowired
	protected ClassfyService classfyService;
	@Autowired
	protected PictureService pictureService;
	@Autowired
	protected GoodService goodService;
	@Autowired
	protected TagService tagService;

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
		tagService.deleteAll();
		pictureService.deleteAll();
		goodService.deleteAll();
		classfyService.deleteAll();
		superMarketService.deleteAll();
		shopKeeperService.deleteAll();
		parameterService.deleteAll();
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

}
