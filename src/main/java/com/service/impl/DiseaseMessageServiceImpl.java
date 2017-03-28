package com.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DiseaseMessageDao;
import com.domain.po.DiseaseMessage;
import com.domain.po.PageBean;
import com.service.DiseaseMessageService;
@Service
@Transactional
public class DiseaseMessageServiceImpl implements DiseaseMessageService{
	@Resource
	private DiseaseMessageDao diseaseMessageDao;
	
	@Override
	public PageBean<DiseaseMessage> getPageBean(Integer currentPage, int pageSize) {
		
		return diseaseMessageDao.getPageBean(currentPage, pageSize, "from DiseaseMessage", "select count(*) from DiseaseMessage", null);
	}

	@Override
	public void add(DiseaseMessage diseaseMessage) {
		diseaseMessageDao.save(diseaseMessage);
	}

	@Override
	public void update(DiseaseMessage diseaseMessage) {
		diseaseMessageDao.update(diseaseMessage);
	}

	@Override
	public void delete(Serializable id) {
		diseaseMessageDao.delete(id);
	}

	@Override
	public List<DiseaseMessage> find(String key) {
		System.out.println(key);
		return diseaseMessageDao.find("from DiseaseMessage d where d.title like '%" + key + "%'");
	}

	@Override
	public DiseaseMessage get(Long id) {
		// TODO Auto-generated method stub
		return diseaseMessageDao.get(id);
	}

}
