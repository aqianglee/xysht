package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Manager;

public class ManagerServiceImplTest extends BaseTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ManagerServiceImplTest.class);

	@Test
	public void testValidateLoginInfo() {
		Manager manager = new Manager();
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		managerService.validateLoginInfo(manager, info);
		isEq(2, info.size());
		isEq("missing.username", info.get(0).getKey());
		isEq("missing.password", info.get(1).getKey());
	}

	@Test
	public void testValidateLoginInfo2() {
		Manager manager = new Manager();
		manager.setUsername("aqiang");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		managerService.validateLoginInfo(manager, info);
		isEq(1, info.size());
		isEq("missing.password", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo3() {
		Manager manager = new Manager();
		manager.setUsername("aqiang");
		manager.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		managerService.validateLoginInfo(manager, info);
		isEq(1, info.size());
		isEq("userNotRegister", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo4() {
		Manager manager = new Manager();
		manager.setUsername("aqiang");
		manager.setPassword("123456");
		managerService.register(manager);
		Manager Manager2 = new Manager();
		Manager2.setUsername("aqiang");
		Manager2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		Manager keeper = managerService.validateLoginInfo(Manager2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(0, info.size());
		isNotNull(keeper);
	}

	@Test
	public void testValidateLoginInfo5() {
		Manager Manager = new Manager();
		Manager.setEmail("695182311@qq.com");
		Manager.setPassword("123456");
		managerService.register(Manager);
		Manager Manager2 = new Manager();
		Manager2.setUsername("695182311@qq.com");
		Manager2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		Manager keeper = managerService.validateLoginInfo(Manager2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(0, info.size());
		isNotNull(keeper);
	}

	@Test
	public void testValidateLoginInfo6() {
		Manager Manager = new Manager();
		Manager.setPhone("18826202524");
		Manager.setPassword("123456");
		managerService.register(Manager);
		Manager Manager2 = new Manager();
		Manager2.setUsername("18826202524");
		Manager2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		Manager keeper = managerService.validateLoginInfo(Manager2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(0, info.size());
		isNotNull(keeper);
	}

	@Test
	public void testValidateLoginInfo7() {
		Manager Manager = new Manager();
		Manager.setUsername("aqiang");
		Manager.setPassword("12345678");
		managerService.register(Manager);
		Manager Manager2 = new Manager();
		Manager2.setUsername("aqiang");
		Manager2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		Manager keeper = managerService.validateLoginInfo(Manager2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(1, info.size());
		isEq("passwordError", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo8() {
		Manager Manager = new Manager();
		Manager.setEmail("695182311@qq.com");
		Manager.setPassword("12345678");
		managerService.register(Manager);
		Manager Manager2 = new Manager();
		Manager2.setUsername("695182311@qq.com");
		Manager2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		managerService.validateLoginInfo(Manager2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(1, info.size());
		isEq("passwordError", info.get(0).getKey());
	}

	@Test
	public void testValidateLoginInfo9() {
		Manager Manager = new Manager();
		Manager.setPhone("18826202524");
		Manager.setPassword("12345678");
		managerService.register(Manager);
		Manager Manager2 = new Manager();
		Manager2.setUsername("18826202524");
		Manager2.setPassword("123456");
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		Long time = new Date().getTime();
		managerService.validateLoginInfo(Manager2, info);
		Long time2 = new Date().getTime();
		LOGGER.info("use time {}", time2 - time);
		isEq(1, info.size());
		isEq("passwordError", info.get(0).getKey());
	}

}
