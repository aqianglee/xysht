package com.aqiang.xysht.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Supermarket;

public class SuperMarketServiceImplTest2 extends BaseTest {

	@Test
	public void testDelete() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		isEq(superMarketService.getAll().size(), 1);
		superMarketService.delete(supermarket, true);
		isEq(superMarketService.getAll().size(), 0);
	}

	@Test
	public void testDelete2() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		isEq(superMarketService.getAll().size(), 1);
		superMarketService.delete(supermarket, false);
		isEq(superMarketService.getAll().size(), 0);
	}

	@Test
	public void testDelete3() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		isEq(superMarketService.getAll().size(), 1);
		superMarketService.delete(supermarket, null);
		isEq(superMarketService.getAll().size(), 0);
	}

	@Test
	public void testDelete4() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		Classfy classfy = createClassfy(supermarket, null, "衣服类", ClassfyLevel.LEVEL_ONE, "1");
		Classfy classfy2 = createClassfy(supermarket, classfy, "牛仔裤", ClassfyLevel.LEVEL_TWO, "2");
		createClassfy(supermarket, classfy2, "LEE", ClassfyLevel.LEVEL_THREE, "3");
		isEq(superMarketService.getAll().size(), 1);
		superMarketService.delete(supermarket, true);
		isEq(superMarketService.getAll().size(), 0);
	}

	@Test
	public void testDelete5() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		Classfy classfy = createClassfy(supermarket, null, "衣服类", ClassfyLevel.LEVEL_ONE, "1");
		Classfy classfy2 = createClassfy(supermarket, classfy, "牛仔裤", ClassfyLevel.LEVEL_TWO, "2");
		createClassfy(supermarket, classfy2, "LEE", ClassfyLevel.LEVEL_THREE, "3");
		isEq(superMarketService.getAll().size(), 1);
		List<ErrorMessage> list = superMarketService.delete(supermarket, false);
		isEq(superMarketService.getAll().size(), 1);
		isEq(list.size(), 1);
		isEq(list.get(0).getKey(), "have.child");
	}

	@Test
	public void testDelete6() {
		Supermarket supermarket = createSupermarket("便利店", "广东珠海");
		Classfy classfy = createClassfy(supermarket, null, "衣服类", ClassfyLevel.LEVEL_ONE, "1");
		Classfy classfy2 = createClassfy(supermarket, classfy, "牛仔裤", ClassfyLevel.LEVEL_TWO, "2");
		createClassfy(supermarket, classfy2, "LEE", ClassfyLevel.LEVEL_THREE, "3");
		isEq(superMarketService.getAll().size(), 1);
		List<ErrorMessage> list = superMarketService.delete(supermarket, null);
		isEq(superMarketService.getAll().size(), 1);
		isEq(list.size(), 1);
		isEq(list.get(0).getKey(), "have.child");
	}

	private Supermarket createSupermarket(String name, String address) {
		Supermarket supermarket = new Supermarket();
		supermarket.setName(name);
		supermarket.setAddress(address);
		superMarketService.saveEntitiy(supermarket);
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
