package com.service;

import java.util.List;

import com.domain.po.Admin;
import com.domain.po.People;

public interface PeopleService {

	void save(People people);

	People getByPhone(String phone);

	Admin getAdminByPhone(String phone);

	People get(Long peopleid);

	Admin getAdminById(Long id);

	void updateAdmin(Admin admin);

	void updatePeople(People people);

	void saveAdmin(Admin admin);

	List<Admin> findAdmin();

	void deleteAdmin(Long id);

}
