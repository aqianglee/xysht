package com.aqiang.xysht.service;

import com.aqiang.xysht.entities.Parameter;

public interface ParameterService extends BaseService<Parameter> {

	public String getParameter(String parameterKey);

	public int getInt(String parameterKey);
}
