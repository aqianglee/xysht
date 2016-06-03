package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.User;
import com.aqiang.xysht.service.ReceiveAddressService;
import com.aqiang.xysht.service.UserService;

@Service
@Transactional
public class ReceiveAddressServiceImpl extends BaseServiceImpl<ReceiveAddress> implements ReceiveAddressService {

	@Autowired
	private UserService userService;

	@Resource(name = "receiveAddressDao")
	@Override
	public void setDao(BaseDao<ReceiveAddress> dao) {
		this.dao = dao;
	}

	@Override
	public List<ReceiveAddress> getReceiveAddressesByUser(User user) {

		return findEntityByJpql("From ReceiveAddress r where r.user = ?", userService.findEntity(user.getId()));
	}
}
