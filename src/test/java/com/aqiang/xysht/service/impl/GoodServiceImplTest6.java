package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.Good;

public class GoodServiceImplTest6 extends BaseTest {

	@Test
	public void tesGetAllDiscountGood() {
		createGoods(null, "康师傅红烧牛肉面", false, 3.5, 0);
		createGoods(null, "统一老坛酸菜牛肉面", false, 3.5, 0);
		createGoods(null, "康师傅酸辣牛肉面", false, 3.5, 0);
		createGoods(null, "康师傅牛肉面", false, 3.5, 0);
		createGoods(null, "统一老坛酸菜牛肉面", false, 3.5, 0);
		List<Good> goods = goodService.getAllDiscountGood();
		isEq(3, goods.size());
	}

	private Good createGoods(Classfy classfy, String name, Boolean offline, Double price, Integer salesVolume) {
		Good good = new Good();
		good.setClassfy(classfy);
		good.setName(name);
		good.setOffLine(offline);
		good.setPrice(price);
		good.setSalesVolume(salesVolume);
		goodService.saveEntitiy(good);
		return good;
	}

}
