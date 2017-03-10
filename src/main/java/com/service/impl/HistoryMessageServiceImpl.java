package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.HistoryMessageDao;
import com.domain.po.HistoryMessage;
import com.domain.po.PageBean;
import com.service.HistoryMessageService;

@Service
@Transactional
public class HistoryMessageServiceImpl implements HistoryMessageService{

	@Resource
	private HistoryMessageDao historyMessageDao;
	@Override
	public void delete(Long id) {
		historyMessageDao.delete(id);
	}

	@Override
	public HistoryMessage get(Long id) {
		// TODO Auto-generated method stub
		return historyMessageDao.get(id);
	}

	@Override
	public void update(HistoryMessage historyMessage) {
		historyMessageDao.update(historyMessage);
	}

	@Override
	public void add(HistoryMessage historyMessage) {
		historyMessageDao.save(historyMessage);
	}

	@Override
	public PageBean<HistoryMessage> getPageBean(Integer currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return historyMessageDao.getPageBean(currentPage, pageSize, "from HistoryMessage", "select count(1) from HistoryMessage", null);
	}

	@Override
	public List<HistoryMessage> findAll() {
		// TODO Auto-generated method stub
		return historyMessageDao.findAll();
	}

}
