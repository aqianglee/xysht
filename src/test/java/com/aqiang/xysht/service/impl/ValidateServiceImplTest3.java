package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;

public class ValidateServiceImplTest3 extends BaseTest {

	@Test
	public void testValidateUsernameBeenUsed() {
		List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		validateService.validateUsernameBeenUsed(messages, "aqiang");
		isEq(messages.size(), 0);
	}

	@Test
	public void testValidateUsernameBeenUsed2() {
		ShopKeeper keeper = new ShopKeeper();
		keeper.setUsername("aqiang");
		keeper.setPassword("123456");
		keeper.setRepassword("123456");
		shopKeeperService.register(keeper);
		List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		validateService.validateUsernameBeenUsed(messages, "aqiang");
		isEq(messages.size(), 1);
		isEq(messages.get(0).getKey(), "usernameBeenUsed");
	}

}
