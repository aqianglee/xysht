package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.User;

public class UserServiceImplTest2 extends BaseTest {

	@Test
	public void testGetUserByPhone() {
		User user = createUser("1号楼223", "18826202524", "李志强");
		userService.saveEntitiy(user);
		User userByPhone = userService.getUserByPhone("18826202524");
		isNotNull(userByPhone);
	}

	@Test
	public void testGetUserByPhone2() {
		userService.saveEntitiy(createUser("1号楼223", "18826202524", "李志强"));
		User userByPhone = userService.getUserByPhone("18397101270");
		isNull(userByPhone);
	}

	private User createUser(String address, String phone, String compellation) {
		User user = new User();
		user.setAddress(address);
		user.setPhone(phone);
		user.setCompellation(phone);
		return user;
	}
}
