package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.service.ShopKeeperService;

@Controller
@RequestMapping("/nonLogin")
public class ShopKeeperHandler {

	@Autowired
	private ShopKeeperService shopKeeperService;

	@RequestMapping("/shopKeeper_toRegisterPage")
	public String toRegisterPage(Map<String, Object> map) {
		map.put("myString", "my messages");
		return "shopKeeper/register";
	}

	@RequestMapping("/shopKeeper_save")
	public String save(ShopKeeper shopKeeper, Map<String, Object> map) {
		System.out.println(shopKeeper);
		List<ErrorMessage> errorMessages = shopKeeperService
				.validateRegister(shopKeeper);
		if (errorMessages.size() == 0) {
			shopKeeperService.register(shopKeeper);
			return "redirect:/nonLogin/shopKeeper_toLoginPage";
		} else {
			map.put("errorMessages", errorMessages);
			return "shopKeeper/register";
		}
	}

	@RequestMapping("/shopKeeper_toLoginPage")
	public String toLoginPage() {
		return "shopKeeper/login";
	}

	@RequestMapping("/shopKeeper_doLogin")
	public String doLogin(ShopKeeper shopKeeper, Map<String, Object> map,
			HttpSession session) {
		List<ErrorMessage> info = new ArrayList<ErrorMessage>();
		ShopKeeper keeper = shopKeeperService.validateLoginInfo(shopKeeper,
				info);
		if (info.size() == 0) {
			session.setAttribute("shopKeeper", keeper);
			return "redirect:/shopKeeper/toManagePage";
		} else {
			map.put("errorMessages", info);
			return "shopKeeper/login";
		}
	}

	@ModelAttribute
	public void getShopKeeper(
			@RequestParam(value = "id", required = false) Integer id,
			Map<String, Object> map) {
		ShopKeeper shopKeeper = null;
		if (id != null) {
			shopKeeper = shopKeeperService.findEntity(id);
		} else {
			shopKeeper = new ShopKeeper();
		}
		map.put("shopKeeper", shopKeeper);
	}

}
