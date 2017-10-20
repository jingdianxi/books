package com.jingdianxi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
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
public class ChapterController {
	@Autowired
	NovelService novelService;
	@Autowired
	ChapterService chapterService;

	@RequestMapping("/chapter/{chapterid}")
	public ModelAndView chapter(@PathVariable("chapterid") int chapterid, HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�½ڶ���
		Chapter chapter = chapterService.getChapterById(chapterid);
		// ��ȡС˵����
		Novel novel = novelService.getNovelById(chapter.getNovel());
		// ����С˵hits�ֶ�ֵ
		novelService.updateHits(chapterid);

		// ȡ��Cookie����
		Cookie[] cookies = request.getCookies();
		// ��������������Cookie
		String recent = "";
		// �ж�Cookie�����Ƿ�Ϊ��
		if (cookies != null) {
			// ����Ϊ�գ�����Cookie
			for (Cookie cookie : cookies) {
				// ��ȡCookie��recent�ֶ�
				if (cookie.getName().equals("recent")) {
					recent = cookie.getValue();
					// ����������½�id�ַ�������
					String[] recents = recent.split("#");
					// ����������½ڼ���
					List<Chapter> recentChapters = new ArrayList<Chapter>();
					// ����������½�id�ַ�������
					for (int i = 0; i < recents.length; i++) {
						// ��ȡ������½���Ϣ
						recentChapters.add(chapterService.getChapterById(Integer.parseInt(recents[i])));
					}
					// ������������¼Cookie���Ƿ����׷���½�id��ӦС˵id
					boolean isExist = false;;
					// ����������½���Ϣ
					for (int i = 0; i < recentChapters.size(); i++) {
						// �ж�׷���½�id��ӦС˵id�Ƿ���ͬ
						if (recentChapters.get(i).getNovel() == novel.getId()) {
							// ����ͬ��������½�id
							recents[i] = new Integer(chapterid).toString();
							// ������������½�id�ַ�������
							recent = "";
							for (int j = 0; j < recents.length; j++) {
								recent += recents[j] + "#";
							}
							recent = recent.substring(0, recent.length() - 1);
							isExist = true;
							// ����ѭ��
							break;
						}
					}
					// �ж�Cookie���Ƿ����׷���½�id��ӦС˵id
					if (!isExist) {
						// �������ڣ���׷���½�id
						recent += "#" + new Integer(chapterid).toString();
						// �ж�����½�id�ַ�������
						if (recent.split("#").length > 3) {
							// ���������Ƴ��ȣ����ȡ�ַ���
							recent = recent.substring(recent.indexOf("#") + 1);
						}
					}
					// ����Cookie��recent�ֶ�
					cookie.setValue(recent);
					cookie.setMaxAge(60*60*24);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		// �ж�recent�ֶ��Ƿ�Ϊ��
		if (recent.equals("")) {
			// ��Ϊ�գ�����Cookie
			Cookie cookie = new Cookie("recent", new Integer(chapterid).toString());
			cookie.setMaxAge(60*60*24);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
		// ��ȡ��һ��id����һ��id
		int prev = chapterService.getPrevChapter(chapterid);
		int next = chapterService.getNextChapter(chapterid);
		// �����������
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.addObject("chapter", chapter);
		modelAndView.addObject("prev", prev);
		modelAndView.addObject("next", next);
		modelAndView.setViewName("chapter");
		return modelAndView;
	}
}
