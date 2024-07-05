package com.first.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.User;

@Repository
public class KakaoDao {	
	
	private final SqlSession session;
	
	public KakaoDao(SqlSession session) {
		this.session = session;
	}
	
	String namespace="com.first.shop.dao.kakaoMapper";
	
	
	
	public User kakaoUser(String id) {
		return session.selectOne("kakaoUser_Info", id);
	}

}
