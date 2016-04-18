package com.aqiang.xysht.service.impl;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;

public class TagServiceImplTest2 extends BaseTest {

	@Test
	public void testGetTags() {
		Integer[] tagsId = null;
		isNull(tagService.getTags(tagsId));
	}

}
