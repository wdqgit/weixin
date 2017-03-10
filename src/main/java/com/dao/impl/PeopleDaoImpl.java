package com.dao.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.baseDao.BaseDaoImpl;
import com.dao.PeopleDao;
import com.domain.po.People;
@Repository
public class PeopleDaoImpl extends BaseDaoImpl<People> implements PeopleDao{
	

	@Override
	public People getByPhone(String hql, String phone) {
		Query query = getSession().createQuery(hql);
		query.setParameter(0, phone);
		return (People) query.uniqueResult();
	}

}
