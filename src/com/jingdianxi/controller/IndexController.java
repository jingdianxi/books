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
		// 从数据库获取全部小说
		List<Novel> novels = novelService.getAllNovel();
		// 从数据库获取全部分类
		List<Category> categories = categoryService.getAllCategory();
		// 声明ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		// 取得Cookie集合
		Cookie[] cookies = request.getCookies();
		// 判断Cookie集合是否为空
		if (cookies != null) {
			// 声明变量，保存Cookie
			String recent = "";
			// 获取Cookie内容
			for (Cookie cookie : cookies) {
				// 从Cookie中获取recent字段
				if (cookie.getName().equals("recent")) {
					recent = cookie.getValue();
				}
			}
			// 判断是否获取recent字段
			if (!recent.equals("")) {
				// 生成已浏览章节id字符串集合
				String[] recents = recent.split("#");
				// 声明已浏览章节id集合
				List<Integer> recentChapters = new ArrayList<Integer>();
				// 遍历已浏览章节id字符串集合
				for (int i = 0; i < recents.length; i++) {
					// 获取浏览章节id集合
					recentChapters.add(Integer.parseInt(recents[i]));
				}
				// 按章节id批量查询小说
				List<Map<String, Object>> maps = chapterService.batchGetChapters(recentChapters);
				// 遍历map
				for (Map<String, Object> map : maps) {
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						System.out.println(entry.getKey() + " " + entry.getValue());
					}
				}
				modelAndView.addObject("maps", maps);
			}
		}
		// 增加请求参数
		modelAndView.addObject("novels", novels);
		modelAndView.addObject("categories", categories);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping("paged")
	public void paged(int pageIndex, int pageSize, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer category = (request.getParameter("category") != null) ? Integer.valueOf(request.getParameter("category")) : 0;
		String condition = request.getParameter("condition");
		// 创建分页参数对象
		PagedParam pagedParam = new PagedParam();
		pagedParam.setCategory(category);
		pagedParam.setPageIndex(pageIndex);
		pagedParam.setPageSize(pageSize);
		pagedParam.setCondition(condition);
		// 获取小说分页查询结果与记录数
		List<Novel> list = novelService.getNovelPaged(pagedParam);
		int total = novelService.getCount(pagedParam);
		// 创建分页查询返回数据类
		PagedModel page = new PagedModel();
		page.setList(list);
		page.setTotal(total);
		// 增加请求参数，生成json字符串并返回
		String json = JSON.toJSONString(page);
		response.getWriter().println(json);
	}

	@RequestMapping("vertify")
	public void vertify(HttpServletRequest request, HttpServletResponse response) {
		// 将内存图片（二进制流）返回给客户端
		BufferedImage img = new BufferedImage(100, 22, BufferedImage.TYPE_INT_RGB);
		// 作图环境
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
		// 取得Cookie集合
		Cookie[] cookies = request.getCookies();
		// 判断Cookie集合是否为空
		if (cookies != null) {
			// 获取Cookie内容
			for (Cookie cookie : cookies) {
				// 从Cookie中获取recent字段
				if (cookie.getName().equals("recent")) {
					// 删除Cookie中recent字段
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
		// 重定向至首页
		return "redirect:/";
	}
	
	@RequestMapping("login_page")
	public String login_page() {
		return "login";
	}
}
