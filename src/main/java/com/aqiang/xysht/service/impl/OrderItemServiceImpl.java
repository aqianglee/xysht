package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.Order;
import com.aqiang.xysht.entities.OrderItem;
import com.aqiang.xysht.service.OrderItemService;

@Service
@Transactional
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem> implements OrderItemService {

	@Resource(name = "orderItemDao")
	@Override
	public void setDao(BaseDao<OrderItem> dao) {
		this.dao = dao;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrder(Order order) {
		return findEntityByJpql("From OrderItem where order = ?", order);
	}

}
