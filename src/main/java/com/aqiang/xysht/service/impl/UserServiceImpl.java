package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.User;
import com.aqiang.xysht.service.ReceiveAddressService;
import com.aqiang.xysht.service.UserService;
import com.aqiang.xysht.utils.Md5Util;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private ReceiveAddressService receiveAddressService;

	@Resource(name = "userDao")
	@Override
	public void setDao(BaseDao<User> dao) {
		this.dao = dao;
	}

	@Override
	public User getUserByPhone(String phone) {
		List<User> users = findEntityByJpql("From User u where u.phone = ?", phone);
		return users.isEmpty() ? null : users.get(0);
	}

	private Object getUserByUsername(String email) {
		List<User> users = findEntityByJpql("From User u where u.email = ?", email);
		return users.isEmpty() ? null : users.get(0);
	}

	private Object getUserByEmail(String username) {
		List<User> users = findEntityByJpql("From User u where u.username = ?", username);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public User getUserByUsernameAndPassword(User user, List<ErrorMessage> errorMessages) {
		List<User> users = findEntityByJpql("From User u where u.username = ? or u.phone = ? or u.email = ?",
				user.getUsername(), user.getUsername(), user.getUsername());
		if (users.isEmpty()) {
			errorMessages.add(new ErrorMessage("userNotRegister"));
			return null;
		}
		User user2 = users.get(0);
		if (!user2.getPassword().equals(user.getPassword())) {
			errorMessages.add(new ErrorMessage("passwordError"));
			return null;
		}
		return user2;
	}

	@Override
	public void registerUser(User user, List<ErrorMessage> errorMessages) {
		if (getUserByPhone(user.getPhone()) != null) {
			errorMessages.add(new ErrorMessage("phoneUsed"));
			return;
		}
		if (getUserByEmail(user.getEmail()) != null) {
			errorMessages.add(new ErrorMessage("emailUsed"));
			return;
		}
		if (getUserByUsername(user.getUsername()) != null) {
			errorMessages.add(new ErrorMessage("usernameUsed"));
			return;
		}
		user.setPassword(Md5Util.md5(user.getPassword()));
		saveEntitiy(user);
	}

	@Override
	public void addReceiveAddress(ReceiveAddress receiveAddress) {
		User user = receiveAddress.getUser();
		user = findEntity(user.getId());
		receiveAddress.setUser(user);
		receiveAddressService.saveEntitiy(receiveAddress);
	}

	@Override
	public void setDefaultReceiveAddress(Integer userId, Integer receiveAddressId) {
		User user = findEntity(userId);
		ReceiveAddress receiveAddress = receiveAddressService.findEntity(receiveAddressId);
		receiveAddress.setUser(user);
		List<ReceiveAddress> receiveAddressesByUser = receiveAddressService.getReceiveAddressesByUser(user);
		for (ReceiveAddress address : receiveAddressesByUser) {
			address.setSelected(false);
		}
		receiveAddress.setSelected(true);
	}

}
