package com.aqiang.xysht.service;

import java.util.List;

import com.aqiang.xysht.entities.Tag;

public interface TagService extends BaseService<Tag> {

	public List<Tag> getAllTags();

	public Tag createTag(String name);

	public List<Tag> getTags(Integer[] tagsId);

	public Tag getTagByName(String hot);
}
