package com.service;

import java.io.Serializable;
import java.util.List;

import com.domain.po.DiseaseMessage;
import com.domain.po.PageBean;

public interface DiseaseMessageService {

	PageBean<DiseaseMessage> getPageBean(Integer currentPage, int pageSize);

	void add(DiseaseMessage diseaseMessage);
	void update(DiseaseMessage diseaseMessage);
	void delete(Serializable id);
	List<DiseaseMessage> find(String key);

	DiseaseMessage get(Long id);
	

}
