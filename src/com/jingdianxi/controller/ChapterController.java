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
		// 获取章节对象
		Chapter chapter = chapterService.getChapterById(chapterid);
		// 获取小说对象
		Novel novel = novelService.getNovelById(chapter.getNovel());
		// 更新小说hits字段值
		novelService.updateHits(chapterid);

		// 取得Cookie集合
		Cookie[] cookies = request.getCookies();
		// 声明变量，保存Cookie
		String recent = "";
		// 判断Cookie集合是否为空
		if (cookies != null) {
			// 若不为空，更新Cookie
			for (Cookie cookie : cookies) {
				// 获取Cookie中recent字段
				if (cookie.getName().equals("recent")) {
					recent = cookie.getValue();
					// 生成已浏览章节id字符串集合
					String[] recents = recent.split("#");
					// 声明已浏览章节集合
					List<Chapter> recentChapters = new ArrayList<Chapter>();
					// 遍历已浏览章节id字符串集合
					for (int i = 0; i < recents.length; i++) {
						// 获取已浏览章节信息
						recentChapters.add(chapterService.getChapterById(Integer.parseInt(recents[i])));
					}
					// 声明变量，记录Cookie中是否存在追加章节id对应小说id
					boolean isExist = false;;
					// 遍历已浏览章节信息
					for (int i = 0; i < recentChapters.size(); i++) {
						// 判断追加章节id对应小说id是否相同
						if (recentChapters.get(i).getNovel() == novel.getId()) {
							// 若相同，则更新章节id
							recents[i] = new Integer(chapterid).toString();
							// 重新生成浏览章节id字符串集合
							recent = "";
							for (int j = 0; j < recents.length; j++) {
								recent += recents[j] + "#";
							}
							recent = recent.substring(0, recent.length() - 1);
							isExist = true;
							// 跳出循环
							break;
						}
					}
					// 判断Cookie中是否存在追加章节id对应小说id
					if (!isExist) {
						// 若不存在，则追加章节id
						recent += "#" + new Integer(chapterid).toString();
						// 判断浏览章节id字符串长度
						if (recent.split("#").length > 3) {
							// 若超过限制长度，则截取字符串
							recent = recent.substring(recent.indexOf("#") + 1);
						}
					}
					// 更新Cookie中recent字段
					cookie.setValue(recent);
					cookie.setMaxAge(60*60*24);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		// 判断recent字段是否为空
		if (recent.equals("")) {
			// 若为空，生成Cookie
			Cookie cookie = new Cookie("recent", new Integer(chapterid).toString());
			cookie.setMaxAge(60*60*24);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		
		// 获取上一章id与下一章id
		int prev = chapterService.getPrevChapter(chapterid);
		int next = chapterService.getNextChapter(chapterid);
		// 增加请求参数
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("novel", novel);
		modelAndView.addObject("chapter", chapter);
		modelAndView.addObject("prev", prev);
		modelAndView.addObject("next", next);
		modelAndView.setViewName("chapter");
		return modelAndView;
	}
}
