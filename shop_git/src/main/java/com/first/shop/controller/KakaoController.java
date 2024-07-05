package com.first.shop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.first.shop.dto.User;
import com.first.shop.service.KakaoService;
import com.first.shop.service.RegisterService;

@RequestMapping("/kakao")
@Controller
public class KakaoController {
	

	private final RegisterService registerService;
	private final KakaoService kakaoService;
	private final BCryptPasswordEncoder encoder;

	public KakaoController(RegisterService registerService, KakaoService kakaoService, BCryptPasswordEncoder encoder) {
		this.registerService = registerService;
		this.kakaoService = kakaoService;
		this.encoder = encoder;
	}
	
	
	
	@GetMapping("/kakao_callback")
	public String redirectKakao(@RequestParam String code, HttpServletRequest request) {
		
		String kakaoToken = kakaoService.getReturnAccessToken(code);
		
		Map<String, Object> result = kakaoService.getUserInfo(kakaoToken);
		
		Map<String, Object> address = kakaoService.getAddress(kakaoToken);
		
		String email = (String)result.get("email");
		String password = "geumsung0830@";
		String name = (String)result.get("nickname");
		int age = 22;
		String birth = "1999-11-11";
		String user_address = (String)address.get("baseAddress");
		user_address += (String)address.get("detailAddress");
		String user_email = (String)result.get("email");
		String color = "#508050";
		int snscheck = 1;
		
		
		
		User user = new User();
		user.setId(email);
		
		int count = registerService.idCheck(user);
					
		if(count == 0) {
			String encodePassword = encoder.encode(password); 
			user.setPassword(encodePassword);
			user.setName(name);
			user.setAge(age);
			user.setAddress(user_address);
			user.setTel("010-1111-1111");
			user.setEmail(user_email);
			user.setSex(1);
			user.setColor(color);
			user.setSnscheck(snscheck);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date birthDate = null;
	        
			try {
				birthDate = dateFormat.parse(birth);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			user.setBirth(birthDate);
			registerService.registerUser(user);
			
			HttpSession session = request.getSession();
			User dbUser = kakaoService.getKaKaoUser_Info(user.getId());
			session.setAttribute("user", dbUser);
			session.setAttribute("access_token", kakaoToken);
			
		}else {
			HttpSession session = request.getSession();
			User DBuser = kakaoService.getKaKaoUser_Info(user.getId());
			session.setAttribute("user", DBuser);
			session.setAttribute("access_token", kakaoToken);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/unlink")
	public String unlink(HttpServletRequest request) {
		
		HttpSession session =  request.getSession();
		
		String access_token = (String)session.getAttribute("access_token");
		
		String reqURL = "https://kapi.kakao.com/v1/user/unlink";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_token);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
			conn.setDoOutput(true); 
			
			int responseCode = conn.getResponseCode();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String result = "";
			String line = "";
			
			while((line = br.readLine()) != null) {
				result += line;
			}
			
			
			
			session.removeAttribute("access_token");
			session.removeAttribute("user");
			
			session.invalidate();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
			return "redirect:/";
	}
	
}
