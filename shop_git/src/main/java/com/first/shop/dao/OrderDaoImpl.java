package com.first.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.shop.dto.Cart;
import com.first.shop.dto.OrderProduct;
import com.first.shop.dto.Orders;
import com.first.shop.dto.Product;
import com.first.shop.dto.User;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	String namespace="com.first.shop.dao.OrderMapper.";
	
	@Autowired
	SqlSession session;
	
	// 주문정보 등록
	@Override
	public int register(Orders orders) {
		return session.insert(namespace+"register_product", orders); 
	}
	
	// 주문상품정보 등록
	@Override
	public int order_product(OrderProduct orderProduct) {
		return session.insert(namespace+"register_order_product", orderProduct);
	}
	
	// 유저 금액, 포인트정보 업데이트
	@Override
	public int update(User user) {
		return session.update(namespace+"update_user", user);
	}
	
	// 상품 재고 업데이트
	@Override
	public int update(Product product) {
		return session.update(namespace+"update_stock", product);
	}
	
	
	// 주문 유저 정보 받아오기
	@Override
	public User user(String id) {
		return session.selectOne(namespace+"orderUser_info", id);
	}

	// 주문 상품 정보 받아오기
	@Override
	public Product product(int product_id) {
		return session.selectOne(namespace+"orderProduct_info", product_id);		
	}
	
	// 주문이 완료된 상품을 장바구니에서 제거
	@Override
	public int delete(Cart cart) {
		return session.delete(namespace+"delete_Ordered_Cart", cart);
	}

	

}
