package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.HotClassfyName;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.entities.TagName;

public class GoodServiceImplTest5 extends BaseTest {

	@Test
	public void testGetHotGoods() {
		Tag hotTag = tagService.createTag(TagName.HOT);
		Tag discountTag = tagService.createTag(TagName.DISCOUNT);

		Supermarket supermarket = createSupermarket("supermarket");
		Supermarket supermarket2 = createSupermarket("supermarket2");
		Supermarket supermarket3 = createSupermarket("supermarket3");

		Classfy snack = createClassfy(supermarket, null, HotClassfyName.SNACK, ClassfyLevel.LEVEL_ONE);
		Classfy snack2 = createClassfy(supermarket2, null, HotClassfyName.SNACK, ClassfyLevel.LEVEL_ONE);
		Classfy snack3 = createClassfy(supermarket3, null, HotClassfyName.SNACK, ClassfyLevel.LEVEL_ONE);

		Classfy drinking = createClassfy(supermarket, null, HotClassfyName.DRINKING, ClassfyLevel.LEVEL_ONE);
		Classfy drinking2 = createClassfy(supermarket2, null, HotClassfyName.DRINKING, ClassfyLevel.LEVEL_ONE);
		Classfy drinking3 = createClassfy(supermarket3, null, HotClassfyName.DRINKING, ClassfyLevel.LEVEL_ONE);

		Classfy noodles = createClassfy(supermarket, snack, "方便面", ClassfyLevel.LEVEL_TWO);
		createGoods(noodles, "康师傅红烧牛肉面", false, 3.5, 10);
		createGoods(noodles, "统一老坛酸菜牛肉面", false, 3.5, 25);
		createGoods(noodles, "康师傅酸辣牛肉面", false, 3.5, 2);
		createGoods(noodles, "康师傅鸡蛋面", false, 3.5, 50);
		createGoods(noodles, "康师傅豉香排骨面", false, 3.5, 18);
		Classfy hot = createClassfy(supermarket, snack, "辣条", ClassfyLevel.LEVEL_TWO);
		createGoods(hot, "李小鹏", false, 2.5, 10);

		Classfy noodles2 = createClassfy(supermarket2, snack2, "方便面", ClassfyLevel.LEVEL_TWO);
		createGoods(noodles2, "康师傅红烧牛肉面", false, 3.5, 70);
		createGoods(noodles2, "统一老坛酸菜牛肉面", false, 3.5, 10);
		createGoods(noodles2, "康师傅酸辣牛肉面", false, 3.5, 12);
		createGoods(noodles2, "康师傅鸡蛋面", false, 3.5, 50);
		createGoods(noodles2, "康师傅豉香排骨面", false, 3.5, 28);

		Classfy classfy = createSnackClassfy();
		List<Good> hotGoods = goodService.getHotGoods(classfy);
		isEq(6, hotGoods.size());
		isEq(hotGoods.get(0).getName(), "康师傅鸡蛋面");
		isEq(hotGoods.get(1).getName(), "统一老坛酸菜牛肉面");
		isEq(hotGoods.get(2).getName(), "康师傅豉香排骨面");
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

	private Classfy createClassfy(Supermarket supermarket, Classfy parent, String name, String level) {
		Classfy classfy = new Classfy();
		classfy.setName(name);
		classfy.setLevel(level);
		classfy.setParent(parent);
		classfy.setSupermarket(supermarket);
		classfyService.saveEntitiy(classfy);
		return classfy;
	}

	private Supermarket createSupermarket(String name) {
		Supermarket supermarket = new Supermarket();
		supermarket.setName(name);
		supermarketService.saveEntitiy(supermarket);
		return supermarket;
	}

	private Classfy createSnackClassfy() {
		Classfy classfy = new Classfy();
		classfy.setName(HotClassfyName.SNACK);
		return classfy;
	}

}
