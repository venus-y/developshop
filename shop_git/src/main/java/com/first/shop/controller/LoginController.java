package com.first.shop.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.shop.dto.User;
import com.first.shop.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoginService loginService; 
	
	// 로그인 페이지 불러오기
	@GetMapping("/getLogin")
	public String getLogin() {
		return "loginForm";
	}
	
	//로그인 요청
	@PostMapping("/postLogin")
	public String postLogin(User user, boolean rememberId, HttpServletRequest request,
			HttpServletResponse response, String toURL) throws UnsupportedEncodingException {
		
		User dbUser = loginService.checkUser(user);
		// 로그인 실패시 메세지와 함께 로그인 페이지로 재이동시킨다.
		String msg = URLEncoder.encode("아이디나 비밀번호가 일치하지 않습니다.","utf-8");
		if(dbUser==null) {			
			return  "redirect:/login/getLogin?msg="+ msg;
		}
		
		// 로그인 성공 시 요청한 쪽의 세션에 유저객체를 담아 반환
		HttpSession session = request.getSession();
		session.setAttribute("user", dbUser);
		// 아이디 기억에 체크할 경우 쿠키에 유저 아이디를 저장한다.
		if(rememberId) {
			Cookie cookie = new Cookie("id", dbUser.getId());
			// 요청이 전달될 곳에 쿠키를 추가해준다.
			response.addCookie(cookie);
		}else {
			//체크 안할 경우 기존쿠키를 없애준다. -> 쿠키 유효시간을 0으로 설정해서
			Cookie cookie = new Cookie("id", dbUser.getId());
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}		
		return toURL.isEmpty() ? "redirect:/" : "redirect:" + toURL;
	}
	
	//로그아웃 요청
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션을 종료시킨다.
		session.removeAttribute("access_token");
		session.removeAttribute("user");
		session.invalidate();
		
		return "redirect:/";
	}
}
