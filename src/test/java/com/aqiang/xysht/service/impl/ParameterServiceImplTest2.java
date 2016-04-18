package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Parameter;
import com.aqiang.xysht.entities.ParameterKey;

public class ParameterServiceImplTest2 extends BaseTest {

	@Test
	public void testGetInt() {
		try {
			isEq(3, parameterService.getInt(ParameterKey.FILE_ROOT_DIR));
		} catch (RuntimeException e) {
			isEq("no.parameter.matched.parameterkey", e.getMessage());
		}
	}

	@Test
	public void testGetInt2() {
		Parameter parameter = new Parameter();
		parameter.setKey(ParameterKey.FILE_ROOT_DIR);
		parameter.setValue("3");
		parameterService.saveEntitiy(parameter);
		isEq(3, parameterService.getInt(ParameterKey.FILE_ROOT_DIR));
	}

	@Test
	public void testGetInt3() {
		Parameter parameter = new Parameter();
		parameter.setKey(ParameterKey.FILE_ROOT_DIR);
		parameter.setValue("A");
		parameterService.saveEntitiy(parameter);
		try {
			isEq(3, parameterService.getInt(ParameterKey.FILE_ROOT_DIR));
		} catch (RuntimeException e) {
			isEq("not.number.parameter", e.getMessage());
		}
	}
}
