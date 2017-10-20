package com.jingdianxi.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("admin")
public class EditChapterController {
	@Autowired
	ChapterService chapterService;
	@Autowired
	NovelService novelService;

	@RequestMapping("/edit_chapter/{novelid}")
	public ModelAndView adminChapter(@PathVariable("novelid") int novelid) {
		// 获取章节信息
		List<Chapter> chapters = chapterService.getChapterByNovel(novelid);
		// 增加请求参数，并转发至章节列表页
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("chapters", chapters);
		modelAndView.addObject("novel", novelid);
		modelAndView.setViewName("admin_chapter_list");
		return modelAndView;
	}

	@RequestMapping("/add_chapter/{novelid}")
	public ModelAndView addChapter(@PathVariable("novelid") int novelid) {
		// 获取小说信息
		Novel novel = novelService.getNovelById(novelid);
		// 增加请求参数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.setViewName("admin_chapter_edit");
		return modelAndView;
	}

	@RequestMapping("/update_chapter/{novelid}/{chapterid}")
	public ModelAndView updateNovel(@PathVariable("novelid") int novelid, @PathVariable("chapterid") int chapterid) {
		// 获取章节信息
		Chapter chapter = chapterService.getChapterById(chapterid);
		// 获取小说信息
		Novel novel = novelService.getNovelById(novelid);
		// 增加请求参数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("chapter", chapter);
		modelAndView.addObject("novel", novel);
		modelAndView.setViewName("admin_chapter_edit");
		return modelAndView;
	}
	
	@RequestMapping("edit_content")
	public void editContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取章节id
		String chapter = request.getParameter("chapter");
		// 判断章节id是否为空或空字符串
		if (chapter != null && !chapter.equals("")) {
			// 若id不为空，获取章节内容
			int chapterid = Integer.parseInt(chapter);
			// 保存章节内容
			response.getWriter().println(chapterService.getChapterById(chapterid).getContent());
		}
	}
	
	@RequestMapping("submit_chapter")
	public String submitChapter(Chapter chapter, HttpServletRequest request) {
		// 获取请求参数
		int novelid = Integer.parseInt(request.getParameter("novel"));
		chapter.setContent(request.getParameter("editorValue"));
		// 判断隐藏域是否为空
		if (chapter.getId() != null) {
			// 隐藏域不为空字符串，修改章节
			chapterService.editChapter(chapter);
		} else {
			// 隐藏域为空字符串，增加章节
			chapterService.addChapter(chapter);
		}
		// 重定向至章节管理页
		return "redirect:/admin/edit_chapter/" + novelid;
	}

	@RequestMapping("/del_chapter/{novelid}/{chapterid}")
	public String delNovel(@PathVariable("novelid") int novelid, @PathVariable("chapterid") int chapterid) {
		// 删除章节
		chapterService.delChapter(chapterid);
		// 重定向至章节管理页
		return "redirect:/admin/edit_chapter/" + novelid;
	}
}
