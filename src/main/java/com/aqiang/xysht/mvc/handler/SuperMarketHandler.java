package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.exception.NotLoginException;
import com.aqiang.xysht.service.ParameterService;
import com.aqiang.xysht.service.SuperMarketService;

@Controller
@RequestMapping("shopKeeper")
public class SuperMarketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(SuperMarketHandler.class);

	@Autowired
	private SuperMarketService superMarketService;
	@Autowired
	private ParameterService parameterService;
	private ShopKeeper shopKeeper;

	private List<ErrorMessage> messages;

	@RequestMapping("listAllSuperMarkets")
	public String list(Map<String, Object> map, HttpSession session) {
		try {
			ShopKeeper shopKeeper = getLoginShopKeeper(session);
			if (shopKeeper != null) {
				List<Supermarket> supermarkets = superMarketService.getSupermarketsByShopKeeper(shopKeeper);
				LOGGER.info("get {} supermarkets", supermarkets.size());
				map.put("superMarkets", supermarkets);
			}
			map.put("location", 0);
			return "superMarket/list";
		} catch (NotLoginException e) {
			return "redirect:/nonLogin/shopKeeper_toLoginPage";
		}
	}

	@RequestMapping("editSuperMarket")
	public String editUI(Map<String, Object> map) {
		map.put("location", 0);
		return "superMarket/editUI";
	}

	@RequestMapping("saveSuperMarket")
	public String save(Map<String, Object> map, Supermarket supermarket, HttpSession session,
			HttpServletRequest request, @RequestParam(value = "p", required = false) MultipartFile p) {
		messages = new ArrayList<ErrorMessage>();
		try {
			shopKeeper = getLoginShopKeeper(session);
			supermarket.setShopKeeper(shopKeeper);
			superMarketService.saveOrUpdateEntity(supermarket, p);
			return "redirect:/shopKeeper/listAllSuperMarkets";
		} catch (NotLoginException e) {
			return "redirect:/nonLogin/shopKeeper_toLoginPage";
		} catch (RuntimeException e) {
			messages.add(new ErrorMessage(e.getMessage()));
			e.printStackTrace();
			map.put("errorMessages", messages);
			return "superMarket/editUI";
		}

	}

	private ShopKeeper getLoginShopKeeper(HttpSession session) {
		ShopKeeper shopKeeper = (ShopKeeper) session.getAttribute("shopKeeper");
		if (shopKeeper == null) {
			throw new NotLoginException("not.login");
		}
		return shopKeeper;
	}

	@ResponseBody
	@RequestMapping("/deleteSuperMarket")
	public List<ErrorMessage> delete(int id, Boolean noAsk, Map<String, Object> map) {
		Supermarket supermarket = new Supermarket();
		supermarket.setId(id);
		List<ErrorMessage> messages = superMarketService.delete(supermarket, noAsk);
		return messages;
	}

	@ModelAttribute
	public void getSuperMerket(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {

		Supermarket supermarket = null;
		if (id != null) {
			supermarket = superMarketService.findEntity(id);
		} else {
			supermarket = new Supermarket();
		}
		map.put("superMarket", supermarket);
	}

}
