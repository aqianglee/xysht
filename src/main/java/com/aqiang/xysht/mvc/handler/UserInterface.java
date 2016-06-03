package com.aqiang.xysht.mvc.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aqiang.xysht.dto.OrderSummary;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;
import com.aqiang.xysht.entities.OrderStatus;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.User;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.OrderService;
import com.aqiang.xysht.service.PictureService;
import com.aqiang.xysht.service.ReceiveAddressService;
import com.aqiang.xysht.service.SupermarketService;
import com.aqiang.xysht.service.UserService;

@Controller
@RequestMapping("nonLogin")
public class UserInterface {

	@Autowired
	private ClassfyService classfyService;
	@Autowired
	private SupermarketService supermarketService;
	@Autowired
	private GoodService goodService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserInterface.class);
	@Autowired
	private PictureService pictureSerivce;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReceiveAddressService receiveAddressService;

	@ResponseBody
	@RequestMapping("getFirstLevelClassfies")
	public List<Classfy> getFirstLevelClassfies(int sid) {
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarketService.findEntity(sid), null);
		LOGGER.info("supermarket id {}, get {} first level classfies", sid, classfies.size());
		return classfies;
	}

	@ResponseBody
	@RequestMapping("getSecondLevelClassfies")
	public List<Classfy> getSecondLevelClassfies(int sid, int cid) {
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarketService.findEntity(sid),
				classfyService.findEntity(cid));
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
	public List<Good> getHotGoods(Classfy classfy) {
		LOGGER.info("hot classfy name : {}", classfy.getName());
		List<Good> goods = goodService.getHotGoods(classfy);
		for (Good good : goods) {
			good.setPicture(pictureSerivce.initPictureContext(good.getPicture()));
		}
		return goods;
	}

	@RequestMapping("getDisCountGoods")
	@ResponseBody
	public List<Good> getAllDisCountGoodBySupermarketId() {
		List<Good> goods = goodService.getAllDiscountGood();
		for (Good good : goods) {
			good.setPicture(pictureSerivce.initPictureContext(good.getPicture()));
		}
		return goods;
	}

	@RequestMapping("getGoodsByClassfyId")
	@ResponseBody
	public List<Good> getAllGoodsByReclassfyId(Integer id) {
		Classfy classfy = classfyService.findEntity(id);
		List<Good> goods = goodService.getAllGoodsByClassfy(classfy);
		for (Good good : goods) {
			good.setPicture(pictureSerivce.initPictureContext(good.getPicture()));
		}
		List<Classfy> classfies = classfyService.getClassfiesByParent(classfy.getSupermarket(), classfy);
		for (Classfy c : classfies) {
			goods.addAll(goodService.getAllGoodsByClassfy(c));
		}
		return goods;
	}

	@RequestMapping("submitOrder")
	@ResponseBody
	public String submitOrder(Order order, String goodsId, String counts) {
		String[] goodsIdArray = goodsId.split(",");
		String[] countsArray = counts.split(",");
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (int i = 0; i < goodsIdArray.length; i++) {
			OrderItem orderItem = new OrderItem();
			orderItem.setGood(goodService.findEntity(Integer.parseInt(goodsIdArray[i])));
			orderItem.setCount(Integer.parseInt(countsArray[i]));
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		order.initPrice(orderItems);
		order.setSupermarket(orderItems.get(0).getGood().getSupermarket());
		order.setOrderStatus(OrderStatus.SUBMITED);
		orderService.submitOrder(order, orderItems);
		return "success";
	}

	@RequestMapping("confirmOrders")
	@ResponseBody
	public String confirmOrders(Order order) {
		LOGGER.info("confirm order");
		orderService.receivedOrders(order);
		return "success";
	}

	@RequestMapping("rfundOrders")
	@ResponseBody
	public String rfundOrders(Order order) {
		orderService.refundOrders(order);
		return "success";
	}

	@RequestMapping("getHotClassfies")
	@ResponseBody
	public List<Classfy> getHotClassfies() {
		return classfyService.getHotClassfies();
	}

	@RequestMapping("getHotActivities")
	@ResponseBody
	public List<Classfy> getHotActivities() {
		return classfyService.getHotClassfies();
	}

	@RequestMapping("getMyOrders")
	@ResponseBody
	public List<OrderSummary> getMyOrders(User user, String orderStatus) {
		return orderService.getOrderSummaryByUserAndOrderStatus(user, orderStatus);
	}

	@RequestMapping("register")
	@ResponseBody
	public User register(User user) {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		if (errorMessages.isEmpty()) {
			userService.registerUser(user, errorMessages);
		} else {
			throw new RuntimeException(errorMessages.get(0).getKey());
		}
		return user;
	}

	@RequestMapping("login")
	@ResponseBody
	public User login(User user) {
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		user = userService.getUserByUsernameAndPassword(user, errorMessages);
		if (!errorMessages.isEmpty()) {
			throw new RuntimeException(errorMessages.get(0).getKey());
		}
		return user;
	}

	@RequestMapping("getReceiveAddresses")
	@ResponseBody
	public List<ReceiveAddress> getReceiveAddresses(User user) {
		return receiveAddressService.getReceiveAddressesByUser(user);
	}

	@RequestMapping("addReceiveClass")
	@ResponseBody
	public String addReceiveClass(ReceiveAddress receiveAddress) {
		userService.addReceiveAddress(receiveAddress);
		return "success";
	}

	@RequestMapping("setDefaultReceiveAddress")
	@ResponseBody
	public String setDefaultReceiveAddress(Integer userId, Integer receiveAddressId) {
		userService.setDefaultReceiveAddress(userId, receiveAddressId);
		return "success";
	}
}
