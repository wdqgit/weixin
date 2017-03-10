package com.dao;

import com.baseDao.BaseDao;
import com.domain.po.People;

public interface PeopleDao extends BaseDao<People>{

	People getByPhone(String hql, String phone);

}
