package com.aqiang.xysht.mvc.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.exception.NotLoginException;
import com.aqiang.xysht.service.OrderItemService;
import com.aqiang.xysht.service.OrderService;

@Controller
@RequestMapping("shopKeeper")
public class OrderHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderHandler.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;

	@RequestMapping("listAllOrders")
	public String list(Map<String, Object> map, HttpSession session, String orderStatus) {
		try {
			ShopKeeper shopKeeper = getLoginShopKeeper(session);
			if (shopKeeper != null) {
				List<Order> orders = orderService.getOrdersByShopKeeperAndOrderStatus(shopKeeper, orderStatus);
				LOGGER.info("get {} orders", orders.size());
				map.put("orders", orders);
			}
			map.put("activeId", orderStatus);
			map.put("location", 1);
			return "order/list";
		} catch (NotLoginException e) {
			return "redirect:/nonLogin/shopKeeper_toLoginPage";
		}
	}

	@RequestMapping("showOrderDetails")
	public String showOrderDetails(Integer orderId, Map<String, Object> map) {
		Order order = orderService.findEntity(orderId);
		List<OrderItem> orderItems = orderItemService.getOrderItemsByOrder(order);
		map.put("order", order);
		map.put("orderItems", orderItems);
		return "order/orderDetails";
	}

	@RequestMapping("confirmOrder")
	@ResponseBody
	public String confirmOrder(Integer orderId, Map<String, Object> map) {
		Order order = orderService.findEntity(orderId);
		orderService.checkOrder(order);
		return "success";
	}

	@RequestMapping("forwordOrders")
	@ResponseBody
	public String forwordOrders(Integer orderId, Map<String, Object> map) {
		Order order = orderService.findEntity(orderId);
		orderService.forwordOrders(order);
		return "success";
	}

	private ShopKeeper getLoginShopKeeper(HttpSession session) {
		ShopKeeper shopKeeper = (ShopKeeper) session.getAttribute("shopKeeper");
		if (shopKeeper == null) {
			throw new NotLoginException("not.login");
		}
		return shopKeeper;
	}

}
