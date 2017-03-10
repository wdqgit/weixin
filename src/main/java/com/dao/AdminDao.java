package com.dao;

import com.baseDao.BaseDao;
import com.domain.po.Admin;

public interface AdminDao extends BaseDao<Admin>{

	Admin getAdminByPhone(String hql, String phone);

}
