package com.aqiang.xysht.mvc.handler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aqiang.xysht.entities.Manager;

@Controller
@RequestMapping("/nonLogin")
public class ManagerHandler {

	@RequestMapping("/manager_toLoginPage")
	public String toLoginPage() {
		return "manager/login";
	}

	@RequestMapping("/manager_doLogin")
	public String doLogin() {
		return "redirect:";
	}

	@ModelAttribute
	public void getManager(Map<String, Manager> map) {
		map.put("manager", new Manager());
	}
}
