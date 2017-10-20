package com.jingdianxi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingdianxi.domain.Admin;
import com.jingdianxi.service.AdminService;
import com.jingdianxi.util.Md5Utils;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@RequestMapping("login")
	public void login(String vertify, String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取验证码
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		// md5加密
		String md5 = Md5Utils.md5(password);
		// 生成管理员对象
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(md5);
		// 保存管理员id至Session
		int adminid = adminService.login(admin);
		session.setAttribute("admin", adminid);
		// 验证登录信息
		if (code.equalsIgnoreCase(vertify) && adminid != 0) {
			// 登录成功，返回成功信息
			response.getWriter().print("1");
		} else {
			// 登录失败，返回错误信息
			response.getWriter().print("0");
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		// 清除session  
        session.invalidate();
        // 重定向至首页
		return "redirect:/";
	}
	
	@RequestMapping("admin_home")
	public String admin_home() {
		return "admin_home";
	}
}
