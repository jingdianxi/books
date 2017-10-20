package com.jingdianxi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jingdianxi.domain.Chapter;
import com.jingdianxi.domain.Novel;
import com.jingdianxi.service.ChapterService;
import com.jingdianxi.service.NovelService;

@Controller
public class NovelController {
	@Autowired
	NovelService novelService;
	@Autowired
	ChapterService chapterService;
	
	@RequestMapping("/novel/{novelid}")
	public ModelAndView novel(@PathVariable("novelid") int novelid) {
		// ʹ��@PathVariable��ȡid��ʹ��model����ҳ��

		// ��ȡС˵����
		Novel novel = novelService.getNovelById(novelid);
		// ��ȡС˵ȫ���½�
		List<Chapter> chapters = chapterService.getChapterByNovel(novelid);
		// �����������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.addObject("chapters", chapters);
		modelAndView.setViewName("novel");
		return modelAndView;
	}
}
