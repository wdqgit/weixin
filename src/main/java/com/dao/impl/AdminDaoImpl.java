package com.dao.impl;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.baseDao.BaseDaoImpl;
import com.dao.AdminDao;
import com.domain.po.Admin;
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{

	@Override
	public Admin getAdminByPhone(String hql, String phone) {
		Query query = getSession().createQuery(hql);
		query.setParameter(0, phone);
		return (Admin) query.uniqueResult();
	}

}
