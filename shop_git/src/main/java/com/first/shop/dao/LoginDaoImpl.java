package com.first.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.User;

@Repository
public class LoginDaoImpl implements LoginDao {
	//db 접속 객체 주입
	@Autowired
	SqlSession session;
	// 매퍼 namespace
	String namespace = "com.first.shop.dao.LoginMapper.";
	
	
	@Override
	// 로그인 시도 -> 유저 있을 시 유저 반환 , 없을 시 null 반환
	public User check(User user) {
		return session.selectOne(namespace+"checkUser", user);
	}

}
