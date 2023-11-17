package com.first.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.Cart;
import com.first.shop.dto.Product;

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
	
	// 장바구니 상품 가져오기
	@Override
	public Product productImage(int product_id) {
		return session.selectOne(namespace+"cartProductImage", product_id);
		}
	
	// 장바구니 목록 가져오기
	@Override
	public List<Cart> list(String user_id) {
		return session.selectList(namespace+"cartList", user_id);	
		}

}
