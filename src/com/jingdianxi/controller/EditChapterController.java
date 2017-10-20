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
		// ��ȡ�½���Ϣ
		List<Chapter> chapters = chapterService.getChapterByNovel(novelid);
		// ���������������ת�����½��б�ҳ
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("chapters", chapters);
		modelAndView.addObject("novel", novelid);
		modelAndView.setViewName("admin_chapter_list");
		return modelAndView;
	}

	@RequestMapping("/add_chapter/{novelid}")
	public ModelAndView addChapter(@PathVariable("novelid") int novelid) {
		// ��ȡС˵��Ϣ
		Novel novel = novelService.getNovelById(novelid);
		// �����������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.setViewName("admin_chapter_edit");
		return modelAndView;
	}

	@RequestMapping("/update_chapter/{novelid}/{chapterid}")
	public ModelAndView updateNovel(@PathVariable("novelid") int novelid, @PathVariable("chapterid") int chapterid) {
		// ��ȡ�½���Ϣ
		Chapter chapter = chapterService.getChapterById(chapterid);
		// ��ȡС˵��Ϣ
		Novel novel = novelService.getNovelById(novelid);
		// �����������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("chapter", chapter);
		modelAndView.addObject("novel", novel);
		modelAndView.setViewName("admin_chapter_edit");
		return modelAndView;
	}
	
	@RequestMapping("edit_content")
	public void editContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ�½�id
		String chapter = request.getParameter("chapter");
		// �ж��½�id�Ƿ�Ϊ�ջ���ַ���
		if (chapter != null && !chapter.equals("")) {
			// ��id��Ϊ�գ���ȡ�½�����
			int chapterid = Integer.parseInt(chapter);
			// �����½�����
			response.getWriter().println(chapterService.getChapterById(chapterid).getContent());
		}
	}
	
	@RequestMapping("submit_chapter")
	public String submitChapter(Chapter chapter, HttpServletRequest request) {
		// ��ȡ�������
		int novelid = Integer.parseInt(request.getParameter("novel"));
		chapter.setContent(request.getParameter("editorValue"));
		// �ж��������Ƿ�Ϊ��
		if (chapter.getId() != null) {
			// ������Ϊ���ַ������޸��½�
			chapterService.editChapter(chapter);
		} else {
			// ������Ϊ���ַ����������½�
			chapterService.addChapter(chapter);
		}
		// �ض������½ڹ���ҳ
		return "redirect:/admin/edit_chapter/" + novelid;
	}

	@RequestMapping("/del_chapter/{novelid}/{chapterid}")
	public String delNovel(@PathVariable("novelid") int novelid, @PathVariable("chapterid") int chapterid) {
		// ɾ���½�
		chapterService.delChapter(chapterid);
		// �ض������½ڹ���ҳ
		return "redirect:/admin/edit_chapter/" + novelid;
	}
}
