package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aqiang.xysht.entities.Parameter;
import com.aqiang.xysht.entities.ParameterKey;
import com.aqiang.xysht.service.ParameterService;

@Controller
@RequestMapping("manager")
public class ParameterHandler {

	@Autowired
	private ParameterService parameterService;

	@RequestMapping("listAllParameters")
	public String list(Map<String, Object> map) {
		List<Parameter> list = parameterService.getAll();
		for (Parameter parameter : list) {
			map.put(parameter.getKey(), parameter.getValue());
		}
		return "manager/parameter";
	}

	@RequestMapping("editParameters")
	public String edit(String fileRootDir) {
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(createParameter(ParameterKey.FILE_ROOT_DIR, fileRootDir));
		parameterService.updateParameters(parameters);
		return "redirect:/manager/listAllParameters";
	}

	private Parameter createParameter(String key, String value) {
		Parameter parameter = new Parameter();
		parameter.setKey(key);
		parameter.setValue(value);
		return parameter;
	}
}
