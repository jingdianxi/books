package com.jingdianxi.mapper;

import java.util.List;

import com.jingdianxi.domain.Category;

public interface CategoryMapper {
	// 增加分类
	int addCate(Category category);
	// 删除分类
	int delCate(int categoryidid);
	// 修改分类
	int editCate(Category category);
	// 查询全部分类
	List<Category> getAllCategory();
	// 按id查询分类
	Category getCateById(int categoryid);
}
