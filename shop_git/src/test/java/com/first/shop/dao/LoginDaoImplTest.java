package com.first.shop.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.first.shop.dto.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class LoginDaoImplTest {
	
	@Autowired
	LoginDao loginDao;
	
	@Test
	public void testCheck() {
		User user = new User();
		user.setId("geumsung7769");
		// 행의 개수가 1일시 존재하는 유저
		User user2 = loginDao.check(user);
		
		System.out.println(user2);
		
	}

}
