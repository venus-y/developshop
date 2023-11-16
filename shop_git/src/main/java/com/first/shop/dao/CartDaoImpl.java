package com.first.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.Cart;

@Repository
public class CartDaoImpl implements CartDao {
	
	// 매퍼 주소
	String namespace="com.first.shop.dao.CartMapper.";
	
	@Autowired
	SqlSession session;
	
	// 장바구니 등록
	@Override
	public int register(Cart cart) {
		return session.insert(namespace+"registerCart", cart);
	}

}
