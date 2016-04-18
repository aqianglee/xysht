package com.aqiang.xysht.service.impl;

import java.util.List;

import org.junit.Test;

import com.aqiang.xysht.BaseTest;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.entities.TagName;

public class TagServiceImplTest extends BaseTest {

	@Test
	public void testGetAllTags() {
		createTag(TagName.DISCOUNT);
		createTag(TagName.HOT);
		List<Tag> tags = tagService.getAllTags();
		isEq(2, tags.size());
		isEq(TagName.DISCOUNT, tags.get(0).getName());
		isEq(TagName.HOT, tags.get(1).getName());
	}

	private void createTag(String name) {
		Tag tag = new Tag();
		tag.setName(name);
		tagService.saveEntitiy(tag);
	}

}
