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
		
		// ��Session�л�ȡ��¼�û�id
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("admin");
		// �ж��û��Ƿ��¼
		if (id == null || id == 0) {
			// �û�δ��¼��ת����������ҳ
			response.sendRedirect("/");
			return false;
		} else {
			// �û��ѵ�¼
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
