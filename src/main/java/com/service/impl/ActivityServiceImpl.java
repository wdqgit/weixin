package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ActivityDao;
import com.domain.po.Activity;
import com.domain.po.ActivityType;
import com.service.ActivityService;
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
	@Resource
	private ActivityDao activityDao;

	
	public void save(Activity activity) {
		activityDao.save(activity);
	}

	@Override
	public Activity get(Long id) {
		if(id != null && id > 0){
			return activityDao.get(id);
		}else{
			return null;
		}
	}

	@Override
	public void update(Activity activity) {
		activityDao.update(activity);
	}

	@Override
	public void delete(Long id) {
		Activity activity = get(id);
		activity.setRun_type(ActivityType.END);
		activityDao.update(activity);
	}

	@Override
	public List<Activity> findRun() {
		String hql = "from Activity a where run_type=?";
		Object[] params = new Object[]{ActivityType.RUN};
		
		List<Activity> list = activityDao.find(hql, params);
		List<Activity> retList = new ArrayList<Activity>();
		retList.addAll(list);
		for(Activity activity : list){
			Long time = System.currentTimeMillis() - activity.getEnd_time().getTime();
			if(time >= 0){
				activity.setRun_type(ActivityType.END);
				activityDao.update(activity);
				retList.remove(activity);
			}
		}
		return retList;
		
	}

}
