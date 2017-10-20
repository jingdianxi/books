package com.jingdianxi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jingdianxi.domain.Category;
import com.jingdianxi.mapper.CategoryMapper;
import com.jingdianxi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = categoryMapper.getAllCategory();
		return categories;
	}

	@Override
	public Category getCateById(int categoryid) {
		// TODO Auto-generated method stub
		Category category = categoryMapper.getCateById(categoryid);
		return category;
	}

	@Override
	public int addCate(Category category) {
		// TODO Auto-generated method stub
		return categoryMapper.addCate(category);
	}

	@Override
	public int delCate(int categoryid) {
		// TODO Auto-generated method stub
		return categoryMapper.delCate(categoryid);
	}

	@Override
	public int editCate(Category category) {
		// TODO Auto-generated method stub
		return categoryMapper.editCate(category);
	}

}
