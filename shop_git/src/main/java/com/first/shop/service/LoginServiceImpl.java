package com.first.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.first.shop.dao.LoginDao;
import com.first.shop.dto.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;
	
	// 비밀번호 인코딩 관련
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public User checkUser(User user) {
		// 로그인페이지에서 넘겨준 유저객체와 DB에 있는 유저 데이터의 아이디,비밀번호 
		// 비교해서 동일할 경우 성공
		User dbUser = loginDao.check(user);
		//
		if(dbUser!=null) {
			// 컨트롤러에서 전달받은 유저와 db유저의 인코딩된 비밀번호가 같은지 비교가 필요 -> 
			 String userPw = user.getPassword();
			 String encodePw = dbUser.getPassword();
			 
			 // 같으면 성공
			 if(encoder.matches(userPw, encodePw)) {
				 return dbUser;
				 
			} else {
			 // 실패
				return null;
			}
		}
			return null;		
	}

}
