package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.TagName;

public class TagServiceImplTest3 extends BaseTest {

	@Test
	public void testGetTagByName() {
		tagService.createTag(TagName.HOT);
		isEq(1, tagService.getAll().size());
		isNotNull(tagService.getTagByName(TagName.HOT));
		isNull(tagService.getTagByName(TagName.DISCOUNT));
	}

}
