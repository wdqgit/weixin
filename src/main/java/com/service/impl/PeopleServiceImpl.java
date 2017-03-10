package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdminDao;
import com.dao.PeopleDao;
import com.domain.po.Admin;
import com.domain.po.People;
import com.service.PeopleService;
@Service
@Transactional
public class PeopleServiceImpl implements PeopleService {
	@Resource
	private PeopleDao peopleDao;
	@Resource
	private AdminDao adminDao;
	@Override
	public void save(People people) {
		peopleDao.save(people);
	}
	@Override
	public People getByPhone(String phone) {
		String hql = "from People p where p.phone=?";
		return peopleDao.getByPhone(hql, phone);
	}
	@Override
	public Admin getAdminByPhone(String phone) {
		String hql = "from Admin a where a.phone=?";
		return adminDao.getAdminByPhone(hql, phone);
		
	}
	@Override
	public People get(Long peopleid) {
		// TODO Auto-generated method stub
		return peopleDao.get(peopleid);
	}
	@Override
	public Admin getAdminById(Long id) {
		// TODO Auto-generated method stub
		return adminDao.get(id);
	}
	@Override
	public void updateAdmin(Admin admin) {
		adminDao.update(admin);
	}
	@Override
	public void updatePeople(People people) {
		peopleDao.update(people);
	}
	@Override
	public void saveAdmin(Admin admin) {
		adminDao.save(admin);
	}
	@Override
	public List<Admin> findAdmin() {
		// TODO Auto-generated method stub
		List<Admin> admins = adminDao.findAll();
		for(Admin admin : admins){
			if("admin".equals(admin.getName())){
				admins.remove(admin);
				break;
			}
		}
		return admins;
	}
	@Override
	public void deleteAdmin(Long id) {
		adminDao.delete(id);
	}

}