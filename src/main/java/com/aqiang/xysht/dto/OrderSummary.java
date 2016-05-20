package com.aqiang.xysht.dto;

import java.util.List;

import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;

public class OrderSummary {
	private Order order;
	private List<OrderItem> orderItems;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
