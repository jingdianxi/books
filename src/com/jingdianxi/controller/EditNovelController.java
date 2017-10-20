package com.jingdianxi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jingdianxi.domain.Category;
import com.jingdianxi.domain.Novel;
import com.jingdianxi.service.CategoryService;
import com.jingdianxi.service.NovelService;

@Controller
@RequestMapping("admin")
public class EditNovelController {
	@Autowired
	NovelService novelService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping("edit_novel")
	public ModelAndView adminNovel() {
		// 获取小说信息
		List<Novel> novels = novelService.getAllNovel();
		// 增加请求参数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novels", novels);
		modelAndView.setViewName("admin_novel_list");
		return modelAndView;
	}

	@RequestMapping("add_novel")
	public ModelAndView addNovel() {
		// 获取分类信息
		List<Category> categories = categoryService.getAllCategory();
		// 增加请求参数，并转发至小说编辑页
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin_novel_edit");
		return modelAndView;
	}

	@RequestMapping("/update_novel/{novelid}")
	public ModelAndView updateNovel(@PathVariable("novelid") int novelid) {
		// 获取小说信息
		Novel novel = novelService.getNovelById(novelid);
		// 获取分类信息
		List<Category> categories = categoryService.getAllCategory();
		// 增加请求参数，并转发至小说编辑页
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin_novel_edit");
		return modelAndView;
	}

	@RequestMapping("submit_novel")
	public String submitNovel(@RequestParam MultipartFile file, HttpServletRequest request, Novel novel) throws IllegalStateException, IOException {
		// 判断是否上传文件
		if (!file.isEmpty()) {
			// 获取上传文件名，使用UUID生成唯一标识
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			// 设置文件保存位置字段值
			novel.setCover("img/" + fileName);
			// 获取服务器项目发布运行所在地址
			String path = request.getServletContext().getRealPath("/assets/img") + File.separator;
			// 判断文件夹是否存在
			File folder = new File(path);
			// 若不存在，则新建文件夹
			if (!folder.exists()) {
				folder.mkdir();
			}
			// 上传文件
			File localFile = new File(path + fileName);
			file.transferTo(localFile);
		} else {
			// 表单数据无封面文件地址字段，未上传文件时设置封面文件地址为空字符串
			novel.setCover("");
		}
		// 判断隐藏域是否为空字符串
		if (novel.getId() != null) {
			// 隐藏域不为空，修改小说，并判断封面是否更新
			if (novel.getCover().equals("")) {
				// 若未更新，则获取原封面文件地址
				novel.setCover(novelService.getNovelById(novel.getId()).getCover());
			}
			novelService.editNovel(novel);
		} else {
			// 隐藏域为空，增加小说
			novelService.addNovel(novel);
		}
		// 重定向至小说管理页
		return "redirect:/admin/edit_novel";
	}

	@RequestMapping("/del_novel/{novelid}")
	public String delNovel(@PathVariable("novelid") int novelid) {
		// 删除小说
		novelService.delNovel(novelid);
		// 重定向至小说管理页
		return "redirect:/admin/edit_novel";
	}
}
