package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.utils.Md5Util;

public class ShopKeeperServiceImplTest extends BaseTest {

	@Test
	public void testRegister() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPassword("123456");
		shopKeeperService.register(shopKeeper);
		List<ShopKeeper> list = shopKeeperService.getAll();
		isEq(list.get(0).getUsername(), "aqiang");
		isEq(list.get(0).getPassword(), Md5Util.md5("123456"));
	}

}
