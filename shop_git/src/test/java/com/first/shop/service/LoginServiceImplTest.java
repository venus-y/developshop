package com.first.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.User;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

public class LoginServiceImplTest extends TestCase {
	
	@Autowired
	LoginService loginService;
	
	@Test
	public void testCheckUser() {
		// db로부터 넘어받은 유저와 로그인 페이지에서 넘겨준 유저의 아이디 비밀번호 동일여부 검사
		User user = new User();
		user.setId("geumsung7769");
		user.setPassword("geum0830@");
	
		User dbUser = loginService.checkUser(user);
	
		if(dbUser != null) {
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패");
		}
	}

}
