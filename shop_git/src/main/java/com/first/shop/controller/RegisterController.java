package com.first.shop.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.first.shop.dto.User;
import com.first.shop.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	RegisterService registerService;
	
	// 비밀번호 인코딩 관련
	@Autowired
	BCryptPasswordEncoder encoder;
	
	// 메일 전송 클래스 주입
	@Autowired
	JavaMailSender mailSender;
	
	// 날짜 형식 변환을 위해 사용
	@InitBinder
	public void toDate(WebDataBinder binder) {
		// 지정한 형식으로 데이터를 변환해준다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
	
	// 회원가입 페이지 불러오기
	@GetMapping("/getRegister")
	public String getRegisterForm() {
		return "registerForm";
	}
	
	// 회원가입 처리
	@PostMapping("/postRegister")
	public String postRegisterForm(User user, BindingResult result) {
		System.out.println("바인딩Result:"+result);
		//회원가입 페이지로부터 넘어온 유저객체의 비밀번호를 인코딩처리한다.
		//유저의 비밀번호를 다시 세팅
		user.setPassword(encoder.encode(user.getPassword()));
		registerService.registerUser(user);
		
		return "redirect:/";
	}
	
	// 아이디 중복체크, 처리가 끝난뒤 다시 JSON 데이터로 변환해서 반환해줘야 되기 떄문에 @ResponseBody 를 붙인다.
	@ResponseBody
	@PostMapping("/checkId")
	public int check(@RequestBody User user) {
		// 회원가입 페이지로부터 전달받은 아이디를 통해 아이디 중복처리검사를 시행		
		int idCheckCount = registerService.idCheck(user);
		return idCheckCount;
	}
	
	// 이메일 인증번호 전송 메서드, 비동기 처리이기 때문에 @ResponseBody로 처리
	@ResponseBody
	@GetMapping("/mailNum")
	public String mailNum(String email) {
		// 이메일 넘어왔나 체크
		System.out.println("비동기로 넘어온 이메일" + email);
		
		// 이메일 전송을 위한 랜덤클래스 생성
		Random random = new Random();
		// 인증번호 생성
		int emailNum = random.nextInt(888888)+111111;
		
		// 발신자
		String setFrom = "geumsung7769@naver.com";
		// 수신자
		String toMail = email;
		// 제목 및 내용
		String title = "회원가입 인증 번호입니다.";
		String content = 
				"방문해주셔서 감사합니다." +
				"<br><br>" +
				"인증번호는" + emailNum + "입니다." +
				"<br>" +
				"해당 인증번호를 인증번호 입력창에 입력해주세요.";
		
		// 전송하는 메일 형식 설정
		try {
			  MimeMessage message = mailSender.createMimeMessage();
			  MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			  helper.setFrom(setFrom);
			  helper.setTo(toMail);
			  helper.setSubject(title);
			  helper.setText(content,true);
			  mailSender.send(message);
		  } catch(Exception e) {
			  e.printStackTrace();
		  }	
		
		// 인증번호를 회원가입 뷰로 반환
		
		String num = Integer.toString(emailNum);
		
		return num;
	}
	
		
}
