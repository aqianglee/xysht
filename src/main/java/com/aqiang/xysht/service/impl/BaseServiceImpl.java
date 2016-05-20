package com.aqiang.xysht.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.aqiang.xysht.dao.BaseDao;
import com.aqiang.xysht.service.BaseService;

@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	Class<T> clazz;

	protected BaseDao<T> dao;

	@Resource
	public abstract void setDao(BaseDao<T> dao);

	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	public void saveEntitiy(T t) {
		dao.saveEntitiy(t);
	}

	public T margeEntity(T t) {
		return dao.margeEntity(t);
	}

	public void deleteEntity(int id) {
		dao.deleteEntity(id);
	}

	public void batchEntityByJpql(String jpql, Object... objects) {
		dao.batchEntityByJpql(jpql, objects);
	}

	public T findEntity(Integer id) {
		return dao.findEntity(id);
	}

	public List<T> findEntityByJpql(String jpql, Object... objects) {
		return dao.findEntityByJpql(jpql, objects);
	}

	@Override
	public List<T> findEntityByJpql(String jpql, List<Object> objects, int first, int max) {
		return dao.findEntityByJpql(jpql, objects, first, max);
	}

	@Override
	public void deleteAll() {
		String jpql = String.format("Delete %s e", clazz.getSimpleName());
		batchEntityByJpql(jpql);
	}

	@Override
	public List<T> getAll() {
		String jpql = String.format("From %s", clazz.getSimpleName());
		return findEntityByJpql(jpql);
	}
}
