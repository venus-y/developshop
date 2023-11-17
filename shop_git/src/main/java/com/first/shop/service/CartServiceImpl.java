package com.first.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.first.shop.dao.CartDao;
import com.first.shop.dto.Cart;
import com.first.shop.dto.Product;

@Service
public class CartServiceImpl implements CartService {
	
	private final CartDao cartDao;
	
	public CartServiceImpl(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	// 장바구니 등록
	@Override
	public int registerCart(Cart cart) {
		// 총 적립포인트, 총 가격정보를 초기화해준다.
		cart.init();
		System.out.println("cart정보:"+cart);
		return cartDao.register(cart);
	}
	
	// 장바구니 상품 이미지 받아오기
	@Override
	public Product CartProductImage(int product_id) {
		return cartDao.productImage(product_id);
		}
	
	// 유저의 장바구니 목록 가져오기
	@Override
	public List<Cart> getCartList(String user_id) {
		return cartDao.list(user_id);
	}

}
