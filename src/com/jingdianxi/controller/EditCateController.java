package com.jingdianxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jingdianxi.domain.Category;
import com.jingdianxi.service.CategoryService;

@Controller
@RequestMapping("admin")
public class EditCateController {
	@Autowired
	CategoryService categoryService;

	@RequestMapping("edit_cate")
	public ModelAndView adminCate() {
		// ��ȡ������Ϣ
		List<Category> categories = categoryService.getAllCategory();
		// �����������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin_category");
		return modelAndView;
	}

	@RequestMapping("submit_cate")
	public String submitCate(Category category) {
		// �ж��������Ƿ�Ϊ���ַ���
		if (category.getId() != null) {
			// ������Ϊ�գ��޸ķ���
			categoryService.editCate(category);
		} else {
			// ������Ϊ�գ����ӷ���
			categoryService.addCate(category);
		}
		// �ض������������ҳ
		return "redirect:/admin/edit_cate";
	}

	@RequestMapping("/update_cate/{categoryid}")
	public ModelAndView updateCate(@PathVariable("categoryid") int categoryid) {
		// ��ȡ������Ϣ
		Category category = categoryService.getCateById(categoryid);
		// ���������������ת�����������ҳ
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("category", category);
		modelAndView.setViewName("forward:/admin/edit_cate");
		return modelAndView;
	}

	@RequestMapping("/del_cate/{categoryid}")
	public String delCate(@PathVariable("categoryid") int categoryid) {
		// ɾ������
		categoryService.delCate(categoryid);
		// �ض������������ҳ
		return "redirect:/admin/edit_cate";
	}
}
