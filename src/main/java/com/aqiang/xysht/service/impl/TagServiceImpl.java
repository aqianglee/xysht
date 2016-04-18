package com.aqiang.xysht.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.Tag;
import com.aqiang.xysht.service.TagService;

@Service
@Transactional
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {

	@Override
	public List<Tag> getAllTags() {
		return findEntityByJpql("From Tag");
	}

	@Resource(name = "tagDao")
	@Override
	public void setDao(BaseDao<Tag> dao) {
		this.dao = dao;
	}

	@Override
	public Tag createTag(String name) {
		Tag tag = new Tag();
		tag.setName(name);
		margeEntity(tag);
		return tag;
	}

	@Override
	public List<Tag> getTags(Integer[] tagsId) {
		if (tagsId != null && tagsId.length > 0) {
			List<Tag> tags = new ArrayList<Tag>();
			for (Integer tagId : tagsId) {
				tags.add(findEntity(tagId));
			}
			return tags;
		}
		return null;
	}
}
