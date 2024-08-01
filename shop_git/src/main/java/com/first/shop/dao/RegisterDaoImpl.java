package com.first.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.User;

@Repository
public class RegisterDaoImpl implements RegisterDao {
	
	// 마이바티스 매퍼 네임스페이스
	String namespace = "com.first.shop.dao.RegisterMapper.";
	
	@Autowired
	// db 접근 객체
	SqlSession session;                                    
	
	//회원가입
	@Override
	public int register(User user) {
		return session.insert(namespace+"registerUser", user);
	}

	
	
	// 아이디 중복체크
	@Override
	public int check(User user) {
		int cnt = session.selectOne(namespace+"idCheck",user); 
		
		return cnt;
		
	}


	
}
