package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;

public class ValidateServiceImplTest2 extends BaseTest {

	@Test
	public void testValidateListOfErrorMessageDateStringObjectArray() {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validate(errorMessages, new Date(), "registerDate");
		isEq(0, errorMessages.size());
	}

	@Test
	public void testValidateListOfErrorMessageDateStringObjectArray2() {
		Date date = null;
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validate(errorMessages, date, "registerDate");
		isEq(1, errorMessages.size());
		isEq("missing.registerDate", errorMessages.get(0).getKey());
	}

}
