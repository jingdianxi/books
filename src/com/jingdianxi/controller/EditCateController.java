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
		// 获取分类信息
		List<Category> categories = categoryService.getAllCategory();
		// 增加请求参数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin_category");
		return modelAndView;
	}

	@RequestMapping("submit_cate")
	public String submitCate(Category category) {
		// 判断隐藏域是否为空字符串
		if (category.getId() != null) {
			// 隐藏域不为空，修改分类
			categoryService.editCate(category);
		} else {
			// 隐藏域为空，增加分类
			categoryService.addCate(category);
		}
		// 重定向至分类管理页
		return "redirect:/admin/edit_cate";
	}

	@RequestMapping("/update_cate/{categoryid}")
	public ModelAndView updateCate(@PathVariable("categoryid") int categoryid) {
		// 获取分类信息
		Category category = categoryService.getCateById(categoryid);
		// 增加请求参数，并转发至分类管理页
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("category", category);
		modelAndView.setViewName("forward:/admin/edit_cate");
		return modelAndView;
	}

	@RequestMapping("/del_cate/{categoryid}")
	public String delCate(@PathVariable("categoryid") int categoryid) {
		// 删除分类
		categoryService.delCate(categoryid);
		// 重定向至分类管理页
		return "redirect:/admin/edit_cate";
	}
}
