package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;

public interface OrderItemService extends BaseService<OrderItem> {

	List<OrderItem> getOrderItemsByOrder(Order order);

}
