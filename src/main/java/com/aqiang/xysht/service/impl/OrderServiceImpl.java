package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.dto.OrderSummary;
import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;
import com.aqiang.xysht.entities.OrderStatus;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.User;
import com.aqiang.xysht.service.OrderItemService;
import com.aqiang.xysht.service.OrderService;
import com.aqiang.xysht.service.SupermarketService;
import com.aqiang.xysht.service.UserService;

@Service
@Transactional
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private UserService userService;
	@Autowired
	private SupermarketService supermarketService;

	@Resource(name = "orderDao")
	@Override
	public void setDao(BaseDao<Order> dao) {
		this.dao = dao;
	}

	@Override
	public void submitOrder(Order order, List<OrderItem> orderItems) {
		order.setUser(userService.updateUser(order.getUser()));
		order.initPrice(orderItems);
		LOGGER.info("order price: {}", order.getPrice());
		LOGGER.info("order status: {}", order.getOrderStatus());
		LOGGER.info("order supermarket name: {}", order.getSupermarket().getName());
		LOGGER.info("order supermarket id: {}", order.getSupermarket().getId());
		saveEntitiy(order);
		for (OrderItem orderItem : orderItems) {
			LOGGER.info("orderItem price: {}", orderItem.getGood().getPrice());
			LOGGER.info("orderItem name: {}", orderItem.getGood().getName());
			LOGGER.info("orderItem count: {}", orderItem.getCount());
			orderItemService.saveEntitiy(orderItem);
		}

	}

	// TODO add test for this method
	@Override
	public List<Order> getOrdersByShopKeeperAndOrderStatus(ShopKeeper shopKeeper, String orderStatus) {
		LOGGER.info("query status :{}", orderStatus);
		List<Supermarket> supermarkets = supermarketService.getSupermarketsByShopKeeper(shopKeeper);
		LOGGER.info("get {} supermarket", supermarkets.size());
		List<Order> orders = new ArrayList<Order>();
		for (Supermarket supermarket : supermarkets) {
			LOGGER.info("supermarket id is {}, and supermarket name is :{}", supermarket.getId(), supermarket.getName());
			orders.addAll(getOrdersBySupermarketAndOrderStatus(supermarket, orderStatus));
		}
		return orders;
	}

	@Override
	public List<Order> getOrdersBySupermarketAndOrderStatus(Supermarket supermarket, String orderStatus) {
		if (orderStatus != null && !orderStatus.equals("") && !OrderStatus.ALL.equals(orderStatus)) {
			LOGGER.info("use has orderStatus query");
			return findEntityByJpql("From Order o where o.supermarket = ? and orderStatus = ?", supermarket,
					orderStatus);
		} else {
			LOGGER.info("use no orderStatus query");
			return findEntityByJpql("From Order o where o.supermarket = ?", supermarket);
		}
	}

	@Override
	public void comfirmOrder(Order order) {
		order.setOrderStatus(OrderStatus.CHECKED);
	}

	@Override
	public void forwordOrders(Order order) {
		order.setOrderStatus(OrderStatus.FORWORD);
	}

	@Override
	public List<OrderSummary> getOrderSummaryByUserAndOrderStatus(User user, String orderStatus) {
		user = userService.getUserByPhone(user.getPhone());
		List<OrderSummary> summaries = new ArrayList<OrderSummary>();
		List<Order> orders = getOrdersByUserAndOrderStatus(user, orderStatus);
		for (Order order : orders) {
			OrderSummary summary = new OrderSummary();
			summary.setOrder(order);
			summary.setOrderItems(orderItemService.getOrderItemsByOrder(order));
			summaries.add(summary);
		}
		return summaries;
	}

	private List<Order> getOrdersByUserAndOrderStatus(User user, String orderStatus) {
		if (orderStatus != null && !orderStatus.equals("") && !OrderStatus.ALL.equals(orderStatus)) {
			LOGGER.info("use has orderStatus query");
			return findEntityByJpql("From Order o where o.user = ? and orderStatus = ?", user, orderStatus);
		} else {
			LOGGER.info("use no orderStatus query");
			return findEntityByJpql("From Order o where o.user = ?", user);
		}
	}
}
