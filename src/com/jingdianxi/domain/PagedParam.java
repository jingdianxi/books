package com.jingdianxi.domain;

public class PagedParam {
	private int pageIndex;
	private int pageSize;
	private int pageStart;
	private Integer category;
	private String condition;
	public int getPageStart() {
		pageStart = pageSize * pageIndex;
		return pageStart;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
}
