package com.domain.po;

import java.util.List;

public class PageBean<T> {
	
	private int currentPage;
	private int pageSize;
	private int recordCount;
	private int pageCount;
	private int beginPageIndex;
	private int endPageIndex;
	private List<T> recordList;
	
	
	
	
	public PageBean(){}
	
	public PageBean(int currentPage, int pageSize, int recordCount,
			 List<T> recordList) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.recordList = recordList;
		pageCount = recordCount % pageSize == 0 ? recordCount / pageSize : recordCount / pageSize +1;
		if(pageCount <= 5){
			beginPageIndex = 1;
			endPageIndex = pageCount;
		}else{
			beginPageIndex = currentPage - 2;
			endPageIndex = currentPage + 2;
			if(beginPageIndex < 1){
				beginPageIndex = 1;
				endPageIndex = 5;
			}
			if(endPageIndex > pageCount){
				endPageIndex = pageCount;
				beginPageIndex = pageCount - 5 + 1;
			}
		}
		
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	public List<T> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}
	
	

}
