package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;

public class ValidateServiceImplTest extends BaseTest {

	@Test
	public void testValidateListOfErrorMessageStringStringObjectArray() {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validate(errorMessages, "", "username");
		isEq(1, errorMessages.size());
		isEq("missing.username", errorMessages.get(0).getKey());
	}
	
	@Test
	public void testValidateListOfErrorMessageStringStringObjectArray2() {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validate(errorMessages, "    ", "username");
		isEq(1, errorMessages.size());
		isEq("missing.username", errorMessages.get(0).getKey());
	}
	
	@Test
	public void testValidateListOfErrorMessageStringStringObjectArray3() {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		validateService.validate(errorMessages, "  aqiang  ", "username");
		isEq(0, errorMessages.size());
	}

}
