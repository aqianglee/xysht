package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.dto.OrderSummary;
import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.User;

public interface OrderService extends BaseService<Order> {

	public List<Order> getOrdersByShopKeeperAndOrderStatus(ShopKeeper shopKeeper, String orderStatus);

	public List<Order> getOrdersBySupermarketAndOrderStatus(Supermarket supermarket, String orderStatus);

	public void submitOrder(Order order, List<OrderItem> orderItems);

	public void checkOrder(Order order);

	public void forwordOrders(Order order);

	public void receivedOrders(Order order);

	public void refundOrders(Order order);

	public List<OrderSummary> getOrderSummaryByUserAndOrderStatus(User user, String orderStatus);

}
