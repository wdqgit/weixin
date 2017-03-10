package com.baseDao;

import java.io.Serializable;
import java.util.List;

import com.domain.po.PageBean;

public interface BaseDao<T> {
	void save(T t);
	void update(T t);
	void delete(Serializable id);
	T get(Serializable id);
	List<T> findAll();
	List<T> find(String hql, Object...objects);
	PageBean<T> getPageBean(int currentPage, int pageSize, String hqlList, String hqlCount, 
			List<Object> params);


}
