package com.aqiang.xysht.dao;

import java.util.List;

public interface BaseDao<T> {

	public void saveEntitiy(T t);

	public T margeEntity(T t);

	public void deleteEntity(int id);

	public void batchEntityByJpql(String jpql, Object... objects);

	public T findEntity(Integer id);

	public List<T> findEntityByJpql(String jpql, Object... objects);

	public List<T> findEntityByJpql(String jpql, List<Object> objects, int first, int max);

}
