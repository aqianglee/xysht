package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.Supermarket;

public class ClassfyServiceImplTest2 extends BaseTest {

	@Test
	public void testGetClassfiesByParent() {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("校园超市");
		supermarketService.saveEntitiy(supermarket);
		Classfy clothes = createClassfy(supermarket, "衣服类", null);
		createClassfy(supermarket, "外套", clothes);
		createClassfy(supermarket, "牛仔裤", clothes);
		Classfy snacks = createClassfy(supermarket, "零食类", null);
		createClassfy(supermarket, "辣条", snacks);
		createClassfy(supermarket, "方便面", snacks);
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, clothes);
		isEq("外套", classfies.get(0).getName());
		isEq("牛仔裤", classfies.get(1).getName());
		isEq(2, classfies.size());
	}

	@Test
	public void testGetClassfiesByParent2() {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("校园超市");
		supermarketService.saveEntitiy(supermarket);
		Classfy clothes = createClassfy(supermarket, "衣服类", null);
		createClassfy(supermarket, "外套", clothes);
		createClassfy(supermarket, "牛仔裤", clothes);
		Classfy snacks = createClassfy(supermarket, "零食类", null);
		createClassfy(supermarket, "辣条", snacks);
		createClassfy(supermarket, "方便面", snacks);
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, null);
		isEq(2, classfies.size());
		isEq("衣服类", classfies.get(0).getName());
		isEq("零食类", classfies.get(1).getName());
	}

	@Test
	public void testGetClassfiesByParent3() {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("校园超市");
		supermarketService.saveEntitiy(supermarket);
		Classfy clothes = createClassfy(supermarket, "衣服类", null);
		createClassfy(supermarket, "外套", clothes);
		createClassfy(supermarket, "牛仔裤", clothes);
		Classfy snacks = createClassfy(supermarket, "零食类", null);
		createClassfy(supermarket, "辣条", snacks);
		createClassfy(supermarket, "方便面", snacks);
		List<Classfy> classfies = classfyService.getClassfiesByParent(supermarket, snacks);
		isEq(2, classfies.size());
		isEq("辣条", classfies.get(0).getName());
		isEq("方便面", classfies.get(1).getName());
	}

	private Classfy createClassfy(Supermarket supermarket, String name, Classfy parent) {
		Classfy classfy = new Classfy();
		classfy.setSupermarket(supermarket);
		classfy.setName(name);
		classfy.setParent(parent);
		classfyService.saveEntitiy(classfy);
		return classfy;
	}

}
