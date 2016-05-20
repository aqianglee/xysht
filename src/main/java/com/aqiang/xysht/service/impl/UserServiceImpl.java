package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.User;
import com.aqiang.xysht.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name = "userDao")
	@Override
	public void setDao(BaseDao<User> dao) {
		this.dao = dao;
	}

	@Override
	public User updateUser(User user) {
		User userByPhone = getUserByPhone(user.getPhone());
		if (userByPhone != null) {
			userByPhone.setAddress(user.getAddress());
			userByPhone.setCompellation(user.getCompellation());
			return margeEntity(userByPhone);
		}
		LOGGER.info("user address : {}", user.getAddress());
		LOGGER.info("user name : {}", user.getCompellation());
		LOGGER.info("user phone : {}", user.getPhone());
		saveEntitiy(user);
		return user;
	}

	@Override
	public User getUserByPhone(String phone) {
		List<User> users = findEntityByJpql("From User u where u.phone = ?", phone);
		return users.isEmpty() ? null : users.get(0);
	}
}
