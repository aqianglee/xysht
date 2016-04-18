package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;

public class ClassfyServiceImplTest4 extends BaseTest {

	@Test
	public void testValidateClassfy() {
		Classfy classfy = new Classfy();
		List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		classfyService.validateClassfy(classfy, messages);
		isEq(1, messages.size());
		isEq("missing.classfyName", messages.get(0).getKey());
	}

	@Test
	public void testValidateClassfy2() {
		Classfy classfy = new Classfy();
		classfy.setName("衣服类");
		List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		classfyService.validateClassfy(classfy, messages);
		isEq(0, messages.size());
	}

}
