package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;

public class ShopKeeperServiceImplTest2 extends BaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopKeeperServiceImplTest2.class);

	@Test
	public void testValidateLoginInfo() {
		ShopKeeper shopKeeper = new ShopKeeper();
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		shopKeeperService.validateLoginInfo(shopKeeper, info);
		isEq(2, info.size());
		isEq("missing.username", info.get(0).getKey());
		isEq("missing.password", info.get(1).getKey());
	}

	@Test
	public void testValidateLoginInfo2() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		shopKeeperService.validateLoginInfo(shopKeeper, info);
		isEq(1, info.size());
		isEq("missing.password", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo3() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		shopKeeperService.validateLoginInfo(shopKeeper, info);
		isEq(1, info.size());
		isEq("userNotRegister", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo4() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("123456");
		shopKeeperService.register(shopKeeper);
		ShopKeeper shopkeeper2 = new ShopKeeper();
		shopkeeper2.setUsername("aqiang");
		shopkeeper2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		ShopKeeper keeper = shopKeeperService.validateLoginInfo(shopkeeper2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(0, info.size());
		isNotNull(keeper);
	}

	@Test
	public void testValidateLoginInfo5() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setEmail("695182311@qq.com");
		shopKeeper.setPassword("123456");
		shopKeeperService.register(shopKeeper);
		ShopKeeper shopkeeper2 = new ShopKeeper();
		shopkeeper2.setUsername("695182311@qq.com");
		shopkeeper2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		ShopKeeper keeper = shopKeeperService.validateLoginInfo(shopkeeper2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(0, info.size());
		isNotNull(keeper);
	}

	@Test
	public void testValidateLoginInfo6() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setPhone("18826202524");
		shopKeeper.setPassword("123456");
		shopKeeperService.register(shopKeeper);
		ShopKeeper shopkeeper2 = new ShopKeeper();
		shopkeeper2.setUsername("18826202524");
		shopkeeper2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		ShopKeeper keeper = shopKeeperService.validateLoginInfo(shopkeeper2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(0, info.size());
		isNotNull(keeper);
	}

	@Test
	public void testValidateLoginInfo7() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("12345678");
		shopKeeperService.register(shopKeeper);
		ShopKeeper shopkeeper2 = new ShopKeeper();
		shopkeeper2.setUsername("aqiang");
		shopkeeper2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		ShopKeeper keeper = shopKeeperService.validateLoginInfo(shopkeeper2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(1, info.size());
		isEq("passwordError", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo8() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setEmail("695182311@qq.com");
		shopKeeper.setPassword("12345678");
		shopKeeperService.register(shopKeeper);
		ShopKeeper shopkeeper2 = new ShopKeeper();
		shopkeeper2.setUsername("695182311@qq.com");
		shopkeeper2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		shopKeeperService.validateLoginInfo(shopkeeper2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(1, info.size());
		isEq("passwordError", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo9() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setPhone("18826202524");
		shopKeeper.setPassword("12345678");
		shopKeeperService.register(shopKeeper);
		ShopKeeper shopkeeper2 = new ShopKeeper();
		shopkeeper2.setUsername("18826202524");
		shopkeeper2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		shopKeeperService.validateLoginInfo(shopkeeper2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(1, info.size());
		isEq("passwordError", info.get(0).getKey());
	}

}
