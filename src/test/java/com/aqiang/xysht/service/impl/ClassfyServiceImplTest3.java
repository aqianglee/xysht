package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.Supermarket;

public class ClassfyServiceImplTest3 extends BaseTest {

	@Test
	public void testUpdateClassfy() {
		Supermarket supermarket = new Supermarket();
		supermarket.setName("supermarket");
		supermarketService.saveEntitiy(supermarket);
		Classfy clothes = createClassfy(supermarket, "衣服类", null);
		clothes = classfyService.updateClassfy(clothes);
		Classfy coats = createClassfy(supermarket, "外套", clothes);
		coats = classfyService.updateClassfy(coats);
		Classfy sports = createClassfy(supermarket, "运动服", coats);
		sports = classfyService.updateClassfy(sports);
		Classfy jeans = createClassfy(supermarket, "牛仔裤", clothes);
		jeans = classfyService.updateClassfy(jeans);
		Classfy snacks = createClassfy(supermarket, "零食类", null);
		snacks = classfyService.updateClassfy(snacks);
		Classfy chips = createClassfy(supermarket, "薯片", snacks);
		chips = classfyService.updateClassfy(chips);
		Classfy instantNoodles = createClassfy(supermarket, "方便面", snacks);
		instantNoodles = classfyService.updateClassfy(instantNoodles);
		List<Classfy> all = classfyService.getAll();
		isEq(7, all.size());
		isEq(ClassfyLevel.LEVEL_ONE, all.get(0).getLevel());
		isEq("衣服类", all.get(0).getName());
		isEq("supermarket", all.get(0).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(1).getLevel());
		isEq("外套", all.get(1).getName());
		isEq("supermarket", all.get(1).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_THREE, all.get(2).getLevel());
		isEq("运动服", all.get(2).getName());
		isEq("supermarket", all.get(2).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(3).getLevel());
		isEq("牛仔裤", all.get(3).getName());
		isEq("supermarket", all.get(3).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_ONE, all.get(4).getLevel());
		isEq("零食类", all.get(4).getName());
		isEq("supermarket", all.get(4).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(5).getLevel());
		isEq("薯片", all.get(5).getName());
		isEq("supermarket", all.get(5).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(6).getLevel());
		isEq("方便面", all.get(6).getName());
		isEq("supermarket", all.get(6).getSupermarket().getName());
		isNull(all.get(0).getNumber());
		isNull(all.get(1).getNumber());
		isNull(all.get(2).getNumber());
		isNull(all.get(3).getNumber());
		isNull(all.get(4).getNumber());
		isNull(all.get(5).getNumber());
		isNull(all.get(6).getNumber());
		clothes.setNumber("1");
		coats.setNumber("2");
		sports.setNumber("3");
		jeans.setNumber("4");
		snacks.setNumber("5");
		chips.setNumber("6");
		instantNoodles.setNumber("7");
		clothes = classfyService.updateClassfy(clothes);
		coats = classfyService.updateClassfy(coats);
		sports = classfyService.updateClassfy(sports);
		jeans = classfyService.updateClassfy(jeans);
		snacks = classfyService.updateClassfy(snacks);
		chips = classfyService.updateClassfy(chips);
		instantNoodles = classfyService.updateClassfy(instantNoodles);
		isEq(7, all.size());
		isEq(ClassfyLevel.LEVEL_ONE, all.get(0).getLevel());
		isEq("衣服类", all.get(0).getName());
		isEq("supermarket", all.get(0).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(1).getLevel());
		isEq("外套", all.get(1).getName());
		isEq("supermarket", all.get(1).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_THREE, all.get(2).getLevel());
		isEq("运动服", all.get(2).getName());
		isEq("supermarket", all.get(2).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(3).getLevel());
		isEq("牛仔裤", all.get(3).getName());
		isEq("supermarket", all.get(3).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_ONE, all.get(4).getLevel());
		isEq("零食类", all.get(4).getName());
		isEq("supermarket", all.get(4).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(5).getLevel());
		isEq("薯片", all.get(5).getName());
		isEq("supermarket", all.get(5).getSupermarket().getName());
		isEq(ClassfyLevel.LEVEL_TWO, all.get(6).getLevel());
		isEq("方便面", all.get(6).getName());
		isEq("supermarket", all.get(6).getSupermarket().getName());
		isEq("1", all.get(0).getNumber());
		isEq("2", all.get(1).getNumber());
		isEq("3", all.get(2).getNumber());
		isEq("4", all.get(3).getNumber());
		isEq("5", all.get(4).getNumber());
		isEq("6", all.get(5).getNumber());
		isEq("7", all.get(6).getNumber());
	}

	private Classfy createClassfy(Supermarket supermarket, String name,
			Classfy parent) {
		Classfy classfy = new Classfy();
		classfy.setSupermarket(supermarket);
		classfy.setName(name);
		classfy.setParent(parent);
		return classfy;
	}
}
