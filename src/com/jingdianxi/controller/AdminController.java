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
		// ��ȡ��֤��
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		// md5����
		String md5 = Md5Utils.md5(password);
		// ���ɹ���Ա����
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(md5);
		// �������Աid��Session
		int adminid = adminService.login(admin);
		session.setAttribute("admin", adminid);
		// ��֤��¼��Ϣ
		if (code.equalsIgnoreCase(vertify) && adminid != 0) {
			// ��¼�ɹ������سɹ���Ϣ
			response.getWriter().print("1");
		} else {
			// ��¼ʧ�ܣ����ش�����Ϣ
			response.getWriter().print("0");
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		// ���session  
        session.invalidate();
        // �ض�������ҳ
		return "redirect:/";
	}
	
	@RequestMapping("admin_home")
	public String admin_home() {
		return "admin_home";
	}
}
