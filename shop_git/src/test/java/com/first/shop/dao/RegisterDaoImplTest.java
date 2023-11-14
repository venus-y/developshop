package com.first.shop.dao;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RegisterDaoImplTest {
	
	@Autowired
	RegisterDao registerDao;
	
	@Test
	// 회원가입 테스트
	public void testRegister() {
	// 유저가 db에 들어가면 rowCnt가 1이 된다.
	User user = new User();
	user.setId("test555");
	user.setPassword("test");
	user.setAddress("test");
	user.setAge(4);
	user.setName("test");
	user.setSex(1);
	Calendar cal = Calendar.getInstance();
	cal.set(1111, 1,11);
	user.setBirth(new Date(cal.getTimeInMillis()));
	user.setColor("test");
	user.setEmail("test");
	user.setTel("111");
	
	int rowCnt = registerDao.register(user);
	System.out.println(rowCnt);
	}
	
	@Test
	// 아이디 중복 테스트
	public void testIdCheck() {
		int cnt = registerDao.check("test555");
		System.out.println(cnt);
		
		
	}

}