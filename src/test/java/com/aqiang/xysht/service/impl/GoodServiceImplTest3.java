package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Tag;

public class GoodServiceImplTest3 extends BaseTest {

	@Test(expected = RuntimeException.class)
	public void testAddTag() {
		Good good = new Good();
		goodService.addTag(good, new Tag());
		isEq(good.getTags(), "1");
	}

	@Test
	public void testAddTag2() {
		tagService.createTag("一折");
		Good good = new Good();
		Tag tag = tagService.getAll().get(0);
		goodService.addTag(good, tag);
		isEq(good.getTags(), tag.getId().toString());
	}

}
