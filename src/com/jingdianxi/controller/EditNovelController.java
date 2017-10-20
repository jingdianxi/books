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
		// ��ȡС˵��Ϣ
		List<Novel> novels = novelService.getAllNovel();
		// �����������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novels", novels);
		modelAndView.setViewName("admin_novel_list");
		return modelAndView;
	}

	@RequestMapping("add_novel")
	public ModelAndView addNovel() {
		// ��ȡ������Ϣ
		List<Category> categories = categoryService.getAllCategory();
		// ���������������ת����С˵�༭ҳ
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin_novel_edit");
		return modelAndView;
	}

	@RequestMapping("/update_novel/{novelid}")
	public ModelAndView updateNovel(@PathVariable("novelid") int novelid) {
		// ��ȡС˵��Ϣ
		Novel novel = novelService.getNovelById(novelid);
		// ��ȡ������Ϣ
		List<Category> categories = categoryService.getAllCategory();
		// ���������������ת����С˵�༭ҳ
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("admin_novel_edit");
		return modelAndView;
	}

	@RequestMapping("submit_novel")
	public String submitNovel(@RequestParam MultipartFile file, HttpServletRequest request, Novel novel) throws IllegalStateException, IOException {
		// �ж��Ƿ��ϴ��ļ�
		if (!file.isEmpty()) {
			// ��ȡ�ϴ��ļ�����ʹ��UUID����Ψһ��ʶ
			String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			// �����ļ�����λ���ֶ�ֵ
			novel.setCover("img/" + fileName);
			// ��ȡ��������Ŀ�����������ڵ�ַ
			String path = request.getServletContext().getRealPath("/assets/img") + File.separator;
			// �ж��ļ����Ƿ����
			File folder = new File(path);
			// �������ڣ����½��ļ���
			if (!folder.exists()) {
				folder.mkdir();
			}
			// �ϴ��ļ�
			File localFile = new File(path + fileName);
			file.transferTo(localFile);
		} else {
			// �������޷����ļ���ַ�ֶΣ�δ�ϴ��ļ�ʱ���÷����ļ���ַΪ���ַ���
			novel.setCover("");
		}
		// �ж��������Ƿ�Ϊ���ַ���
		if (novel.getId() != null) {
			// ������Ϊ�գ��޸�С˵�����жϷ����Ƿ����
			if (novel.getCover().equals("")) {
				// ��δ���£����ȡԭ�����ļ���ַ
				novel.setCover(novelService.getNovelById(novel.getId()).getCover());
			}
			novelService.editNovel(novel);
		} else {
			// ������Ϊ�գ�����С˵
			novelService.addNovel(novel);
		}
		// �ض�����С˵����ҳ
		return "redirect:/admin/edit_novel";
	}

	@RequestMapping("/del_novel/{novelid}")
	public String delNovel(@PathVariable("novelid") int novelid) {
		// ɾ��С˵
		novelService.delNovel(novelid);
		// �ض�����С˵����ҳ
		return "redirect:/admin/edit_novel";
	}
}
