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
	
	// 장바구니 수정
	@Override
	public int update(Cart cart) {
		return session.update(namespace+"updateCart", cart);
	}
	
	// 개별 장바구니 정보 가져오기
	@Override
	public Cart cartInfo(int cart_id) {
		return session.selectOne(namespace+"cartInfo", cart_id);
	}
	
	// 장바구니 삭제
	@Override
	public int remove(Integer cart_id) {
		return session.delete(namespace+"removeCart", cart_id);
	}
	
	// 장바구니에 담긴 상품의 실재고 조회
	@Override
	public int stock_Integer(Integer product_id) {
		
		return session.selectOne(namespace+"checkStock_Integer", product_id);
	}
	
	// 장바구니에 담긴 상품의 실재고 조회
	@Override 
	public int stock_int(int product_id) {
		System.out.println("상품 아이디?"+product_id);
			
		return session.selectOne(namespace+"checkStock_int", product_id);
	}
	
	// 동일한 유저와 상품아이디가 DB에 있나 체크
	@Override
	public int same_product(Cart cart) {
		return session.selectOne(namespace+"prevent_Same", cart);
	}


}
