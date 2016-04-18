package com.aqiang.xysht.dao.impl;

import org.springframework.stereotype.Repository;

import com.aqiang.xysht.dao.OrderDao;
import com.aqiang.xysht.entities.Order;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

}
