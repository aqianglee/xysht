package com.aqiang.xysht.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.entities.Classfy;
import com.aqiang.xysht.entities.ClassfyLevel;
import com.aqiang.xysht.entities.ErrorMessage;
import com.aqiang.xysht.entities.Good;
import com.aqiang.xysht.entities.Supermarket;
import com.aqiang.xysht.service.ClassfyService;
import com.aqiang.xysht.service.GoodService;
import com.aqiang.xysht.service.ValidateService;

@Service
@Transactional
public class ClassfyServiceImpl extends BaseServiceImpl<Classfy> implements ClassfyService {
	@Autowired
	private ValidateService validateService;
	@Autowired
	private GoodService goodService;

	@Resource(name = "classfyDao")
	@Override
	public void setDao(BaseDao<Classfy> dao) {
		this.dao = dao;
	}

	@Override
	public List<Classfy> getAllClassfies(Supermarket supermarket) {
		return dao.findEntityByJpql("From Classfy c where c.supermarket = ?", supermarket);
	}

	@Override
	public List<Classfy> getClassfiesByParent(Supermarket supermarket, Classfy classfy) {
		String jpql = null;
		if (classfy != null) {
			jpql = "From Classfy c where c.supermarket = ? and parent = ?";
			return dao.findEntityByJpql(jpql, supermarket, classfy);
		} else {
			jpql = "From Classfy c where c.supermarket = ? and parent is null";
			return dao.findEntityByJpql(jpql, supermarket);
		}
	}

	@Override
	public Classfy updateClassfy(Classfy classfy) {
		if (classfy.getParent() != null && classfy.getParent().getId() != null) {
			classfy.setParent(findEntity(classfy.getParent().getId()));
		} else {
			classfy.setParent(null);
		}
		classfy.setLevel(getClassfyLevel(classfy.getParent()));
		return margeEntity(classfy);
	}

	private String getClassfyLevel(Classfy parent) {
		if (parent == null) {
			return ClassfyLevel.LEVEL_ONE;
		}
		if (ClassfyLevel.LEVEL_ONE.equals(parent.getLevel())) {
			return ClassfyLevel.LEVEL_TWO;
		}
		return ClassfyLevel.LEVEL_THREE;
	}

	@Override
	public void validateClassfy(Classfy classfy, List<ErrorMessage> messages) {
		validateService.validate(messages, classfy.getName(), "classfyName");
	}

	@Override
	public void delete(Classfy classfy) {
		List<Good> goods = goodService.getAllGoodsByClassfy(classfy);
		for (Good good : goods) {
			goodService.delete(good);
		}
		List<Classfy> classfies = getClassfiesByParent(classfy.getSupermarket(), classfy);
		for (Classfy classfy2 : classfies) {
			delete(classfy2);
		}
		deleteEntity(classfy.getId());
	}
}
