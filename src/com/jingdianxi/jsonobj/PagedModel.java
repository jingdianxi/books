package com.jingdianxi.jsonobj;

import java.util.List;

import com.jingdianxi.domain.Novel;

/*
 * ��ҳ��ѯ����json����
 */
public class PagedModel {
	// ��ѯ��¼��
	private int total;
	// ��ҳ��ѯ���
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
