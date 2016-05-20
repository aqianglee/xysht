package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.Supermarket;

public class ClassfyServiceImplTest5 extends BaseTest {

	@Test
	public void testDelete() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		Classfy classfy = createClassfy(supermarket, null, "衣服类", ClassfyLevel.LEVEL_ONE, "1");
		isEq(classfyService.getAll().size(), 1);
		classfyService.delete(classfy);
		isEq(classfyService.getAll().size(), 0);
	}

	@Test
	public void testDelete2() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		Classfy classfy = createClassfy(supermarket, null, "衣服类", ClassfyLevel.LEVEL_ONE, "1");
		createClassfy(supermarket, classfy, "牛仔裤", ClassfyLevel.LEVEL_TWO, "2");
		isEq(classfyService.getAll().size(), 2);
		classfyService.delete(classfy);
		isEq(classfyService.getAll().size(), 0);
	}

	@Test
	public void testDelete3() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		Classfy classfy = createClassfy(supermarket, null, "衣服类", ClassfyLevel.LEVEL_ONE, "1");
		Classfy classfy2 = createClassfy(supermarket, classfy, "牛仔裤", ClassfyLevel.LEVEL_TWO, "2");
		createClassfy(supermarket, classfy2, "LEE", ClassfyLevel.LEVEL_THREE, "3");
		isEq(classfyService.getAll().size(), 3);
		classfyService.delete(classfy);
		isEq(classfyService.getAll().size(), 0);
	}

	private Supermarket createSupermarket(String name, String address) {
		Supermarket supermarket = new Supermarket();
		supermarket.setName(name);
		supermarket.setAddress(address);
		supermarketService.saveEntitiy(supermarket);
		return supermarket;
	}

	private Classfy createClassfy(Supermarket supermarket, Classfy parent, String name, String level, String number) {
		Classfy classfy = new Classfy();
		classfy.setLevel(level);
		classfy.setName(name);
		classfy.setNumber(number);
		classfy.setParent(parent);
		classfy.setSupermarket(supermarket);
		classfyService.saveEntitiy(classfy);
		return classfy;
	}

}
