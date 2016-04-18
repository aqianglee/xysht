package com.aqiang.xysht.dao.impl;

import org.springframework.stereotype.Repository;

import com.aqiang.xysht.dao.OrderItemDao;
import com.aqiang.xysht.entities.OrderItem;

@Repository("orderItemDao")
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements OrderItemDao{

}
