package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Parameter;
import com.aqiang.xysht.entities.ParameterKey;

public class ParameterServiceImplTest extends BaseTest {

	@Test
	public void testGetParameter() {
		Parameter parameter = new Parameter();
		parameter.setKey(ParameterKey.FILE_ROOT_DIR);
		parameter.setValue("D://xysht/");
		parameterService.saveEntitiy(parameter);
		isEq("D://xysht/",
				parameterService.getParameter(ParameterKey.FILE_ROOT_DIR));
	}

	@Test
	public void testGetParameter2() {
		try {
			isEq("D://xysht/",
					parameterService.getParameter(ParameterKey.FILE_ROOT_DIR));
		} catch (RuntimeException e) {
			isEq("no.parameter.matched.parameterkey", e.getMessage());
		}
	}

}
