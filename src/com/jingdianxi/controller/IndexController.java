package com.jingdianxi.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jingdianxi.domain.Category;
import com.jingdianxi.domain.Novel;
import com.jingdianxi.domain.PagedParam;
import com.jingdianxi.jsonobj.PagedModel;
import com.jingdianxi.service.CategoryService;
import com.jingdianxi.service.ChapterService;
import com.jingdianxi.service.NovelService;

@Controller
public class IndexController {

	@Autowired
	NovelService novelService;
	@Autowired
	ChapterService chapterService;
	@Autowired
	CategoryService categoryService;

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) {
		// �����ݿ��ȡȫ��С˵
		List<Novel> novels = novelService.getAllNovel();
		// �����ݿ��ȡȫ������
		List<Category> categories = categoryService.getAllCategory();
		// ����ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		// ȡ��Cookie����
		Cookie[] cookies = request.getCookies();
		// �ж�Cookie�����Ƿ�Ϊ��
		if (cookies != null) {
			// ��������������Cookie
			String recent = "";
			// ��ȡCookie����
			for (Cookie cookie : cookies) {
				// ��Cookie�л�ȡrecent�ֶ�
				if (cookie.getName().equals("recent")) {
					recent = cookie.getValue();
				}
			}
			// �ж��Ƿ��ȡrecent�ֶ�
			if (!recent.equals("")) {
				// ����������½�id�ַ�������
				String[] recents = recent.split("#");
				// ����������½�id����
				List<Integer> recentChapters = new ArrayList<Integer>();
				// ����������½�id�ַ�������
				for (int i = 0; i < recents.length; i++) {
					// ��ȡ����½�id����
					recentChapters.add(Integer.parseInt(recents[i]));
				}
				// ���½�id������ѯС˵
				List<Map<String, Object>> maps = chapterService.batchGetChapters(recentChapters);
				// ����map
				for (Map<String, Object> map : maps) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						System.out.println(entry.getKey() + " " + entry.getValue());
					}
				}
				modelAndView.addObject("maps", maps);
			}
		}
		// �����������
		modelAndView.addObject("novels", novels);
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping("paged")
	public void paged(int pageIndex, int pageSize, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer category = (request.getParameter("category") != null) ? Integer.valueOf(request.getParameter("category")) : 0;
		String condition = request.getParameter("condition");
		// ������ҳ��������
		PagedParam pagedParam = new PagedParam();
		pagedParam.setCategory(category);
		pagedParam.setPageIndex(pageIndex);
		pagedParam.setPageSize(pageSize);
		pagedParam.setCondition(condition);
		// ��ȡС˵��ҳ��ѯ������¼��
		List<Novel> list = novelService.getNovelPaged(pagedParam);
		int total = novelService.getCount(pagedParam);
		// ������ҳ��ѯ����������
		PagedModel page = new PagedModel();
		page.setList(list);
		page.setTotal(total);
		// �����������������json�ַ���������
		String json = JSON.toJSONString(page);
		response.getWriter().println(json);
	}

	@RequestMapping("vertify")
	public void vertify(HttpServletRequest request, HttpServletResponse response) {
		// ���ڴ�ͼƬ���������������ظ��ͻ���
		BufferedImage img = new BufferedImage(100, 22, BufferedImage.TYPE_INT_RGB);
		// ��ͼ����
		Graphics graphics = img.getGraphics();
		graphics.setColor(new Color(195, 195, 195));
		graphics.fillRect(0, 0, 100, 22);

		char[] arr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		Random random = new Random();

		String str = "";
		for (int i = 0; i < 4; i++) {
			int n = random.nextInt(arr.length);
			String code = arr[n] + "";
			graphics.setColor(new Color(0, 0, 0));
			graphics.drawString(code, 8 + i * 25, 14);
			str += code;
		}

		HttpSession session = request.getSession();
		session.setAttribute("code", str);

		try {
			ImageIO.write(img, "JPG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("delCookie")
	public String delCookie(HttpServletRequest request, HttpServletResponse response) {
		// ȡ��Cookie����
		Cookie[] cookies = request.getCookies();
		// �ж�Cookie�����Ƿ�Ϊ��
		if (cookies != null) {
			// ��ȡCookie����
			for (Cookie cookie : cookies) {
				// ��Cookie�л�ȡrecent�ֶ�
				if (cookie.getName().equals("recent")) {
					// ɾ��Cookie��recent�ֶ�
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
		// �ض�������ҳ
		return "redirect:/";
	}
	
	@RequestMapping("login_page")
	public String login_page() {
		return "login";
	}
}
