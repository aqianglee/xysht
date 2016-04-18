package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.ShopKeeper;

public class ShopKeeperServiceImplTest3 extends BaseTest {

	@Test
	public void testValidateRegister() {
		ShopKeeper shopKeeper = new ShopKeeper();
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(5, messages.size());
		isEq("missing.username", messages.get(0).getKey());
		isEq("missing.password", messages.get(1).getKey());
		isEq("missing.repassword", messages.get(2).getKey());
		isEq("missing.phone", messages.get(3).getKey());
		isEq("missing.email", messages.get(4).getKey());
	}
	
	@Test
	public void testValidateRegister2() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setEmail("695182311@qq.com");
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(4, messages.size());
		isEq("missing.username", messages.get(0).getKey());
		isEq("missing.password", messages.get(1).getKey());
		isEq("missing.repassword", messages.get(2).getKey());
		isEq("missing.phone", messages.get(3).getKey());
	}
	
	@Test
	public void testValidateRegister3() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setPhone("18826202524");
		shopKeeper.setEmail("695182311@qq.com");
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(3, messages.size());
		isEq("missing.username", messages.get(0).getKey());
		isEq("missing.password", messages.get(1).getKey());
		isEq("missing.repassword", messages.get(2).getKey());
	}
	
	@Test
	public void testValidateRegister4() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setRepassword("1234567");
		shopKeeper.setPhone("18826202524");
		shopKeeper.setEmail("695182311@qq.com");
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(2, messages.size());
		isEq("missing.username", messages.get(0).getKey());
		isEq("missing.password", messages.get(1).getKey());
	}
	
	@Test
	public void testValidateRegister5() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setPassword("123456");
		shopKeeper.setRepassword("1234567");
		shopKeeper.setPhone("18826202524");
		shopKeeper.setEmail("695182311@qq.com");
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(1, messages.size());
		isEq("missing.username", messages.get(0).getKey());
	}
	
	@Test
	public void testValidateRegister6() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("123456");
		shopKeeper.setRepassword("1234567");
		shopKeeper.setPhone("18826202524");
		shopKeeper.setEmail("695182311@qq.com");
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(1, messages.size());
		isEq("passwordNotEqRepassword", messages.get(0).getKey());
	}
	
	@Test
	public void testValidateRegister7() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("123456");
		shopKeeper.setRepassword("123456");
		shopKeeper.setPhone("18826202524");
		shopKeeper.setEmail("695182311@qq.com");
		List<ErrorMessage> messages = shopKeeperService.validateRegister(shopKeeper);
		isEq(0, messages.size());
	}
}
