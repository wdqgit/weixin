package com.service;

import java.util.List;

import com.domain.po.HistoryMessage;
import com.domain.po.PageBean;

public interface HistoryMessageService {

	void delete(Long id);

	HistoryMessage get(Long id);

	void update(HistoryMessage historyMessage);

	void add(HistoryMessage historyMessage);

	PageBean<HistoryMessage> getPageBean(Integer currentPage, int i);

	List<HistoryMessage> findAll();

}
