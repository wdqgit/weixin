package com.dao;

import java.util.List;

import com.baseDao.BaseDao;
import com.domain.po.Activity;

public interface ActivityDao extends BaseDao<Activity>{

	List<Activity> find(String hql, Object[] params);

}
