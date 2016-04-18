package com.aqiang.xysht.dao.impl;

import org.springframework.stereotype.Repository;

import com.aqiang.xysht.dao.TagDao;
import com.aqiang.xysht.entities.Tag;

@Repository("tagDao")
public class TagDaoImpl extends BaseDaoImpl<Tag> implements TagDao{

}
