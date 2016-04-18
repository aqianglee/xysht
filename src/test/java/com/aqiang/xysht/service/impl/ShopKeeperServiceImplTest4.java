package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.utils.Md5Util;

public class ShopKeeperServiceImplTest4 extends BaseTest {

	@Test
	public void testGetShopKeeperByUsername() {
		ShopKeeper shopKeeper = shopKeeperService.getShopKeeperByUsername("aqiang");
		isNull(shopKeeper);
	}

	@Test
	public void testGetShopKeeperByUsername2() {
		ShopKeeper keeper = new ShopKeeper();
		keeper.setUsername("aqiang");
		keeper.setPassword("123456");
		keeper.setRepassword("123456");
		shopKeeperService.register(keeper);
		ShopKeeper shopKeeper = shopKeeperService.getShopKeeperByUsername("aqiang");
		isNotNull(shopKeeper);
		isEq(shopKeeper.getUsername(), "aqiang");
		isEq(shopKeeper.getPassword(), Md5Util.md5("123456"));
	}

}
