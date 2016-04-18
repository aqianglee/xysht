package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.Good;

public class GoodServiceImplTest extends BaseTest {

	@Test
	public void testGetAllGoodsByClassfy() {
		Classfy classfy = new Classfy();
		classfy.setName("方便面");
		classfyService.saveEntitiy(classfy);
		createGood(classfy, "康师傅红烧牛肉面", 4.5);
		createGood(classfy, "统一老谭酸菜面", 4.0);
		createGood(null, "康师傅矿泉水", 1D);
		List<Good> goods = goodService.getAllGoodsByClassfy(classfy);
		isEq(2, goods.size());
		isEq(4.5, goods.get(0).getPrice());
		isEq("康师傅红烧牛肉面", goods.get(0).getName());
		isEq(4.0, goods.get(1).getPrice());
		isEq("统一老谭酸菜面", goods.get(1).getName());
	}

	private void createGood(Classfy classfy, String name, Double price) {
		Good good = new Good();
		good.setClassfy(classfy);
		good.setName(name);
		good.setPrice(price);
		goodService.saveEntitiy(good);
	}

}
