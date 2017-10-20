package com.jingdianxi.jsonobj;

import java.util.List;

import com.jingdianxi.domain.Novel;

/*
 * 分页查询返回json对象
 */
public class PagedModel {
	// 查询记录数
	private int total;
	// 分页查询结果
	private List<Novel> list;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Novel> getList() {
		return list;
	}
	public void setList(List<Novel> list) {
		this.list = list;
	}
}
