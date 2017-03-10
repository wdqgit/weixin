package com.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baseDao.BaseDaoImpl;
import com.dao.ActivityDao;
import com.domain.po.Activity;
@Repository
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao{

}
