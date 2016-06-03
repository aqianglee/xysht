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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.ManagerService;
import com.aqiang.xysht.service.OrderItemService;
import com.aqiang.xysht.service.OrderService;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.ReceiveAddressService;
import com.aqiang.xysht.service.ShopKeeperService;
import com.aqiang.xysht.service.SupermarketService;
import com.aqiang.xysht.service.TagService;
import com.aqiang.xysht.service.UserService;
import com.aqiang.xysht.service.ValidateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
	@Autowired
	protected ShopKeeperService shopKeeperService;
	@Autowired
	protected ValidateService validateService;
	@Autowired
	protected SupermarketService supermarketService;
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
	@Autowired
	protected ManagerService managerService;
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected OrderItemService orderItemService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected ReceiveAddressService receiveAddressService;

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
		LOGGER.info("test is finished");
		clearDB();
	}

	private void clearDB() {
		tagService.deleteAll();
		pictureService.deleteAll();
		orderItemService.deleteAll();
		goodService.deleteAll();
		classfyService.deleteAll();
		shopKeeperService.deleteAll();
		parameterService.deleteAll();
		orderService.deleteAll();
		receiveAddressService.deleteAll();
		userService.deleteAll();
		supermarketService.deleteAll();
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
