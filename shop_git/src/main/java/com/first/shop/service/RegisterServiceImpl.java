package com.first.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.shop.dao.RegisterDao;
import com.first.shop.dto.User;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	RegisterDao registerDao;
	
	// 회원가입 처리
	@Override
	public int registerUser(User user) {
		return registerDao.register(user);
	}

	@Override
	public int idCheck(User user) {
		int rowCnt = registerDao.check(user);
		// 아이디체크의 결과가 1일 경우 아이디가 이미 존재하는 것
		return rowCnt;
	}

}
