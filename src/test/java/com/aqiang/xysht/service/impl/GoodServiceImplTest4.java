package com.aqiang.xysht.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Tag;

public class GoodServiceImplTest4 extends BaseTest {

	@Test
	public void testAddTags() {
		Good good = new Good();
		tagService.createTag("一折");
		tagService.createTag("一折");
		tagService.createTag("9折");
		tagService.createTag("9折");
		List<Tag> all = tagService.getAll();
		Integer[] tagsId = { all.get(0).getId(), all.get(1).getId(),
				all.get(2).getId(), all.get(3).getId() };
		goodService.addTags(good, all);
		isEq(good.getTags(), StringUtils.join(tagsId, ","));
	}
}
