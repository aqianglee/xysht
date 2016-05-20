package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.Supermarket;

public class ClassfyServiceImplTest extends BaseTest {

	@Test
	public void testGetAllClassfies() {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("校园超市");
		supermarketService.saveEntitiy(supermarket);
		createClassfy(supermarket, "方便面", null);
		List<Classfy> classfies = classfyService.getAllClassfies(supermarket);
		isEq(1, classfies.size());
	}

	private void createClassfy(Supermarket supermarket, String name, Classfy parent) {
		Classfy classfy = new Classfy();
		classfy.setSupermarket(supermarket);
		classfy.setName(name);
		classfy.setParent(parent);
		classfyService.saveEntitiy(classfy);
	}

}
