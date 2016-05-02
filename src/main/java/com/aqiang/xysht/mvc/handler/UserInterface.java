package com.aqiang.xysht.mvc.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.SuperMarketService;

@Controller
@RequestMapping("nonLogin")
public class UserInterface {

	@Autowired
	private ClassfyService classfyService;
	@Autowired
	private SuperMarketService supermarketService;
	@Autowired
	private GoodService goodService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInterface.class);
	@Autowired
	private PictureService pictureSerivce;

	@ResponseBody
	@RequestMapping("getFirstLevelClassfies")
	public List<Classfy> getFirstLevelClassfies(int sid) {
		Supermarket supermarket = new Supermarket();
		supermarket.setId(sid);
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, null);
		LOGGER.info("supermarket id {}, get {} first level classfies", sid, classfies.size());
		return classfies;
	}

	@ResponseBody
	@RequestMapping("getSecondLevelClassfies")
	public List<Classfy> getSecondLevelClassfies(int sid, int cid) {
		Supermarket supermarket = new Supermarket();
		supermarket.setId(sid);
		Classfy classfy = new Classfy();
		classfy.setId(cid);
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, classfy);
		LOGGER.info("supermarket id {}, classfy id {}, get {} second level classfies", sid, cid, classfies.size());
		return classfies;
	}

	@ResponseBody
	@RequestMapping("listAllSupermarkets")
	public List<Supermarket> listAllSupermarkets() {
		List<Supermarket> supermarkets = supermarketService.getAll();
		for (Supermarket supermarket : supermarkets) {
			supermarket.setIcon(pictureSerivce.initPictureContext(supermarket.getIcon()));
		}
		LOGGER.info("get {} supermarkets", supermarkets.size());
		return supermarkets;
	}

	@RequestMapping("getHotGoods")
	@ResponseBody
	public List<Good> getAllHotGoodBySupermarketId() {
		List<Good> goods = goodService.getAllHotGood();
		return goods;
	}

	@RequestMapping("getDisCountGoods")
	@ResponseBody
	public List<Good> getAllDisCountGoodBySupermarketId() {
		List<Good> goods = goodService.getAllDiscountGood();
		return goods;
	}

	@RequestMapping("getGoodsByClassfyId")
	@ResponseBody
	public List<Good> getAllGoodsByReclassfyId(Integer id) {
		Classfy classfy = classfyService.findEntity(id);
		List<Good> goods = goodService.getAllGoodsByClassfy(classfy);
		List<Classfy> classfies = classfyService.getClassfiesByParent(classfy.getSupermarket(), classfy);
		for (Classfy c : classfies) {
			goods.addAll(goodService.getAllGoodsByClassfy(c));
		}
		return goods;
	}

	@RequestMapping("submitOrder")
	@ResponseBody
	public void submitOrder(Order order) {

	}

	@RequestMapping("getHotClassfies")
	@ResponseBody
	public List<Classfy> getHotClassfies() {
		// TODO implement this method
		return classfyService.getHotClassfies();
	}

	@RequestMapping("getHotActivities")
	@ResponseBody
	public List<Classfy> getHotActivities() {
		// TODO implement this method
		return classfyService.getHotClassfies();
	}
}
