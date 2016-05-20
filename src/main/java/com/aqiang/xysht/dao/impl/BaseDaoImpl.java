package com.aqiang.xysht.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqiang.xysht.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);

	Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void saveEntitiy(T t) {
		entityManager.persist(t);
	}

	@Override
	public T margeEntity(T t) {
		return entityManager.merge(t);
	}

	@Override
	public void deleteEntity(int id) {
		T t = this.findEntity(id);
		entityManager.remove(t);
	}

	@Override
	public void batchEntityByJpql(String jpql, Object... objects) {
		Query query = entityManager.createQuery(jpql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i + 1, objects[i]);
		}
		query.executeUpdate();
	}

	@Override
	public T findEntity(Integer id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public List<T> findEntityByJpql(String jpql, Object... objects) {
		Query query = entityManager.createQuery(jpql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i + 1, objects[i]);
		}
		return query.getResultList();
	}

	@Override
	public List<T> findEntityByJpql(String jpql, List<Object> objects, int first, int max) {
		Query query = entityManager.createQuery(jpql);
		if (objects != null) {
			for (int i = 0; i < objects.size(); i++) {
				query.setParameter(i + 1, objects.get(i));
			}
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.getResultList();
	}

}
