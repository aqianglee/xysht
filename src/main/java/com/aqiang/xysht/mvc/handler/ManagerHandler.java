package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Manager;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.service.ManagerService;

@Controller
@RequestMapping("/nonLogin")
public class ManagerHandler {

	@Autowired
	private ManagerService managerService;

	@RequestMapping("/manager_toLoginPage")
	public String toLoginPage() {
		return "manager/login";
	}

	@RequestMapping("/manager_doLogin")
	public String doLogin(HttpSession session, Manager manager,
			Map<String, Object> map) {
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		manager = managerService.validateLoginInfo(manager, info);
		if (info.size() == 0) {
			session.setAttribute("manager", manager);
			return "redirect:/manager/toManagePage";
		} else {
			map.put("errorMessages", info);
			return "manager/login";
		}
	}

	@ModelAttribute
	public void getManager(Map<String, Manager> map) {
		map.put("manager", new Manager());
	}
}
