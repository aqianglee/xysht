package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;

public class ValidateServiceImplTest4 extends BaseTest {

	@Test
	public void testValidatePasswordEqRepassword() {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validatePasswordEqRepassword(errorMessages, "123456", "123456");
		isEq(errorMessages.size(), 0);
	}

	@Test
	public void testValidatePasswordEqRepassword2() {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validatePasswordEqRepassword(errorMessages, "123456", "1234567");
		isEq(errorMessages.size(), 1);
		isEq(errorMessages.get(0).getKey(), "passwordNotEqRepassword");
	}

}
