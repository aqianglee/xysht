package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;
import com.aqiang.xysht.entities.OrderStatus;
import com.aqiang.xysht.entities.PayWays;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.User;

public class OrderServiceImplTest extends BaseTest {

	@Test
	public void testSubmitOrder() {
		Order order = new Order();
		order.setUser(createUser());
		order.setSubmitedTime(new Date());
		order.setOrderStatus(OrderStatus.SUBMITED);
		order.setNumber("123");
		Supermarket supermarket = createSupermarket();
		order.setSupermarket(supermarket);
		List<OrderItem> orderItems = createOrderItems(supermarket, order);
		order.setPayWay(PayWays.OFF_LINE);
		orderService.submitOrder(order, orderItems);
		List<Order> orders = orderService.getAll();
		isEq(1, orders.size());
		Order order2 = orders.get(0);
		isEq(PayWays.OFF_LINE, order2.getPayWay());
		isEq(OrderStatus.SUBMITED, order2.getOrderStatus());
		isEq(8D, order2.getPrice());
		List<OrderItem> orderItems2 = orderItemService.getOrderItemsByOrder(order2);
		isEq(2, orderItems2.size());
	}

	private List<OrderItem> createOrderItems(Supermarket supermarket, Order order) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		prepareGoods(supermarket);
		List<Good> all = goodService.getAll();
		for (Good good : all) {
			OrderItem orderItem = new OrderItem();
			orderItem.setGood(good);
			orderItem.setOrder(order);
			orderItem.setCount(2);
			orderItems.add(orderItem);
		}
		return orderItems;
	}

	private void prepareGoods(Supermarket supermarket) {
		createGood(supermarket, "方便面", 2.5D);
		createGood(supermarket, "火腿肠", 1.5D);
	}

	private void createGood(Supermarket supermarket, String name, Double price) {
		Good good = new Good();
		good.setSupermarket(supermarket);
		good.setName(name);
		good.setPrice(price);
		goodService.saveEntitiy(good);
	}

	private Supermarket createSupermarket() {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("青大超市");
		supermarketService.saveEntitiy(supermarket);
		return supermarket;
	}

	private User createUser() {
		User user = new User();
		ReceiveAddress receiveAddress = new ReceiveAddress();
		receiveAddress.setAddress("一号楼223");
		receiveAddress.setPhone("18826202524");
		receiveAddress.setCompellation("李志强");
		return user;
	}

}
