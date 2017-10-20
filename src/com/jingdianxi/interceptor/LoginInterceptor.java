package com.jingdianxi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		
		// 从Session中获取登录用户id
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("admin");
		// 判断用户是否登录
		if (id == null || id == 0) {
			// 用户未登录，转发请求至首页
			response.sendRedirect("/");
			return false;
		} else {
			// 用户已登录
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
