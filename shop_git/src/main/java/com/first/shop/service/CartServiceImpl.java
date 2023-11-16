package com.first.shop.service;

import org.springframework.stereotype.Service;

import com.first.shop.dao.CartDao;
import com.first.shop.dto.Cart;

@Service
public class CartServiceImpl implements CartService {
	
	private final CartDao cartDao;
	
	public CartServiceImpl(CartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	// 장바구니 등록
	@Override
	public int registerCart(Cart cart) {
		return cartDao.register(cart);
	}

}
