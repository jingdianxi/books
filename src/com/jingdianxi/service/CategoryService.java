package com.jingdianxi.service;

import java.util.List;

import com.jingdianxi.domain.Category;

public interface CategoryService {
	// ���ӷ���
	int addCate(Category category);
	// ɾ������
	int delCate(int categoryidid);
	// �޸ķ���
	int editCate(Category category);
	// ��ѯȫ������
	List<Category> getAllCategory();
	// ��id��ѯ����
	Category getCateById(int categoryid);
}
