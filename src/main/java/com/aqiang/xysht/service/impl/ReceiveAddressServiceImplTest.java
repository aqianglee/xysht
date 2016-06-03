package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ReceiveAddress;
import com.aqiang.xysht.entities.User;

public class ReceiveAddressServiceImplTest extends BaseTest {

	@Test
	public void testGetReceiveAddressesByUser() {
		User user = new User();
		user.setEmail("695182311@qq.com");
		user.setPassword("123");
		user.setUsername("aqiang");
		user.setPhone("18826202524");
		userService.registerUser(user, null);
		createReceiveAddress(user, "李志强", "青海大学学生公寓一号楼223", "18826202524", false);
		createReceiveAddress(user, "李志强", "广东省珠海市中建大厦1420", "18826202524", true);
		createReceiveAddress(null, "陈大文", "青海大学学生公寓一号楼223", "18826202524", false);
		List<ReceiveAddress> receiveAddresses = receiveAddressService.getReceiveAddressesByUser(user);
		isEq(2, receiveAddresses.size());
	}

	private ReceiveAddress createReceiveAddress(User user, String compellation, String address, String phone,
			Boolean selected) {
		ReceiveAddress receiveAddress = new ReceiveAddress();
		receiveAddress.setUser(user);
		receiveAddress.setAddress(address);
		receiveAddress.setCompellation(compellation);
		receiveAddress.setPhone(phone);
		receiveAddress.setSelected(selected);
		receiveAddressService.saveEntitiy(receiveAddress);
		return receiveAddress;
	}
}
