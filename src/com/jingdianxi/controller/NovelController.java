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
		// 使用@PathVariable获取id，使用model传回页面

		// 获取小说对象
		Novel novel = novelService.getNovelById(novelid);
		// 获取小说全部章节
		List<Chapter> chapters = chapterService.getChapterByNovel(novelid);
		// 增加请求参数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.addObject("chapters", chapters);
		modelAndView.setViewName("novel");
		return modelAndView;
	}
}
