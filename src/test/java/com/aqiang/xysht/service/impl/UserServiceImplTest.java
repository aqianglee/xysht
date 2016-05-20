package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.User;

public class UserServiceImplTest extends BaseTest {

	@Test
	public void testUpdateUser() {
		User user = createUser("1号楼223", "18826202524", "李志强");
		userService.updateUser(user);
		List<User> list = userService.getAll();
		isEq(1, list.size());
		User user2 = list.get(0);
		isEq("1号楼223", user2.getAddress());
		isEq("18826202524", user2.getPhone());
		isEq("李志强", user2.getCompellation());
	}

	@Test
	public void testUpdateUser2() {
		userService.saveEntitiy(createUser("1号楼223", "18826202524", "李志强"));
		User user = createUser("1号楼223", "18826202524", "李志强");
		userService.updateUser(user);
		List<User> list = userService.getAll();
		isEq(1, list.size());
		User user2 = list.get(0);
		isEq("1号楼223", user2.getAddress());
		isEq("18826202524", user2.getPhone());
		isEq("李志强", user2.getCompellation());
	}

	@Test
	public void testUpdateUser3() {
		userService.saveEntitiy(createUser("1号楼223", "18826202524", "李志强"));
		User user = createUser("拱北中建大厦1420", "18826202524", "李志强");
		userService.updateUser(user);
		List<User> list = userService.getAll();
		isEq(1, list.size());
		User user2 = list.get(0);
		isEq("拱北中建大厦1420", user2.getAddress());
		isEq("18826202524", user2.getPhone());
		isEq("李志强", user2.getCompellation());
	}

	private User createUser(String address, String phone, String compellation) {
		User user = new User();
		user.setAddress(address);
		user.setPhone(phone);
		user.setCompellation(compellation);
		return user;
	}
}
