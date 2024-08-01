package com.first.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.first.shop.dto.User;

public class AdminInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션에 있는 유저를 확인해 유저가 관리자일 경우 접근 가능하게 한다.
		HttpSession httpSession = request.getSession();
		
		User user = (User)httpSession.getAttribute("user");
		
		if(user == null || user.getAdmincheck() == 0) {
			
			response.sendRedirect("loginForm");
			return false;
		}else {
			return true;
		}		
	}
}
