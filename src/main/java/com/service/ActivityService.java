package com.service;

import java.util.List;

import com.domain.po.Activity;

public interface ActivityService {

	void save(Activity activity);

	Activity get(Long id);

	void update(Activity activity);
	void delete(Long id);

	List<Activity> findRun();

}
