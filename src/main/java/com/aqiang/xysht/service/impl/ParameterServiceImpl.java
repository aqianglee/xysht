package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.Parameter;
import com.aqiang.xysht.service.ParameterService;

@Service
@Transactional
public class ParameterServiceImpl extends BaseServiceImpl<Parameter> implements
		ParameterService {

	@Resource(name = "parameterDao")
	@Override
	public void setDao(BaseDao<Parameter> dao) {
		this.dao = dao;
	}

	@Override
	public String getParameter(String parameterKey) {
		List<Parameter> parameters = findEntityByJpql(
				"From Parameter where key = ?", parameterKey);
		if (parameters.isEmpty()) {
			throw new RuntimeException("no.parameter.matched.parameterkey");
		}
		return parameters.get(0).getValue();
	}

	@Override
	public int getInt(String parameterKey) {
		try {
			return Integer.parseInt(getParameter(parameterKey));
		} catch (NumberFormatException e) {
			throw new RuntimeException("not.number.parameter");
		}
	}

}
