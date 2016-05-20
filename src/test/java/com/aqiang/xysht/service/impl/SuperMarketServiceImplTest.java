package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.ShopKeeper;
import com.aqiang.xysht.entities.Supermarket;

public class SuperMarketServiceImplTest extends BaseTest {

	@Test
	public void testGetAllSuperMarketsByShopKeeper() {
		ShopKeeper shopKeeper = new ShopKeeper();
		shopKeeper.setUsername("aqiang");
		shopKeeper.setPhone("18826202524");
		shopKeeperService.saveEntitiy(shopKeeper);

		Supermarket supermarket = new Supermarket();
		supermarket.setShopKeeper(shopKeeper);
		supermarket.setAddress("�㶫ʡ�麣��");
		supermarket.setName("���˼�");
		supermarket.setBeginSendPrice(10D);
		supermarketService.saveEntitiy(supermarket);
		List<Supermarket> markets = supermarketService
				.getSupermarketsByShopKeeper(shopKeeper);
		isEq(1, markets.size());
		isEq("�㶫ʡ�麣��", markets.get(0).getAddress());
		isEq("���˼�", markets.get(0).getName());
		isEq(10.0, markets.get(0).getBeginSendPrice());
	}

}
