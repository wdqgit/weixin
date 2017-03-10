package com.baseDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.domain.po.PageBean;
public class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	private SessionFactory sessionFactory;
	
	protected Class<T> clazz;
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().merge(t);
	}

	@Override
	public void delete(Serializable id) {
		Object o = get(id);
		if(o != null){
			getSession().delete(o);
		}
	}

	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		Query query = getSession().createQuery("from " + clazz.getSimpleName());
		return query.list();
	}
	
	public List<T> find(String hql, Object...params){
		Query query = getSession().createQuery(hql);
		if(params != null && params.length > 0){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		
		return query.list();
	}
	public PageBean<T> getPageBean(int currentPage, int pageSize, String hqlList, String hqlCount, 
			List<Object> params){
		Query query = getSession().createQuery(hqlList);
		if(params != null && params.size() > 0){
			for(int i = 0; i < params.size(); i++){
				query.setParameter(i, params.get(i));
			}
		}
		System.out.println(currentPage + " " + pageSize);
		List<T> recordList = query.setFirstResult((currentPage-1) * pageSize)//
				.setMaxResults(pageSize).list();
		query = getSession().createQuery(hqlCount);
		if(params != null && params.size() > 0){
			for(int i = 0; i < params.size(); i++){
				query.setParameter(i, params.get(i));
			}
		}
		Long recordCount = (Long) query.uniqueResult();
		return new PageBean<T>(currentPage, pageSize, recordCount.intValue(), recordList);
		
	}

}
